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

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminService aService;

	@PostMapping("/admin")
	public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDto) {

		// convert DTO to entity
		Admin admin = modelMapper.map(adminDto, Admin.class);
		Admin adminDb = aService.createAdmin(admin);

		// convert entity to DTO
		AdminDTO trainerResponse = modelMapper.map(adminDb, AdminDTO.class);

		return new ResponseEntity<>(trainerResponse, HttpStatus.CREATED);
	}

	@GetMapping("/adminlist")
	public ResponseEntity<List<AdminDTO>> getAllTrainers() {
		List<AdminDTO> admins = modelMapper.map(aService.findAllAdmins(), new TypeToken<List<AdminDTO>>() {
		}.getType());
		return ResponseEntity.ok(admins);
	}

}
