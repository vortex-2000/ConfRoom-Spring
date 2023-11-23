
package com.confRoom.model;

public class Booking {
	
	private int bookingId;
	private int userId;
	private int confRoomId;
	private Slot slot;
	private String date;
	private int buildingId;
	private int floorId;
	
	//BUILDER PATTERN...
	public Booking(int userId,int buildingId,int floorId,int confRoomId, String date, Slot slot) {
		this.bookingId=(int)(Math.random()*1000);
		this.userId=userId;
		this.confRoomId=confRoomId;
		this.slot=slot;
		this.date=date;
		this.buildingId=buildingId;
		this.floorId=floorId;
	}
	
	public int getBookingId() {
		return this.bookingId;
	}
	
	public Slot getSlot() {
		return this.slot;
	}
	
	public int getConfRoomId() {
		return this.confRoomId;
	}
	

	
	public int getUserId() {
		return this.userId;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getBuildingId() {
		return this.buildingId;
	}
	
	public int getFloorId() {
		return this.floorId;
	}
	
}
