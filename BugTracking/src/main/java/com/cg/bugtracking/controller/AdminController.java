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
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.AdminService;
import com.cg.bugtracking.service.BugService;
import com.cg.bugtracking.service.EmployeeService;
import com.cg.bugtracking.service.ProjectService;

@RestController
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	private ProjectService prjService;

	@Autowired
	private BugService bugService;

	@PostMapping("/admin")
	public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDto)
			throws NoAdminRoleFoundException, NoSuchUserFoundException {
		return new ResponseEntity<>(aService.createAdmin(adminDto), HttpStatus.CREATED);
	}

	@GetMapping("/adminlist")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		return new ResponseEntity<>(aService.findAllAdmins(), HttpStatus.OK);
	}

	@GetMapping("/admin/{id}")
	public ResponseEntity<AdminDTO> getById(@PathVariable long id) throws NoSuchAdminFoundException {
		return new ResponseEntity<>(aService.findAdminById(id), HttpStatus.FOUND);
	}

	@PutMapping("/admin/{id}")
	public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO adminDto, @PathVariable long id)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException {
		return new ResponseEntity<>(aService.updateAdmin(id, adminDto), HttpStatus.OK);
	}

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<AdminDTO> deleteAdmin(@PathVariable long id) throws NoSuchAdminFoundException {
		return new ResponseEntity<>(aService.deleteAdmin(id), HttpStatus.OK);
	}

	// employee

	@PostMapping("admin/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO empDTO) {
		return new ResponseEntity<>(empService.createEmployee(empDTO), HttpStatus.CREATED);
	}

	// project

	@PostMapping("/admin/project")
	public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO prjDTO) {
		return new ResponseEntity<>(prjService.createProject(prjDTO), HttpStatus.CREATED);
	}

	// bug

	@PostMapping("admin/bug")
	public ResponseEntity<BugDTO> createBug(@RequestBody BugDTO bugDTO) {
		return new ResponseEntity<>(bugService.createBug(bugDTO), HttpStatus.CREATED);
	}

}