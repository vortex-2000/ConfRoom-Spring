package com.confRoom.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confRoom.model.Floor;
import com.confRoom.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String username);
}
