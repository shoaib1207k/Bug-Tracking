package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.exception.NotAdminException;



public interface BugService {
	
    public BugDTO createBug(BugDTO bugDTO);
	
	public BugDTO updateBug(BugDTO bugDTO,long id,long adminId) throws NoSuchBugFoundException, NotAdminException;
	
	public BugDTO getBug(long id,long adminId) throws NoSuchBugFoundException, NotAdminException;
	
	List <BugDTO> getAllBug(long adminId)throws NotAdminException;
	
    List <BugDTO> getAllBugStatus(String status);
	
	public  BugDTO deleteBug(long id, long adminId) throws NoSuchBugFoundException,NotAdminException;
	
	List<BugDTO> getAllBugsByProjectId(long id);
	
	List<BugDTO> findBugByEmpName(String empName);

}
