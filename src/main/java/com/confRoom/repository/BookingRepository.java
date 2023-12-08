package com.confRoom.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confRoom.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{			///////////////////////////////JPA POWER
	
	//public Map<Integer,Booking> getBookings();
	
	//public Booking getBookingById(int bookingId);
	
	
	//public Booking addBooking(Booking booking);
	//public Booking deleteBooking(Booking booking);
	//public TreeSet<Booking> getBookingsByUser(int userId);
	
	public List<Booking> findByUserId(int userId);
	public List<Booking> findByBuildingIdAndFloorIdAndConfRoomIdAndDateOrderByStartTimeAsc(int buildingId, int floorId, int confRoomID, String date);
	//public TreeSet<Booking> getBookingsByRoom(int buildingId, int floorId, int confRoomID, String date);
	
}
