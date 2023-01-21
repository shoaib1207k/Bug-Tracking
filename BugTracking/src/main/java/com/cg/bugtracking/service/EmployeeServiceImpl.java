package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.EmployeeRepository;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ProjectService prjService;

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
	public Employee updateEmployee(long id, Employee emp) throws NoSuchEmployeeFoundException, NoSuchProjectFoundException{
		Optional<Employee> empToUpdate = empRepo.findById(id);
		
		if(empToUpdate.isPresent() && prjService.getProjectById(emp.getProjId())!=null) {
			
			empToUpdate.get().setEmpName(emp.getEmpName());
			empToUpdate.get().setEmail(emp.getEmail());
			empToUpdate.get().setContact(emp.getContact());
			empToUpdate.get().setProjId(emp.getProjId());
			return empRepo.save(empToUpdate.get());
		}else {
			throw new NoSuchEmployeeFoundException("No employee with this id");
		}
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
