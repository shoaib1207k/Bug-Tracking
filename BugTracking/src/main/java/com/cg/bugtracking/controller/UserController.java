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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;
import com.cg.bugtracking.service.BugService;
import com.cg.bugtracking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService uService;

	@Autowired
	private BugService bugService;

	// create (user, bug)

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) throws IdAlreadyExistsException {
		return new ResponseEntity<>(uService.createUser(userDto), HttpStatus.CREATED);
	}

	@PostMapping("/bug")
	public ResponseEntity<BugDTO> createBug(@Valid @RequestBody BugDTO bugDTO) {
		return new ResponseEntity<>(bugService.createBug(bugDTO), HttpStatus.CREATED);
	}

	// user CRUD

	@GetMapping("/{adminId}")
	public ResponseEntity<List<UserDTO>> getAllUsers(@PathVariable("adminId") long adminId) throws NotAdminException {
		return new ResponseEntity<>(uService.findAllUsers(adminId), HttpStatus.OK);
	}

	@GetMapping("/{adminId}/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable long id, @PathVariable("adminId") long adminId)
			throws NoSuchUserFoundException, NotAdminException {
		return new ResponseEntity<>(uService.findById(id, adminId), HttpStatus.OK);
	}

	@PutMapping("/{adminId}/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable long id,
			@PathVariable("adminId") long adminId) throws NoSuchUserFoundException, NotAdminException {
		return new ResponseEntity<>(uService.updateUser(id, userDto, adminId), HttpStatus.OK);
	}

	@DeleteMapping("/{adminId}/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable long id, @PathVariable("adminId") long adminId)
			throws NoSuchUserFoundException, NotAdminException {
		return new ResponseEntity<>(uService.deleteUser(id, adminId), HttpStatus.OK);
	}

}
