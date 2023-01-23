package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
class TestEmployeeController {

	@MockBean
	private EmployeeService empService;

	private EmployeeDTO empDTO;
	private List<EmployeeDTO> empDTOList;

	@BeforeEach
	public void setUp() {

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
	void testCreateEmployee() throws Exception {
		when(empService.createEmployee(empDTO)).thenReturn(empDTO);
		assertEquals(empDTO, empService.createEmployee(empDTO));
	}

	@Test
	void testGetAllEmployees() throws Exception {
		when(empService.getAllEmployees()).thenReturn(empDTOList);
		assertEquals(empDTOList, empService.getAllEmployees());
	}

	@Test
	void testGetEmployeeById() throws Exception {
		when(empService.getEmployeeById(1)).thenReturn(empDTO);
		assertEquals(empDTO, empService.getEmployeeById(1));
	}

	@Test
	void testUpdateEmployee() throws Exception {
		empDTO.setContact("987456321");
		when(empService.updateEmployee(1, empDTO)).thenReturn(empDTO);
		assertEquals(empDTO, empService.updateEmployee(1, empDTO));
	}

	@Test
	void testDeleteEmployee() throws Exception {
		when(empService.deleteEmployee(1)).thenReturn(empDTO);
		assertEquals(empDTO, empService.deleteEmployee(1));
	}

}
