package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.exception.NotAdminException;



public interface BugService {
	
    public BugDTO createBug(BugDTO bugDTO);
	
	public BugDTO updateBug(BugDTO bugDTO,long id) throws NoSuchBugFoundException;
	
	public BugDTO getBug(long id,long adminId) throws NoSuchBugFoundException, NotAdminException;
	
	List <BugDTO> getAllBug();
	
    List <BugDTO> getAllBugStatus(String status);
	
	public  BugDTO deleteBug(long id) throws NoSuchBugFoundException;
	
	List<BugDTO> getAllBugsByProjectId(long id);
	
	List<BugDTO> findBugByEmpName(String empName);

}
