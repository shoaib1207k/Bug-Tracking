package com.cg.bugtracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		try {
			List<EmployeeDTO> empList = empService.getAllEmployees();
			if(empList.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(empList, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
