package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;


public interface EmployeeService {
	
	Employee createEmployee(Employee emp);
	Employee getEmployeeById(long empId) throws NoSuchEmployeeFoundException;
	List<Employee> getAllEmployees();
	Employee updateEmployee(long id, Employee emp) throws NoSuchEmployeeFoundException, NoSuchProjectFoundException;
	Employee deleteEmployee(long id) throws NoSuchEmployeeFoundException;
	
}
