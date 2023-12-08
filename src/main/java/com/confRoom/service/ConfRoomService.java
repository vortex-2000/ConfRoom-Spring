package com.confRoom.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.confRoom.model.ConfRoom;
import com.confRoom.model.Floor;

@Service
public interface ConfRoomService {

	
	  public void isConfRoomPresent(int buildingId,int floorId, int confRoomId);
	  public ConfRoom addConfRoom(ConfRoom confRoom,int buildingId,int floorId);
	  public List<ConfRoom> getRoomsByRequirements(int buildingId, int floorId, String date, String startTime, String endTime, int capacity) throws ParseException; 
	  public List<ArrayList<ConfRoom> > getSuggestedRooms(int buildingId, int floorId, String date, String startTime, String endTime, int capacity, int days) throws ParseException ; 
	  public Boolean isRoomAvailable(int buildingId,int floorId,int confRoomId, String date, String startTimeInput, String endTimeInput) throws ParseException; 
	  public Boolean isCapacitySufficient(ConfRoom confRoom, int capacity);
	 
}
