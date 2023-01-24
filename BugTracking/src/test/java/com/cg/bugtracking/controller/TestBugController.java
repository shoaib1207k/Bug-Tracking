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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.service.BugService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(BugController.class)
class TestBugController {

	private static final BugDTO DTO = null;

	@MockBean
	private BugService bugService;

	@InjectMocks
	private BugController bugController;

	private BugDTO bugDTO;
	private List<BugDTO> bugDTOList;

	@BeforeEach
	public void setUp() {

		bugDTO=new BugDTO();
		bugDTO.setBugId(10);
		bugDTO.setTitle("title");
		bugDTO.setDescription("description");
		bugDTO.setType("type");
		bugDTO.setPriority("priority");
		bugDTO.setProgress(121);
		bugDTO.setEmpName("empName");
		bugDTO.setStatus("status");
		bugDTO.getStartDate();
		bugDTO.getEndDate();
		

		bugDTOList = new ArrayList<>();
		bugDTOList.add(bugDTO);
	}




	@Test
	void testGetAllBug()  {
		when(bugService.getAllBug()).thenReturn(bugDTOList);
		ResponseEntity<List<BugDTO>> response = bugController.getAllBug();		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testGetBugById() {
		try {
			when(bugService.getBug(1)).thenReturn(bugDTO);
			ResponseEntity<BugDTO> response = bugController.getBug(1);
			assertEquals(HttpStatus.FOUND, response.getStatusCode());
		} catch (NoSuchBugFoundException e) {
			fail("Unexpected exception");
		}
	}

	@Test
	void testUpdateBug() {
		bugDTO.setBugId(10);
		try {
			when(bugService.updateBug(bugDTO, 1)).thenReturn(bugDTO);
			ResponseEntity<BugDTO> response = bugController.updateBug(10,bugDTO);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (NoSuchBugFoundException e) {
			fail("Unexpected exception");
		}
	}

	@Test
	void testDeleteBug() {
		try {
			when(bugService.deleteBug(1)).thenReturn(DTO);
			ResponseEntity<BugDTO> response = bugController.deleteBug(1);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (NoSuchBugFoundException e) {
			fail("Unexpected exception");
		}
	}

}
