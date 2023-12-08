package com.confRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confRoom.model.Building;
import com.confRoom.model.Floor;

public interface FloorRepository extends JpaRepository<Floor, Integer>{
	
}
