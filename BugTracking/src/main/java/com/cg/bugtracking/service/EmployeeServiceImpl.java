package com.cg.bugtracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bugtracking.dao.EmployeeRepository;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public EmployeeDTO createEmployee(Employee emp) {
		return this.convertEntityToDTO(empRepo.save(emp));
	}

	@Override
	public EmployeeDTO getEmployeeById(long empId) {

		return null;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {

		return null;
	}

	@Override
	public EmployeeDTO updateEmployee(long id, Employee e) {

		return null;
	}

	@Override
	public EmployeeDTO deleteEmployee(long id) {

		return null;
	}
	
	private EmployeeDTO convertEntityToDTO(Employee emp) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmpId(emp.getEmpId());
		employeeDTO.setEmail(emp.getEmail());
		employeeDTO.setEmpName(emp.getEmpName());
		employeeDTO.setContact(emp.getContact());
		employeeDTO.setProjectList(emp.getProjectList());

		return employeeDTO;
	}

}
