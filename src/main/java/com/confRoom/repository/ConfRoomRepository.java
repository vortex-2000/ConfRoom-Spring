package com.confRoom.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confRoom.model.Building;
import com.confRoom.model.ConfRoom;
import com.confRoom.model.Floor;

@Repository
public interface ConfRoomRepository extends JpaRepository<ConfRoom, Integer>{
	
	
	//public TreeSet<Slot> getBookedSlotsByDate(ConfRoom confRoom,String date);	
}
