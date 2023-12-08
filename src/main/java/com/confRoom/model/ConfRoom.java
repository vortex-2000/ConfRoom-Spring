package com.confRoom.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ConfRoom")
public class ConfRoom {
	
	

	@Id
	@Column(name = "confRoomId")
	@GeneratedValue
	private int id;
	
	@OneToMany(targetEntity=Booking.class)
	@JoinColumn(name = "confRoomId")
	private List<Booking> bookSlot; //tree=sorted
	private int maxCapacity;
	private String confRoomName; //private


	
	private int floorId;

	
	public int getConfRoomId() {
		return this.id;
	}

	
	public List<Booking> getSlots(){
		return this.bookSlot;
	}
	
	public int getMaxCapacity() {
		return this.maxCapacity;
	}
	
	
	
	
	public String getConfRoomName() {
	 return this.confRoomName;
	}
	
	public void setConfRoomId(int confRoomId) {
		this.id=confRoomId;
	}

	public void setBookSlot() {
		this.bookSlot= new ArrayList<Booking>();
	}
	
}
