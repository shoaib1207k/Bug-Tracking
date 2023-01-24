package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

public interface EmployeeService {

	EmployeeDTO createEmployee(EmployeeDTO empDTO, long adminId) throws NoAdminRoleFoundException, NoSuchUserFoundException, NotAdminException;

	EmployeeDTO getEmployeeById(long empId, long adminId) throws NoSuchEmployeeFoundException, NoAdminRoleFoundException;

	List<EmployeeDTO> getAllEmployees(long adminId) throws NoAdminRoleFoundException;

	EmployeeDTO updateEmployee(long empId, EmployeeDTO empDTO, long adminId) throws NoSuchEmployeeFoundException,NoAdminRoleFoundException;

	EmployeeDTO deleteEmployee(long empId, long adminId) throws NoSuchEmployeeFoundException, NoAdminRoleFoundException;
	
	List<EmployeeDTO> getEmployeeByProjectId(long adminId, long projId) throws NoAdminRoleFoundException;
}
