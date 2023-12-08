package com.confRoom.model;
import java.util.*;

import javax.persistence.CascadeType;
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
@Table(name = "Floor")
public class Floor {
	@Id
	@Column(name = "floorId")
	@GeneratedValue
	private int id;
	private String floorName;
	
	@OneToMany(targetEntity=ConfRoom.class)
	@JoinColumn(name = "floorId")
	private List<ConfRoom> confRooms;
	
	
	public void setConfRoom(ConfRoom confRoom) {
		
		this.confRooms.add(confRoom);
	}
	
	public ConfRoom getConfRoom(int confRoomId) {
		return this.confRooms.get(confRoomId);
	}
	
	public int getFloorId() {
		return this.id;
	}
	
	public String getFloorName() {
		return this.floorName;
	}
	
	public List<ConfRoom> getConfRooms(){
		return this.confRooms;
	}
	
	public void setFloorId(int floorId) {
		this.id=floorId;
	}
}
