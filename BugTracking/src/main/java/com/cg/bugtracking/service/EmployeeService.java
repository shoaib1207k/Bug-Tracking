package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;


public interface EmployeeService {
	
	EmployeeDTO createEmployee(EmployeeDTO empDTO);
	EmployeeDTO getEmployeeById(long empId) throws NoSuchEmployeeFoundException;
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO updateEmployee(long id, EmployeeDTO empDTO) throws NoSuchEmployeeFoundException,NoSuchProjectFoundException;
	EmployeeDTO deleteEmployee(long id) throws NoSuchEmployeeFoundException;
	
}