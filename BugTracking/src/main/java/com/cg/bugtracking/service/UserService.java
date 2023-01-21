package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.User;

public interface UserService {

	public User createUser(User user);
	
	List<User> findAllUsers();
}
