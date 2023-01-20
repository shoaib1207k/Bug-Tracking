package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.EmployeeRepository;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;


	@Override
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}

	@Override
	public Employee getEmployeeById(long empId) throws NoSuchEmployeeFoundException {
		Optional<Employee> emp = empRepo.findById(empId);
		if(emp.isPresent())
			return emp.get();
		throw new NoSuchEmployeeFoundException("No Such employee found");
	}

	@Override
	public List<Employee> getAllEmployees() { 
		return empRepo.findAll();

	}

	@Override
	public Employee updateEmployee(long id, Employee emp) throws NoSuchEmployeeFoundException{
		Employee empToUpdate = this.getEmployeeById(id);
		empToUpdate.setEmpId(emp.getEmpId());
		empToUpdate.setEmpName(emp.getEmpName());
		empToUpdate.setEmail(emp.getEmail());
		empToUpdate.setContact(emp.getContact());
		empToUpdate.setProjectList(emp.getProjectList());
		return empRepo.save(empToUpdate);
	}

	@Override
	public Employee deleteEmployee(long id) throws NoSuchEmployeeFoundException{
		Optional<Employee> empToDel = empRepo.findById(id);
		if(empToDel.isPresent()) 
			empRepo.delete(empToDel.get());	
		else
			throw new NoSuchEmployeeFoundException("No employee with this id");
		return empToDel.get();
	}
	

}
