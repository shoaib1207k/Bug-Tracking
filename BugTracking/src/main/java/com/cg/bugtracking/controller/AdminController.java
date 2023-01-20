package com.cg.bugtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService aService;

	@PostMapping("/admin")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin a) {
		return new ResponseEntity(aService.createAdmin(a), HttpStatus.CREATED);
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User u) {
		return new ResponseEntity(aService.createUser(u), HttpStatus.CREATED);
	}
}
