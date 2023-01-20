package com.cg.bugtracking.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeService empService;
	
	
	
}
