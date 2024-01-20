
package com.confRoom.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
	
	@Id
	@Column(name = "bookingId")
	@GeneratedValue
	private int bookingId;
	private int userId;
	private int confRoomId;
	
	private  String startTime;
	private String endTime;
	private String date;
	
	
	
	private int buildingId;
	private int floorId;
	
	
	
	public int getBookingId() {
		return this.bookingId;
	}
	

	public int getConfRoomId() {
		return this.confRoomId;
	}
	

	
	public int getUserId() {
		return this.userId;
	}
	
	
	public int getBuildingId() {
		return this.buildingId;
	}
	
	public int getFloorId() {
		return this.floorId;
	}
	
}
