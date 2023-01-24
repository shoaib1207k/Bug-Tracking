package com.cg.bugtracking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.exception.NoSuchEmployeeFoundException;
import com.cg.bugtracking.service.BugService;
import com.cg.bugtracking.service.BugServiceImpl;
import com.cg.bugtracking.dao.BugRepository;
import com.cg.bugtracking.dto.BugDTO;

@ExtendWith(MockitoExtension.class)
public class BugServiceImplTest{
	@Mock
	private BugRepository bugRepo;
	
	@InjectMocks
	private BugServiceImpl bugService;
	 
	private List<BugDTO> bugList;
	private BugDTO bug =null;
	 
	private BugServiceImplTest() {
		
		bugService=new BugServiceImpl();
		bugList= new ArrayList<>();
	}
	@BeforeEach
	private void initEach() {
		bug=new BugDTO();
		bug.setBugId(10);
		bug.setTitle("title");
		bug.setDescription("description");
		bug.setType("type");
		bug.setPriority("priority");
		bug.setProgress(121);
		bug.setEmpName("empName");
		bug.setStatus("status");
		bug.getStartDate();
		bug.getEndDate();
		
		bugList.add(bug);	
	}
	
	@Test
	void createBugTest() {
	
	}
	@Test
	void updateBugTest() throws NoSuchBugFoundException{
		
		
		
		
	}
	
}