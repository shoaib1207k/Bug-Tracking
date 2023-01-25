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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bugtracking.dto.EmployeeDTO;
import com.cg.bugtracking.dto.ProjectDTO;
import com.cg.bugtracking.exception.NoSuchProjectFoundException;
import com.cg.bugtracking.exception.NotAdminException;
import com.cg.bugtracking.service.ProjectService;

@ExtendWith(MockitoExtension.class)
class TestProjectController {

	@Mock
	private ProjectService prjService;

	@InjectMocks
	private ProjectController prjController;

	private ProjectDTO prjDTO;
	private List<ProjectDTO> prjDTOList;
	private EmployeeDTO empDTO;

	@BeforeEach
	public void setUp() {
		prjDTO = new ProjectDTO();
		prjDTO.setProjId(101);
		prjDTO.setProjName("Spring Project");

		empDTO = new EmployeeDTO();
		empDTO.setEmpId(1);
		empDTO.setEmpName("Prakash");
		empDTO.setEmail("abc@123");
		empDTO.setContact("99999999999");

		// prjDTO.setProjManager(empDTO);
		prjDTO.setProjStatus("active");

		prjDTOList = new ArrayList<>();
		prjDTOList.add(prjDTO);

	}

//	@Test
//	void testCreateProject() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetAllProjects() throws NotAdminException {
		when(prjService.getAllProjects(1)).thenReturn(prjDTOList);
		ResponseEntity<List<ProjectDTO>> response = prjController.getAllProjects(1);		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testGetProjectById() throws NoSuchProjectFoundException,NotAdminException {
			when(prjService.getProjectById(1, 1)).thenReturn(prjDTO);
			ResponseEntity<ProjectDTO> response = prjController.getProjectById(1, 1);
			assertEquals(HttpStatus.FOUND, response.getStatusCode());
			assertEquals(prjDTO, response.getBody());
		}

	@Test
	void testUpdateProject() throws NoSuchProjectFoundException, NotAdminException {
		prjDTO.setProjName("Java");
		when(prjService.updateProject(1, prjDTO, 1)).thenReturn(prjDTO);
		ResponseEntity<ProjectDTO> response = prjController.updateProject(1, prjDTO, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(prjDTO, response.getBody());

	}

	@Test
	void testDeleteProject() throws NoSuchProjectFoundException, NotAdminException {
			when(prjService.deleteProject(1, 0)).thenReturn(prjDTO);
			ResponseEntity<ProjectDTO> response = prjController.deleteProject(1, 0);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

}
