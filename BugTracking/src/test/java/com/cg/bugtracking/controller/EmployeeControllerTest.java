package com.cg.bugtracking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.service.EmployeeService;

class EmployeeControllerTest {

	@Mock
	private EmployeeService empService;

	@InjectMocks
	private EmployeeController empController;

	
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
	void testGetAllEmployees() {
		when(empService.getAllEmployees()).thenReturn(empDTOList);
		assertEquals(empDTOList, empService.getAllEmployees());
	}
//
//	@Test
//	void testGetEmployeeById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateEmployee() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteEmployee() {
//		fail("Not yet implemented");
//	}

}
