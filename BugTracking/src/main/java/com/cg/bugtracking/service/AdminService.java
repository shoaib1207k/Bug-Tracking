package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

public interface AdminService {

	public Admin createAdmin(Admin admin) throws NoSuchUserFoundException, NoAdminRoleFoundException;
	
	List<Admin> findAllAdmins();
	
	public Admin findAdminById(long id) throws NoSuchAdminFoundException;
	
	public Admin updateAdmin(long id, Admin admin) throws NoSuchAdminFoundException, NoAdminRoleFoundException;
	
	public boolean deleteAdmin(long id);
}
