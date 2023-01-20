package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;


public interface EmployeeService {
	
	EmployeeDTO createEmployee(Employee emp);
	EmployeeDTO getEmployeeById(long empId) throws NoSuchEmployeeFoundException;
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO updateEmployee(long id, Employee e) throws NoSuchEmployeeFoundException;
	EmployeeDTO deleteEmployee(long id) throws NoSuchEmployeeFoundException;
	
}
