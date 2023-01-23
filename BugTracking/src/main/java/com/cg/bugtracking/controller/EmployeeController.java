package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.EmployeeService;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO empDTO) throws NoAdminRoleFoundException, NoSuchUserFoundException{
		return new ResponseEntity<>(empService.createEmployee(empDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long id) throws NoSuchEmployeeFoundException{
		return new ResponseEntity<>(empService.getEmployeeById(id), HttpStatus.FOUND);
	}
	
	@PostMapping("/employee/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id")long id, @Valid @RequestBody EmployeeDTO empDTO) throws NoSuchEmployeeFoundException, NoSuchProjectFoundException{
		return new ResponseEntity<>(empService.updateEmployee(id, empDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("id")long id) throws NoSuchEmployeeFoundException{
		return new ResponseEntity<>(empService.deleteEmployee(id), HttpStatus.OK);
	}
}