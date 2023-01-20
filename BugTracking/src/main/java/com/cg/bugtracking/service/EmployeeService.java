package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.Employee;


public interface EmployeeService {
	
	Employee createEmployee(Employee e);
	Employee getEmployeeById(long empId);
	List<Employee> getAllEmployees();
	Employee updateEmployee(long id, Employee e);
	Employee deleteEmployee(long id);
	
}
