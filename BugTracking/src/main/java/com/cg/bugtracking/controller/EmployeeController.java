package com.cg.bugtracking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.service.EmployeeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO empDTO){
		Employee emp = modelMapper.map(empDTO, Employee.class);
		Employee empDB = empService.createEmployee(emp);
		return new ResponseEntity<>(modelMapper.map(empDB, EmployeeDTO.class), HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		List<Employee> empList = empService.getAllEmployees();
		List<EmployeeDTO> empDTOList = empList.stream()
										.map(emp->modelMapper.map(emp, EmployeeDTO.class))
										.collect(Collectors.toList());
		
		return new ResponseEntity<>(empDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long id) throws NoSuchEmployeeFoundException{
		Employee emp = empService.getEmployeeById(id);
		EmployeeDTO empDTO = modelMapper.map(emp, EmployeeDTO.class);
		return new ResponseEntity<>(empDTO, HttpStatus.FOUND);
	}
	
	@PostMapping("/update-employee/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id")long id, @RequestBody EmployeeDTO empDTO) throws NoSuchEmployeeFoundException, NoSuchProjectFoundException{
		Employee empToUpdate = modelMapper.map(empDTO, Employee.class);
		empService.updateEmployee(id, empToUpdate);
		return new ResponseEntity<>(empDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("id")long id) throws NoSuchEmployeeFoundException{
		Employee emp = empService.deleteEmployee(id);
		EmployeeDTO empDTO = modelMapper.map(emp, EmployeeDTO.class);
		return new ResponseEntity<>(empDTO, HttpStatus.OK);
	}
}
