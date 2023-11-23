package com.confRoom.service;

import com.confRoom.model.Floor;

public interface IFloorService {
	
	public Floor addFloor(int buildingId,Floor floor);
	public void isFloorPresent(int buildingId,int floorId);
}
