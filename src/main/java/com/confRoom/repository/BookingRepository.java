package com.confRoom.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.confRoom.model.*;
import com.confRoom.model.ConfRoom.slotComparator;


@Repository  
public class BookingRepository implements IBookingRepository{
	
	
	
	private Map<Integer,Booking> bookings=new HashMap<Integer,Booking>();
	private IConfRoomRepository confRoomRepo;

	
	private static BookingRepository BookingRepository_instance = null;
	
		private BookingRepository() {
			bookings=new HashMap<Integer,Booking>();
			confRoomRepo=new ConfRoomRepository();
			
		}
	
	 public static synchronized BookingRepository getInstance() 
	    { 
	        if (BookingRepository_instance == null) 
	        	BookingRepository_instance = new BookingRepository(); 
	  
	        return BookingRepository_instance; 
	    } 
	
	
	
	public class bookingComparator implements Comparator<Booking>{

		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		java.util.Date date1;
		java.util.Date date2;
		public int compare(Booking b1, Booking b2) {
			
			Slot s1= b1.getSlot();
			Slot s2= b2.getSlot();
			
			Date Dateb1 = null;
			try {
				Dateb1 = new SimpleDateFormat("yyyy-MM-dd").parse(b1.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			Date Dateb2 = null;
			try {
				Dateb2 = new SimpleDateFormat("yyyy-MM-dd").parse(b2.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if(Dateb1==null || Dateb2==null)
				return 0;
			
			if(Dateb1.equals(Dateb2)) {
				try {
					date1 =  parser.parse(s1.getSlotStartTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					date2 = parser.parse(s2.getSlotStartTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return (int) (date1.getTime() - date2.getTime());
			}
			
			return Dateb1.compareTo(Dateb2);		
			
		}
	}

	 
	
	public Booking addBooking(Booking booking) { 
		ConfRoom confRoom = confRoomRepo.getConfRoomById(booking.getBuildingId(), booking.getFloorId(), booking.getConfRoomId()); 
		
		bookings.put(booking.getBookingId(), booking);
		
		confRoom.setBooking(booking);
		
		return booking;

	}
	
	public Booking deleteBooking(Booking booking) { 
		
		bookings.remove(booking.getBookingId());
		ConfRoom confRoom = confRoomRepo.getConfRoomById(booking.getBuildingId(), booking.getFloorId(), booking.getConfRoomId()); 
		confRoom.unsetBooking(booking);
		
		return booking;
		
	}
	
	public TreeSet<Booking> getBookingsByRoom(int buildingId,int floorId,int confRoomId,String date)
	{
		TreeSet<Booking>bookings = new TreeSet<Booking>(new bookingComparator());
		Iterator<Entry<Integer, Booking>> hmIterator = this.bookings.entrySet().iterator();
		
		while (hmIterator.hasNext()) {
			Entry<Integer, Booking> mapElement = hmIterator.next();
			Booking booking = mapElement.getValue();
			//ConfRoom confRoom = buildingRepo.getBuildings().get(booking.getBookingId()).getFloor(booking.getFloorId()).getConfRoom(booking.getConfRoomId());
			//ConfRoom confRoom = confRoomRepo.getConfRoomById(buildingId, floorId, confRoomId); 

			if(booking.getBuildingId()==buildingId && booking.getFloorId()==floorId && booking.getConfRoomId()==confRoomId && booking.getDate().equals(date))
				bookings.add(booking);
		}
		
		
		return bookings;
	}
	
	public TreeSet<Booking> getBookingsByUser(int userId) {
	
		
		
		
		TreeSet<Booking>bookings = new TreeSet<Booking>(new bookingComparator());
		Iterator<Entry<Integer, Booking>> hmIterator = this.bookings.entrySet().iterator();
		
		while (hmIterator.hasNext()) {
			Entry<Integer, Booking> mapElement = hmIterator.next();
			Booking booking = mapElement.getValue();
			
			if(booking.getUserId()==userId)
				bookings.add(booking);
			
		}
		
		 return bookings;
	}
	
	public Booking getBookingById(int bookingId) {
		
		return this.bookings.get(bookingId);
	}
	
	public Map<Integer,Booking> getBookings(){
		return this.bookings;
	}
	
	
	
}
