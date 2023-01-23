package com.cg.bugtracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminService aService;

	@PostMapping("/admin")
	public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDto)
			throws NoAdminRoleFoundException, NoSuchUserFoundException {
		Admin admin = modelMapper.map(adminDto, Admin.class);
		return new ResponseEntity<>(modelMapper.map(aService.createAdmin(admin), AdminDTO.class), HttpStatus.CREATED);
	}

	@GetMapping("/adminlist")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		List<AdminDTO> admins = modelMapper.map(aService.findAllAdmins(), new TypeToken<List<AdminDTO>>() {
		}.getType());
		return ResponseEntity.ok(admins);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<AdminDTO> getById(@PathVariable long id) throws NoSuchAdminFoundException {
		return ResponseEntity.ok(modelMapper.map(aService.findAdminById(id), AdminDTO.class));
	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO adminDto, @PathVariable int id)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException {
		Admin admin = modelMapper.map(adminDto, Admin.class);
		return ResponseEntity.accepted().body(modelMapper.map(aService.updateAdmin(id, admin), AdminDTO.class));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		return new ResponseEntity<>(aService.deleteAdmin(id) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);
	}

}