package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class TestEmployeeController {

	@Mock
	private EmployeeService empService;

	@InjectMocks
	private EmployeeController empController;

	private EmployeeDTO empDTO;
	private AdminDTO adminDTO;
	private List<EmployeeDTO> empDTOList;

	@BeforeEach
	public void setUp() {
		adminDTO = new AdminDTO();
		adminDTO.setAdminId(11);
		adminDTO.setAdminName("Rahul");
		adminDTO.setAdminContact("1234567890");

		empDTO = new EmployeeDTO();
		empDTO.setEmpId(1);
		empDTO.setEmpName("Shoaib Khan");
		empDTO.setEmail("sho@gmail.com");
		empDTO.setContact("0123456789");
		empDTO.setProjId(11);

		empDTOList = new ArrayList<>();
		empDTOList.add(empDTO);
	}


	@Test
	void testGetAllEmployees()  {
		ResponseEntity<List<EmployeeDTO>> response;
		try {
			when(empService.getAllEmployees(11)).thenReturn(empDTOList);
			response = empController.getAllEmployees(11);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (NoAdminRoleFoundException e) {
			fail("Unexpected exception");
		}		
	}

	@Test
	void testGetEmployeeById() {
		try {
			when(empService.getEmployeeById(1,11)).thenReturn(empDTO);
			ResponseEntity<EmployeeDTO> response = empController.getEmployeeById(1,11);
			assertEquals(HttpStatus.FOUND, response.getStatusCode());
		} catch (NoSuchEmployeeFoundException | NoAdminRoleFoundException e) {
			fail("Unexpected exception");
		}
	}

	@Test
	void testUpdateEmployee() {
		empDTO.setContact("987456321");
		try {
			when(empService.updateEmployee(1, empDTO, 11)).thenReturn(empDTO);
			ResponseEntity<EmployeeDTO> response = empController.updateEmployee(1, empDTO,11);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (NoSuchEmployeeFoundException | NoSuchProjectFoundException | NoAdminRoleFoundException e) {
			fail("Unexpected exception");
		}
	}

	@Test
	void testDeleteEmployee() {
		try {
			when(empService.deleteEmployee(1,11)).thenReturn(empDTO);
			ResponseEntity<EmployeeDTO> response = empController.deleteEmployee(1,11);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (NoSuchEmployeeFoundException | NoAdminRoleFoundException e) {
			fail("Unexpected exception");
		}
	}

}
