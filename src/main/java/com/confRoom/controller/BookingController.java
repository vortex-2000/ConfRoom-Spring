package com.confRoom.controller;

import java.text.ParseException;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.confRoom.exception.BookingStartException;
import com.confRoom.exception.CustomExceptionResponse;
import com.confRoom.model.Booking;
import com.confRoom.model.BookingDTO;
import com.confRoom.service.IBookingService;

@RestController
public class BookingController {
	
	@Autowired
	private IBookingService bookingService;
	
	//LOCAL EXCEPTION HANDLER										//move this to common 
	@ExceptionHandler(value= BookingStartException.class)
	public CustomExceptionResponse handleBookingStartException(BookingStartException ex)
	{
		return new CustomExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
	
	
	
	@GetMapping("/bookings")
	public TreeSet<Booking> GetBooking(@RequestParam Optional<Integer> userIdInput,@RequestParam Optional<Integer> buildingIdInput,@RequestParam Optional<Integer> floorIdInput,@RequestParam Optional<Integer> confRoomIdInput,@RequestParam Optional<String> dateInput) throws ParseException
	{		
		int userId  = userIdInput.orElse(-1);
		if(userId==-1) {
			int buildingId  = buildingIdInput.orElse(-1);
			int floorId  = floorIdInput.orElse(-1);
			int confRoomId  = confRoomIdInput.orElse(-1);
			String date = dateInput.orElse("");
			return bookingService.getBookingsByRoom(buildingId, floorId,confRoomId,date);

		}
		return bookingService.getBookingsByUser(userId);
		
	}
	
	
	@PostMapping("/bookings")
	public Booking addBooking(@RequestBody BookingDTO booking) throws ParseException
	{		
		return bookingService.addBooking(booking.booking, booking.capacity);
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public Booking deleteBooking(@PathVariable int bookingId) throws ParseException
	{		
		return bookingService.deleteBooking(bookingId);
	}
}
