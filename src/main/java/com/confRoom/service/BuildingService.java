package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.Building;
import com.confRoom.repository.BuildingRepository;
import com.confRoom.repository.IBuildingRepository;

@Service
@ComponentScan(basePackages = "com.confRoom.aspect")
public class BuildingService implements IBuildingService {
	
	private IBuildingRepository buildingRepo;
	
	public BuildingService()									//AUTOWIRE    // COMMENT OUT
	{
		buildingRepo= BuildingRepository.getInstance();
	}
	
	public Building addBuilding(Building building) {	
		building.setBuildingId((int)(Math.random()*100));
		return buildingRepo.addBuilding(building);								
		
	}
	
 	public void isBuildingPresent(int buildingId) {   
 		//System.out.println(buildingId);
 		if(!buildingRepo.getBuildings().containsKey(buildingId)) {
			throw new MissingEntityException("Exception occured: The requested building is not present");
 		}
 		return;
	}

}
