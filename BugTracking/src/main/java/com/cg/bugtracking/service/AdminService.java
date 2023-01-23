package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;

public interface AdminService {

	public AdminDTO createAdmin(AdminDTO adminDto) throws NoSuchUserFoundException, NoAdminRoleFoundException;

	List<AdminDTO> findAllAdmins();

	public AdminDTO findAdminById(long id) throws NoSuchAdminFoundException;

	public AdminDTO updateAdmin(long id, AdminDTO adminDto) throws NoSuchAdminFoundException, NoAdminRoleFoundException;

	public AdminDTO deleteAdmin(long id) throws NoSuchAdminFoundException;

	// employee

	EmployeeDTO createEmployee(EmployeeDTO empDTO);

	// project

	public ProjectDTO createProject(ProjectDTO prjDTO);

	// bug

	public BugDTO createBug(BugDTO bugDTO);
}
