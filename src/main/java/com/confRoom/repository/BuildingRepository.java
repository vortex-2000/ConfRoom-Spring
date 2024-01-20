package com.confRoom.repository;

import java.util.Map;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confRoom.model.Booking;
import com.confRoom.model.Building;

@Repository 
public interface BuildingRepository extends JpaRepository<Building, Integer>{
	
}
 