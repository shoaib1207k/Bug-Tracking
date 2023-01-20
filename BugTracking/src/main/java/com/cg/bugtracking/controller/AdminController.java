package com.cg.bugtracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.service.UserService;

@RestController
public class AdminController {

	@Autowired
	private UserService uService;
}
