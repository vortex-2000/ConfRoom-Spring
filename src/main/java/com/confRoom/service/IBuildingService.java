package com.confRoom.service;
import com.confRoom.model.Building;

public interface IBuildingService {
	public Building addBuilding(Building building);
	public void isBuildingPresent(int buildingId);
	
}
