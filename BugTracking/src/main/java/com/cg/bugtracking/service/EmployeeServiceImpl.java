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

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO empDTO) {
		Employee emp = modelMapper.map(empDTO, Employee.class);
		empRepo.save(emp);
		return empDTO;
	}

	@Override
	public EmployeeDTO getEmployeeById(long empId) throws NoSuchEmployeeFoundException {
		Optional<Employee> emp = empRepo.findById(empId);
		if(emp.isPresent())
			return modelMapper.map(emp.get(),EmployeeDTO.class);
		throw new NoSuchEmployeeFoundException("No Such employee found");
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() { 
		return empRepo.findAll()
				.stream().map(emp->modelMapper.map(emp, EmployeeDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public EmployeeDTO updateEmployee(long id, EmployeeDTO empDTO) throws NoSuchEmployeeFoundException{
		Optional<Employee> empToUpdate = empRepo.findById(id);
		Employee emp = modelMapper.map(empDTO, Employee.class);

		if(empToUpdate.isPresent()) {
			
			empToUpdate.get().setEmpName(emp.getEmpName());
			empToUpdate.get().setEmail(emp.getEmail());
			empToUpdate.get().setContact(emp.getContact());
			empToUpdate.get().setProjId(emp.getProjId());
//		empToUpdate.setProjectList(emp.getProjectList());
			empRepo.save(empToUpdate.get());
			return empDTO;
		}else {
			throw new NoSuchEmployeeFoundException("No employee with this id");
		}
	}

	@Override
	public EmployeeDTO deleteEmployee(long id) throws NoSuchEmployeeFoundException{
		Optional<Employee> empToDel = empRepo.findById(id);
		if(empToDel.isPresent()) 
			empRepo.delete(empToDel.get());	
		else
			throw new NoSuchEmployeeFoundException("No employee with this id");
		return modelMapper.map(empToDel.get(), EmployeeDTO.class);
	}
	

}
