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

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.AdminService;
import com.cg.bugtracking.service.EmployeeService;
import com.cg.bugtracking.service.ProjectService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	private ProjectService prjService;

	// create (admin, employee, project)

	@PostMapping
	public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDto)
			throws NoAdminRoleFoundException, NoSuchUserFoundException {
		return new ResponseEntity<>(aService.createAdmin(adminDto), HttpStatus.CREATED);
	}

	@PostMapping("/{adminID}/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO empDTO, @PathVariable("adminID") long adminID)
			throws NoAdminRoleFoundException, NoSuchUserFoundException {
		return new ResponseEntity<>(empService.createEmployee(empDTO, adminID), HttpStatus.CREATED);
	}

	@PostMapping("/project")
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO prjDTO) {
		return new ResponseEntity<>(prjService.createProject(prjDTO), HttpStatus.CREATED);
	}

	// admin CRUD

	@GetMapping
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		return new ResponseEntity<>(aService.findAllAdmins(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminDTO> getById(@PathVariable long id) throws NoSuchAdminFoundException {
		return new ResponseEntity<>(aService.findAdminById(id), HttpStatus.FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO adminDto, @PathVariable long id)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException {
		return new ResponseEntity<>(aService.updateAdmin(id, adminDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AdminDTO> deleteAdmin(@PathVariable long id) throws NoSuchAdminFoundException {
		return new ResponseEntity<>(aService.deleteAdmin(id), HttpStatus.OK);
	}

}