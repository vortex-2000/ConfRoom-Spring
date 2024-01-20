package com.confRoom.model;

import java.util.*;

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
@Table(name = "Building")
public class Building {
	
	@Id
	@Column(name = "buildingId")
	@GeneratedValue
	private int id;
	private String buildingName;
	//private Map<Integer,Floor>floors;
	
	@OneToMany(targetEntity=Floor.class)
	@JoinColumn(name = "buildingId")
    private List<Floor>floors;  

	
	
	/*
	 * public Building(int buildingId,String buildingName,Map<Integer,Floor>floors){
	 * this.buildingName=buildingName; this.floors= new HashMap<Integer,Floor>(); }
	 */
	 

	
	public void setFloor(Floor floor) {
		
		if(this.floors==null) this.floors= new ArrayList<Floor>();
		this.floors.add(floor);
		 
	}
	
	public int getBuildingId() {
		return id;
	}

	public Floor getFloor(int floorId) {
		return this.floors.get(floorId); 
		
	}
	
	public String getBuildingName() {
		return this.buildingName;
	}
	
	public void setBuildingId(int buildingId) {
		this.id=buildingId;
	}
}
