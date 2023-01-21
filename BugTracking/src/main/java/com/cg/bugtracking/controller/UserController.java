package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.service.UserService;

@RestController
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService uService;

	@PostMapping("/user")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {

		// convert DTO to entity
		User user = modelMapper.map(userDto, User.class);
		User userDb = uService.createUser(user);

		// convert entity to DTO
		UserDTO trainerResponse = modelMapper.map(userDb, UserDTO.class);

		return new ResponseEntity<>(trainerResponse, HttpStatus.CREATED);
	}

	@GetMapping("/userlist")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = modelMapper.map(uService.findAllUsers(), new TypeToken<List<UserDTO>>() {
		}.getType());
		return ResponseEntity.ok(users);
	}
}
