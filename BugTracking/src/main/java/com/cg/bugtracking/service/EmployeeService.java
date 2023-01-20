package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;


public interface EmployeeService {
	
	EmployeeDTO createEmployee(Employee emp);
	EmployeeDTO getEmployeeById(long empId);
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO updateEmployee(long id, Employee e);
	EmployeeDTO deleteEmployee(long id);
	
}
