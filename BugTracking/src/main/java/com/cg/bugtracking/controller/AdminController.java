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
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;
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
			throws NoAdminRoleFoundException, NoSuchUserFoundException, IdAlreadyExistsException {
		return new ResponseEntity<>(aService.createAdmin(adminDto), HttpStatus.CREATED);
	}

	@PostMapping("/{adminId}/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO empDTO,
			@PathVariable("adminId") long adminId)
			throws NoAdminRoleFoundException, NoSuchUserFoundException, NotAdminException {
		return new ResponseEntity<>(empService.createEmployee(empDTO, adminId), HttpStatus.CREATED);
	}

	@PostMapping("/{adminId}/project")
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO prjDTO,
			@PathVariable("adminId") long adminId) throws NotAdminException {
		return new ResponseEntity<>(prjService.createProject(prjDTO, adminId), HttpStatus.CREATED);
	}

	// admin CRUD

	@GetMapping("/{adminId}")
	public ResponseEntity<List<AdminDTO>> getAllAdmins(@PathVariable("adminId") long adminId) throws NotAdminException {
		return new ResponseEntity<>(aService.findAllAdmins(adminId), HttpStatus.FOUND);
	}

	@GetMapping("/{adminId}/{id}")
	public ResponseEntity<AdminDTO> getById(@PathVariable long id, @PathVariable("adminId") long adminId)
			throws NoSuchAdminFoundException, NotAdminException {
		return new ResponseEntity<>(aService.findAdminById(id, adminId), HttpStatus.FOUND);
	}

	@PutMapping("/{adminId}/{id}")
	public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO adminDto, @PathVariable long id,
			@PathVariable("adminId") long adminId)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException, NotAdminException {
		return new ResponseEntity<>(aService.updateAdmin(id, adminDto, adminId), HttpStatus.OK);
	}

	@DeleteMapping("/{adminId}/{id}")
	public ResponseEntity<AdminDTO> deleteAdmin(@PathVariable long id, @PathVariable("adminId") long adminId)
			throws NoSuchAdminFoundException, NotAdminException {
		return new ResponseEntity<>(aService.deleteAdmin(id, adminId), HttpStatus.OK);
	}

}