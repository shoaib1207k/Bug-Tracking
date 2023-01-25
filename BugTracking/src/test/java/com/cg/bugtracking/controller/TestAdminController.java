package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;
import com.cg.bugtracking.service.AdminService;
import com.cg.bugtracking.service.EmployeeService;
import com.cg.bugtracking.service.ProjectService;

@ExtendWith(MockitoExtension.class)
class TestAdminController {

	@Mock
	private AdminService adminService;
	@Mock
	private EmployeeService employeeService;
	@Mock
	private ProjectService projectService;

	@InjectMocks
	private AdminController adminController;

	private UserDTO userDto;
	private AdminDTO adminDto;
	private EmployeeDTO employeeDto;
	private ProjectDTO projectDto;

	@BeforeEach
	public void init() {
		// user details
		userDto = new UserDTO();
		userDto.setUserId(1);
		userDto.setRole("admin");
		// admin details
		adminDto = new AdminDTO();
		adminDto.setAdminId(userDto.getUserId());
		adminDto.setAdminName("Rahul");
		adminDto.setAdminContact("1234567890");
		// employee details
		employeeDto = new EmployeeDTO();
		employeeDto.setEmpId(userDto.getUserId());
		employeeDto.setEmpName("Rahul");
		employeeDto.setEmail("rahul@mail.com");
		employeeDto.setContact("1234567890");
	}

	@Test
	void testCreateAdmin() throws NoAdminRoleFoundException, NoSuchUserFoundException, IdAlreadyExistsException {
		System.out.println(adminDto);
		when(adminService.createAdmin(adminDto)).thenReturn(adminDto);
		ResponseEntity<AdminDTO> response = adminController.createAdmin(adminDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testCreateEmployee() throws NoAdminRoleFoundException, NoSuchUserFoundException, NotAdminException {
		System.out.println(employeeDto);
		when(employeeService.createEmployee(employeeDto, 1)).thenReturn(employeeDto);
		ResponseEntity<EmployeeDTO> response = adminController.createEmployee(employeeDto, 1);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testCreateProject() throws NotAdminException {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(userDto.getUserId());
		emp.setEmpName("Rahul");
		emp.setEmail("rahul@mail.com");
		emp.setContact("1234567890");
		projectDto = new ProjectDTO();
		projectDto.setProjId(111);
		projectDto.setProjName("Java Project");
		projectDto.setProjStatus("open");
		when(projectService.createProject(projectDto, 1)).thenReturn(projectDto);
		ResponseEntity<ProjectDTO> response = adminController.createProject(projectDto, 1);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testFindAll() throws NotAdminException {
		List<AdminDTO> adminList = new ArrayList<>();
		adminList.add(adminDto);
		when(adminService.findAllAdmins(1)).thenReturn(adminList);
		ResponseEntity<List<AdminDTO>> response = adminController.getAllAdmins(1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(adminList.size(), response.getBody().size());
	}

	@Test
	void testFindById() throws NoSuchAdminFoundException, NotAdminException {
		when(adminService.findAdminById(1, 1)).thenReturn(adminDto);
		ResponseEntity<AdminDTO> response = adminController.getById(1, 1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(adminDto, response.getBody());
	}

	@Test
	void testUpdate() throws NoSuchAdminFoundException, NoAdminRoleFoundException, NotAdminException {
		when(adminService.updateAdmin(1, adminDto, 1)).thenReturn(adminDto);
		ResponseEntity<AdminDTO> response = adminController.updateAdmin(adminDto,1, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(adminDto, response.getBody());
	}

	@Test
	void testRemove() throws NoSuchAdminFoundException, NotAdminException {
		when(adminService.deleteAdmin(1, 1)).thenReturn(adminDto);
		ResponseEntity<AdminDTO> response = adminController.deleteAdmin(1, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(adminDto, response.getBody());
	}

}
