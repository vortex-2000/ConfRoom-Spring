package com.confRoom.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.confRoom.model.BookingDTO;
import com.confRoom.model.ConfRoom;
import com.confRoom.model.Slot;
import com.confRoom.service.IConfRoomService;


@RestController
public class ConfRoomController {
	

	@Autowired
	private IConfRoomService confRoomService;
	
	@PostMapping("/buildings/{buildingId}/floors/{floorId}/conf-rooms")
	public ConfRoom addConfRoom(@RequestBody ConfRoom confRoom,@PathVariable int buildingId,@PathVariable int floorId)
	{	
		return confRoomService.addConfRoom(confRoom,buildingId,floorId);
	}
	
	
	//RETURN FORAMT
	@GetMapping("/buildings/{buildingId}/floors/{floorId}/conf-rooms")
	public ArrayList<ArrayList<ConfRoom> > getSuggestedRooms(@PathVariable int buildingId,@PathVariable int floorId,@RequestParam String date,@RequestParam int capacity,@RequestParam String startTime,@RequestParam String endTime,@RequestParam Optional<Integer> daysInput) throws ParseException {
		
		int days  = daysInput.orElse(-1);
		Slot slot = new Slot(startTime,endTime);
		if(days!=-1)
			return confRoomService.getSuggestedRooms(buildingId, floorId, date,slot,capacity,days);
		
		ArrayList<ConfRoom> arr= confRoomService.getRoomsByRequirements(buildingId, floorId, date,slot,capacity);		
		ArrayList<ArrayList<ConfRoom> > result = new ArrayList<ArrayList<ConfRoom> >();
		result.add(arr);
		return result;
	}
}
