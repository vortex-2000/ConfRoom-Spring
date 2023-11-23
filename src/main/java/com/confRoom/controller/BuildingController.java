package com.confRoom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.confRoom.model.Building;
import com.confRoom.service.BuildingService;



@RestController
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;
	
	Logger logger = LoggerFactory.getLogger(BuildingController.class);
	
	@PostMapping("/buildings")
	public Building addBuilding(@RequestBody Building building)
	{		
		return buildingService.addBuilding(building);
	}
	
	
}
