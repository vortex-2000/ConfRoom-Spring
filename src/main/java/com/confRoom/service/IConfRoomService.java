package com.confRoom.service;

import java.text.ParseException;
import java.util.ArrayList;

import com.confRoom.model.ConfRoom;
import com.confRoom.model.Floor;
import com.confRoom.model.Slot;

public interface IConfRoomService {

	
	public void isConfRoomPresent(int buildingId,int floorId, int confRoomId);
	public ConfRoom addConfRoom(ConfRoom confRoom,int buildingId,int floorId);
	public ArrayList<ConfRoom> getRoomsByRequirements(int buildingId, int floorId, String date,  Slot slot, int capacity) throws ParseException;
	public ArrayList<ArrayList<ConfRoom> > getSuggestedRooms(int buildingId, int floorId, String date,  Slot slot, int capacity, int days) throws ParseException ;
	public Boolean isRoomAvailable(ConfRoom confRoom, String date, Slot slot) throws ParseException;
	public  Boolean isCapacitySufficient(ConfRoom confRoom, int capacity); 
}
