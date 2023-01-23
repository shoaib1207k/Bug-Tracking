package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

public interface AdminService {

	// create (admin, employee, project)

	public AdminDTO createAdmin(AdminDTO adminDto) throws NoSuchUserFoundException, NoAdminRoleFoundException;

	EmployeeDTO createEmployee(EmployeeDTO empDTO);

	public ProjectDTO createProject(ProjectDTO prjDTO);

	// admin CRUD

	List<AdminDTO> findAllAdmins();

	public AdminDTO findAdminById(long id) throws NoSuchAdminFoundException;

	public AdminDTO updateAdmin(long id, AdminDTO adminDto) throws NoSuchAdminFoundException, NoAdminRoleFoundException;

	public AdminDTO deleteAdmin(long id) throws NoSuchAdminFoundException;

}
