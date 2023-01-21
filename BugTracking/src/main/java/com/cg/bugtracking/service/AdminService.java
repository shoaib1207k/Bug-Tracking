package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin);
	
	List<Admin> findAllAdmins();
}
