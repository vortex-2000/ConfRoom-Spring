package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.confRoom.controller.BuildingController;
import com.confRoom.exception.BookingStartException;
import com.confRoom.exception.ConditionFailureException;
import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.*;
import com.confRoom.repository.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private ConfRoomRepository confRoomRepo;
	@Autowired
	private ConfRoomService confRoomService;
	@Autowired
	private UserService userService;
    
    
    
	

	private Boolean isValidDuration(String startTime, String endTime) throws ParseException {

		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		Date currStartTime = parser.parse(startTime);
		Date currEndTime = parser.parse(endTime);

		long difference = (currEndTime.getTime() - currStartTime.getTime()) / (60 * 60 * 1000) % 24;

		if (difference < 0 && (24 + difference) > 12)
			return false;

		return difference < 12;

	}
	

	private Boolean isValidFutureDate(LocalDate bookDate) throws ParseException {

		LocalDate maxFutureDate = LocalDate.now().plusDays(10);

		if (bookDate.isAfter(maxFutureDate)) {
			return false;
		}
		return true;
	}

	private Boolean isValidDate(LocalDate bookDate) throws ParseException {

		LocalDate currDate = LocalDate.now();

		if (bookDate.isBefore(currDate) || bookDate.isEqual(currDate)) {
			return false;
		}
		return true;
	}

	// TRY CATCH for runtime exceptions: generic message custom exception model
	// class

	 public Boolean isBookingPresent(int bookingId) {
				
			if(!bookingRepo.existsById(bookingId)) {
				System.out.println("The mentioned booking dosen't exists");
				return false;
			}
			return true;
		}
	 
	@SuppressWarnings("deprecation")
	public Booking addBooking(Booking booking,int capacity) throws ParseException {

		String date= booking.getDate();
		int confRoomId= booking.getConfRoomId();
		int userId=booking.getUserId();
		int buildingId= booking.getBuildingId();
		int floorId= booking.getFloorId();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.ROOT);
		LocalDate dt = LocalDate.parse(date, formatter);

		if (!isValidDate(dt)) {
			throw new BookingStartException("Exception occured: New bookings starts from tomorrow.");
		}

		if (!isValidFutureDate(dt)) {
			throw new ConditionFailureException("Exception occured: Booking can only be made for 10 days in future.");
		}

		if (!isValidDuration(booking.getStartTime(),booking.getEndTime())) {
			throw new ConditionFailureException("Exception occured: Booking cannot be made for more than 12 hours.");
		}
		
		confRoomService.isConfRoomPresent(buildingId, floorId, confRoomId);
			
		
		Optional<ConfRoom> confRoom = confRoomRepo.findById(confRoomId); 

		userService.isUserPresent(userId);

		if (!confRoomService.isRoomAvailable(buildingId,floorId,confRoomId, date, booking.getStartTime(),booking.getEndTime())) {
			throw new ConditionFailureException("Exception occured: Sorry the required slot is already booked");
		}

		if (!confRoomService.isCapacitySufficient(confRoom.get(), capacity)) {
			throw new ConditionFailureException("Exception occured: Size is less than your requirements, please try a different room");
		}


		return bookingRepo.save(booking);

		
	}

	public Booking deleteBooking(int bookingId) {

		

		if (!isBookingPresent(bookingId))
			return null;
		
		Optional<Booking> booking = bookingRepo.findById(bookingId);

		bookingRepo.delete(booking.get());
		
		return booking.get();
		

	}

	
	public List<Booking> getBookingsByRoom(int buildingId, int floorId, int confRoomID, String date) { 
		
		confRoomService.isConfRoomPresent(buildingId,floorId,confRoomID);
		
		
		List<Booking> bookings=bookingRepo.findByBuildingIdAndFloorIdAndConfRoomIdAndDateOrderByStartTimeAsc(buildingId, floorId, confRoomID, date);
		
		if(bookings==null) {
			System.out.println("No Bookings for the day: " + date);
			return null;
		}
		
		 return bookings;
	}
	
	public List<Booking> getBookingsByUser(int userId) {
		
		
		userService.isUserPresent(userId);
		
		List<Booking> bookings= bookingRepo.findByUserId(userId);
		return bookings;
	}

}
