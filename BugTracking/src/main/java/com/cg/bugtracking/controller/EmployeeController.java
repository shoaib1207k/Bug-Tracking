package com.cg.bugtracking.controller;

import java.util.List;

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
import com.cg.bugtracking.service.EmployeeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee emp){
		return new ResponseEntity<>(empService.createEmployee(emp), HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long id) throws NoSuchEmployeeFoundException{
		return new ResponseEntity<>(empService.getEmployeeById(id), HttpStatus.FOUND);
	}
	
	@PostMapping("/update-employee/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id")long id, @RequestBody Employee emp) throws NoSuchEmployeeFoundException{
		return new ResponseEntity<>(empService.updateEmployee(id, emp), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("id")long id) throws NoSuchEmployeeFoundException{
		return new ResponseEntity<>(empService.deleteEmployee(id), HttpStatus.OK);
	}
}
