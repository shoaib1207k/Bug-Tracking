package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.exception.NoSuchBugFoundException;



public interface BugService {
	
    public BugDTO createBug(BugDTO bugDTO);
	
	public BugDTO updateBug(BugDTO bugDTO,long id) throws NoSuchBugFoundException;
	
	public BugDTO getBug(long id) throws NoSuchBugFoundException;
	
	List <BugDTO> getAllBug();
	
	public  BugDTO DeleteBug(long id) throws NoSuchBugFoundException;

}
