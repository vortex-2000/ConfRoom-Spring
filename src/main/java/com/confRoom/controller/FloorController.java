package com.confRoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.confRoom.model.Floor;
import com.confRoom.service.*;





@RestController
public class FloorController {
	


	@Autowired
	private IFloorService floorService;
	
	@PostMapping("/buildings/{buildingId}/floors") 
	public Floor addFloor(@RequestBody Floor floor,@PathVariable int buildingId) throws Exception
	{	
		Floor resultFloor= floorService.addFloor(buildingId,floor);
		return resultFloor;
	}
	
	
}
