package com.confRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confRoom.model.Building;
import com.confRoom.model.Floor;
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>{
	
}
