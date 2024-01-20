package com.confRoom.service;

import com.confRoom.model.User;

public interface UserService {
	
	public User addUser(User user);
	public void isUserPresent(int userId);
}
