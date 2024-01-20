package com.confRoom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.jwtUtils.WebSecurityConfig;
import com.confRoom.model.User;
import com.confRoom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired	
	private UserRepository userRepo;
	@Autowired	
	private WebSecurityConfig wc;
	
	
	
	public User addUser(User user) {
		//user.setUserId((int)(Math.random()*1000));
		
		PasswordEncoder encoder = wc.passwordEncoder();;
		String result = encoder.encode(user.getPassword());
		user.setPassword(result);
		userRepo.save(user);
		return user;
	}
	
	
	public void isUserPresent(int userId) {
		
		if(!userRepo.existsById(userId))
		{
			throw new MissingEntityException("Exception occured: The requested user is not present");
		}
		return;
	}
	
}
