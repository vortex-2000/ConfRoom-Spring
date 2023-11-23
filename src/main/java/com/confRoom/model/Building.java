package com.confRoom.model;

import java.util.*;




public class Building {
	
 private int buildingId;
	private String buildingName;
	private Map<Integer,Floor>floors;
	
	/*
	 * public Building(int buildingId,String buildingName,Map<Integer,Floor>floors){
	 * this.buildingId=(int)(Math.random()*100); this.buildingName=buildingName;
	 * this.floors= new HashMap<Integer,Floor>(); }
	 */

	
	public void setFloor(Floor floor) {
		
		if(this.floors==null)
			this.floors= new HashMap<Integer,Floor>();
		this.floors.put(floor.getFloorId(), floor);
	}
	
	public int getBuildingId() {
		return buildingId;
	}

	public Floor getFloor(int floorId) {
		return this.floors.get(floorId); 
	}
	
	public String getBuildingName() {
		return this.buildingName;
	}
	
	public void setBuildingId(int buildingId) {
		this.buildingId=buildingId;
	}
}
