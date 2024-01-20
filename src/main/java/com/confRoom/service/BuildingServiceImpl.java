package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.Building;
import com.confRoom.repository.BuildingRepository;

@Service
@ComponentScan(basePackages = "com.confRoom.aspect")
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepo;
	
	
	public Building addBuilding(Building building) {	
		return buildingRepo.save(building);
	}
	


	
 	public void isBuildingPresent(int buildingId) {   
 		System.out.println(buildingId);
 		if(!buildingRepo.existsById(buildingId)) {
			throw new MissingEntityException("Exception occured: The requested building is not present");
 		}
 		return;
	}

}
