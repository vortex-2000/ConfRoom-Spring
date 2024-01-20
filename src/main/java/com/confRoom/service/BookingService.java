package com.confRoom.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.confRoom.model.Booking;
import com.confRoom.model.ConfRoom;


public interface BookingService {
	
	public Booking addBooking(Booking booking, int capacity) throws ParseException;
	public Booking deleteBooking(int bookingId); 
	public List<Booking> getBookingsByRoom(int buildingId, int floorId, int confRoomID, String date);
	public List<Booking> getBookingsByUser(int userId);
	public Boolean isBookingPresent(int bookingId); 
}
