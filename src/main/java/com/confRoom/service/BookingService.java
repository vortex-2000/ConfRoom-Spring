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
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {

	private IBookingRepository bookingRepo;
	private IConfRoomRepository confRoomRepo;	
	private IConfRoomService confRoomService; 
	private IUserService userService;
    
    
    public BookingService() {
    	bookingRepo = BookingRepository.getInstance();
    	confRoomRepo = new ConfRoomRepository();   	
    	confRoomService = new ConfRoomService(); 
    	userService = new UserService();
    }
	

	private Boolean isValidDuration(Slot slot) throws ParseException {

		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		Date currStartTime = parser.parse(slot.getSlotStartTime());
		Date currEndTime = parser.parse(slot.getSlotEndTime());

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
				
			if(!bookingRepo.getBookings().containsKey(bookingId)) {
				System.out.println("The mentioned booking dosen't exists");
				return false;
			}
			return true;
		}
	 
	@SuppressWarnings("deprecation")
	public Booking addBooking(Booking booking,int capacity) throws ParseException {

		String date= booking.getDate();
		Slot slot= booking.getSlot();
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

		if (!isValidDuration(slot)) {
			throw new ConditionFailureException("Exception occured: Booking cannot be made for more than 12 hours.");
		}
		
		confRoomService.isConfRoomPresent(buildingId, floorId, confRoomId);
			
		
		ConfRoom confRoom = confRoomRepo.getConfRoomById(buildingId, floorId, confRoomId); 

		userService.isUserPresent(userId);

		if (!confRoomService.isRoomAvailable(confRoom, date, slot)) {
			throw new ConditionFailureException("Exception occured: Sorry the required slot is already booked");
		}

		if (!confRoomService.isCapacitySufficient(confRoom, capacity)) {
			throw new ConditionFailureException("Exception occured: Size is less than your requirements, please try a different room");
		}


		return bookingRepo.addBooking(booking);

		
	}

	public Booking deleteBooking(int bookingId) {

		

		if (!isBookingPresent(bookingId))
			return null;
		
		Booking booking = bookingRepo.getBookingById(bookingId);

		return bookingRepo.deleteBooking(booking);
		

	}

	
	public TreeSet<Booking> getBookingsByRoom(int buildingId, int floorId, int confRoomID, String date) { 
		
		confRoomService.isConfRoomPresent(buildingId,floorId,confRoomID);
		
		
		TreeSet<Booking> bookings=bookingRepo.getBookingsByRoom(buildingId, floorId, confRoomID, date);
		
		if(bookings==null) {
			System.out.println("No Bookings for the day: " + date);
			return null;
		}
		
		 return bookings;
	}
	
	public TreeSet<Booking> getBookingsByUser(int userId) {
		
		
		userService.isUserPresent(userId);
		
		TreeSet<Booking> bookings= bookingRepo.getBookingsByUser(userId);
		return bookings;
	}
	

}
