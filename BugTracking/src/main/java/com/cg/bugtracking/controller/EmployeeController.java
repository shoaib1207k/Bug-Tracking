package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.service.EmployeeService;

@RestController
@RequestMapping("{adminId}/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;


	
	@GetMapping("/{adminID}")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("empId") long empId,
			@PathVariable("adminId") long adminId) throws NoSuchEmployeeFoundException, NoAdminRoleFoundException {
		return new ResponseEntity<>(empService.getEmployeeById(empId, adminId), HttpStatus.FOUND);
	}

	@PutMapping("/{empId}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("empId") long empId,
			@Valid @RequestBody EmployeeDTO empDTO, @PathVariable("adminId") long adminId)
			throws NoSuchEmployeeFoundException, NoSuchProjectFoundException, NoAdminRoleFoundException {
		return new ResponseEntity<>(empService.updateEmployee(empId, empDTO, adminId), HttpStatus.OK);
	}

	@DeleteMapping("/{empId}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable("empId") long empId,
			@PathVariable("adminId") long adminId) throws NoSuchEmployeeFoundException, NoAdminRoleFoundException {
		return new ResponseEntity<>(empService.deleteEmployee(empId, adminId), HttpStatus.OK);
	}
	
	
	@GetMapping("/projId/{projId}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByProjId(@PathVariable("adminId") long adminId, @PathVariable("projId") long projId) throws NoAdminRoleFoundException{
		return new ResponseEntity<>(empService.getEmployeeByProjectId(adminId, projId), HttpStatus.OK);
	}
}