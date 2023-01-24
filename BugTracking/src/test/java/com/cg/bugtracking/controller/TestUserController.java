package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.dto.UserDTO;
import com.cg.bugtracking.entity.Employee;
import com.cg.bugtracking.entity.Project;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.BugService;
import com.cg.bugtracking.service.UserService;

@ExtendWith(MockitoExtension.class)
class TestUserController {

	@Mock
	private UserService userService;

	@Mock
	private BugService bugService;

	@InjectMocks
	private UserController userController;

	UserDTO userDto;
	BugDTO bugDto;

	@BeforeEach
	public void init() {
		userDto = new UserDTO();
		userDto.setUserId(1);
		userDto.setRole("admin");
		// bug details
		bugDto = new BugDTO();
		Employee emp = new Employee();
		Project pro = new Project();
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(2);
		bugDto.setBugId(1);
		bugDto.setTitle("title");
		bugDto.setDescription("description of the bug");
		bugDto.setEmpName("Rahul");
		bugDto.setStartDate(startDate);
		bugDto.setEndDate(endDate);
		bugDto.setPriority("low");
		bugDto.setProgress(2);
		bugDto.setStatus("ongoing");
		bugDto.setProject(null);
		bugDto.setType("design");
		emp.setEmpId(userDto.getUserId());
		emp.setEmpName("Rahul");
		emp.setEmail("rahul@mail.com");
		emp.setContact("1234567890");
		emp.setProjId(111);
		pro.setProjId(111);
		pro.setProjName("Java Project");
		pro.setProjStatus("open");
		pro.setProjManager(emp);
		bugDto.setProject(pro);
	}

	@Test
	void testCreateUser() throws IdAlreadyExistsException {
		System.out.println(userDto);
		when(userService.createUser(userDto)).thenReturn(userDto);
		ResponseEntity<UserDTO> response = userController.createUser(userDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testCreateBug() {
		System.out.println(bugDto);
		when(bugService.createBug(bugDto)).thenReturn(bugDto);
		ResponseEntity<BugDTO> response = userController.createBug(bugDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void testFindAll() {
		List<UserDTO> userList = new ArrayList<>();
		userList.add(userDto);
		when(userService.findAllUsers()).thenReturn(userList);
		ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(userList.size(), response.getBody().size());
	}

	@Test
	void testFindById() throws NoSuchUserFoundException {
		when(userService.findById(anyLong())).thenReturn(userDto);
		ResponseEntity<UserDTO> response = userController.getById(1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(userDto, response.getBody());
	}

	@Test
	void testUpdate() throws NoSuchUserFoundException {
		when(userService.updateUser(1, userDto)).thenReturn(userDto);
		ResponseEntity<UserDTO> response = userController.updateUser(userDto,1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDto, response.getBody());
	}

	@Test
	void testRemove() throws NoSuchAdminFoundException, NoSuchUserFoundException {
		when(userService.deleteUser(anyLong())).thenReturn(userDto);
		ResponseEntity<UserDTO> response = userController.deleteUser(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDto, response.getBody());
	}

}
