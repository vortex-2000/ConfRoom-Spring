package com.confRoom.repository;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.confRoom.model.ConfRoom;
import com.confRoom.model.Floor;
import com.confRoom.model.Slot;

@Repository  
public class ConfRoomRepository implements IConfRoomRepository{
	
	private IBuildingRepository buildingRepo;

	public ConfRoomRepository() {
		buildingRepo= BuildingRepository.getInstance();
	}
	
	
	public ConfRoom getConfRoomById(int buildingId,int floorId,int confRoomId)
	{	
		return buildingRepo.getBuildings().get(buildingId).getFloor(floorId).getConfRoom(confRoomId);
	}
	
	
	public ConfRoom addConfRoom(ConfRoom confRoom,Floor floor) {
		
		floor.setConfRoom(confRoom);
		confRoom.setBookSlot();
		return confRoom;  
	}
	
	public  Map<Integer, ConfRoom> getConfRooms(Floor floor)
	{
		return floor.getConfRooms();
	}
	
	public TreeSet<Slot> getBookedSlotsByDate(ConfRoom confRoom,String date){
		
		return confRoom.getSlots().get(date);
	}
	
}
