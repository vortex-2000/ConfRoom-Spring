package com.confRoom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.Building;
import com.confRoom.model.Floor;
import com.confRoom.repository.BuildingRepository;
import com.confRoom.repository.FloorRepository;

@Service
public class FloorServiceImpl implements FloorService {
	

	  
	  @Autowired
	  private BuildingRepository buildingRepo;
	  @Autowired
	  private FloorRepository floorRepo;
	  @Autowired
	  private BuildingService buildingService;
	  
	  
	  public Floor addFloor(int buildingId,Floor floor) {
	  
		  buildingService.isBuildingPresent(buildingId);
		  Optional<Building> building = buildingRepo.findById(buildingId);
		  building.get().setFloor(floor);
		  floorRepo.save(floor);
		  return floor;
	  }
	  
	  
	  public void isFloorPresent(int buildingId,int floorId) {
	  
		  buildingService.isBuildingPresent(buildingId);
	  
		  if(!floorRepo.existsById(floorId)) 
		  { 
			  throw new MissingEntityException("Exception occured: The requested floor is not present"); 
		  } 
		  return; 
	}
	 
	
}
