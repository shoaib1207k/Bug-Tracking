package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.entity.User;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
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

	private AdminDTO adminDto;
	private EmployeeDTO employeeDto;
	private ProjectDTO projectDto;

	User user = new User();

	@BeforeEach
	public void init() {
		user.setUserId(1);
		user.setRole("admin");
	}

	@Test
	void testCreateAdmin() throws NoAdminRoleFoundException, NoSuchUserFoundException {
		adminDto = new AdminDTO();
		adminDto.setAdminId(user.getUserId());
		adminDto.setAdminName("Rahul");
		adminDto.setAdminContact("1234567890");
		MockitoAnnotations.openMocks(this);
		when(adminService.createAdmin(adminDto)).thenReturn(adminDto);
		ResponseEntity<AdminDTO> response = adminController.createAdmin(adminDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testCreateEmployee() throws NoAdminRoleFoundException, NoSuchUserFoundException {
		employeeDto = new EmployeeDTO();
		employeeDto.setEmpId(user.getUserId());
		employeeDto.setEmpName("Rahul");
		employeeDto.setEmail("rahul@mail.com");
		employeeDto.setContact("1234567890");
		employeeDto.setProjId(111);
		when(employeeService.createEmployee(employeeDto)).thenReturn(employeeDto);
		ResponseEntity<EmployeeDTO> response = adminController.createEmployee(employeeDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testCreateProject() {
		Employee emp = new Employee();
		emp.setEmpId(user.getUserId());
		emp.setEmpName("Rahul");
		emp.setEmail("rahul@mail.com");
		emp.setContact("1234567890");
		emp.setProjId(111);
		projectDto = new ProjectDTO();
		projectDto.setProjId(111);
		projectDto.setProjName("Java Project");
		projectDto.setProjStatus("open");
		projectDto.setProjManager(emp);
		when(projectService.createProject(projectDto)).thenReturn(projectDto);
		ResponseEntity<ProjectDTO> response = adminController.createProject(projectDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

}
