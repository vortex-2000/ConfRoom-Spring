package com.confRoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.confRoom.model.User;
import com.confRoom.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user)
	{		
		return userService.addUser(user);
	}
	
}
