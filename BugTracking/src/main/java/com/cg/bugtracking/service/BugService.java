package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;



public interface BugService {
	
    public Bug createBug(Bug bug);
	
	public Bug updateBug(Bug bug,long id) throws NoSuchBugFoundException;
	
	public Bug getBug(long id) throws NoSuchBugFoundException;
	
	List <Bug> getAllBug();
	
//	public List <Bug> getAllBugsByStatus(String status) throws NoSuchBugFoundException;
	
	public boolean DeleteBug(long id) throws NoSuchBugFoundException;

}
