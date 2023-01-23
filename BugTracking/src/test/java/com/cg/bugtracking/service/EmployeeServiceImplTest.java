package com.cg.bugtracking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.bugtracking.dao.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository empRepo;
	
	@InjectMocks
	private EmployeeService empService;
	
	
	@Test
	void testCreateEmployee() {

		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllEmployees() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
