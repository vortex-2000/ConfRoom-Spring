package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.Building;
import com.confRoom.model.Floor;
import com.confRoom.repository.BuildingRepository;
import com.confRoom.repository.FloorRepository;
import com.confRoom.repository.IBuildingRepository;
import com.confRoom.repository.IFloorRepository;

@Service
public class FloorService implements IFloorService {
	

	private IBuildingRepository buildingRepo;
	private IFloorRepository floorRepo;
	private IBuildingService buildingService;
	
	public FloorService() {
		buildingRepo= BuildingRepository.getInstance();
		floorRepo= new FloorRepository();
		buildingService = new BuildingService();
	}
	
	public Floor addFloor(int buildingId,Floor floor) {
		
		
		buildingService.isBuildingPresent(buildingId);
		
		Building building = buildingRepo.getBuildingById(buildingId);
		
		floor.setFloorId((int)(Math.random()*100));
		return floorRepo.addFloor(building, floor);
	}
	
	public void isFloorPresent(int buildingId,int floorId) {
		
		buildingService.isBuildingPresent(buildingId);
		
		if(floorRepo.getFloorById(buildingId, floorId)==null) {
			throw new MissingEntityException("Exception occured: The requested floor is not present");
		}
		return;
	}
	
}
