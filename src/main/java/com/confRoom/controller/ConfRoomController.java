package com.confRoom.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.confRoom.model.ConfRoom;

import com.confRoom.service.ConfRoomService;




@RestController
public class ConfRoomController {
	

	@Autowired
	private ConfRoomService confRoomService;
	
	@PostMapping("/buildings/{buildingId}/floors/{floorId}/conf-rooms")
	public ConfRoom addConfRoom(@RequestBody ConfRoom confRoom,@PathVariable int buildingId,@PathVariable int floorId)
	{	
		return confRoomService.addConfRoom(confRoom,buildingId,floorId);
	}
	
	
	//RETURN FORAMT
	@GetMapping("/buildings/{buildingId}/floors/{floorId}/conf-rooms")
	public List<ArrayList<ConfRoom> > getSuggestedRooms(@PathVariable int buildingId,@PathVariable int floorId,@RequestParam String date,@RequestParam int capacity,@RequestParam String startTime,@RequestParam String endTime,@RequestParam Optional<Integer> daysInput) throws ParseException {
		
		int days  = daysInput.orElse(-1);
		if(days!=-1)
			return confRoomService.getSuggestedRooms(buildingId, floorId, date,startTime,endTime,capacity,days);
	
		List<ConfRoom> arr= confRoomService.getRoomsByRequirements(buildingId, floorId, date,startTime,endTime,capacity);		
		List<ArrayList<ConfRoom> > result = new ArrayList<ArrayList<ConfRoom> >();
		result.add((ArrayList<ConfRoom>) arr);
		return result;
	}
}
