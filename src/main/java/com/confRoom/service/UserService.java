package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.User;
import com.confRoom.repository.IUserRepository;
import com.confRoom.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	private IUserRepository userRepo; 
	
	public UserService() {
		userRepo= UserRepository.getInstance(); 
	}
	
	public User addUser(User user) {
		user.setUserId((int)(Math.random()*1000));
		return userRepo.addUser(user);
	}
	
	public void isUserPresent(int userId) {
		
		if(!userRepo.getUsers().containsKey(userId))
		{
			throw new MissingEntityException("Exception occured: The requested user is not present");
		}
		return;
	}
	
}
