package com.confRoom.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confRoom.model.Floor;
import com.confRoom.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
