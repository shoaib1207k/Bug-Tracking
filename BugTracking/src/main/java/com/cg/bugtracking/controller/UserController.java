package com.cg.bugtracking.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService uService;

	@PostMapping("/user")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		User userDb = uService.createUser(user);
		UserDTO userResponse = modelMapper.map(userDb, UserDTO.class);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
}
