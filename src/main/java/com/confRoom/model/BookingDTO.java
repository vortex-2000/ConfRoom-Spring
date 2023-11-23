package com.confRoom.model;

public class BookingDTO {
	
	public Booking booking;
	public int capacity;
	
	public BookingDTO(Booking booking,int capacity) {
		this.booking=booking;
		this.capacity=capacity;
	}
}
