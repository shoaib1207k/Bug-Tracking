package com.cg.bugtracking.service;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;

public interface AdminService {

	public UserDTO createUser(User user);
	public AdminDTO createAdmin(Admin admin);
}
