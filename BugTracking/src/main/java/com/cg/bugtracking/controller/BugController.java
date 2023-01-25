package com.cg.bugtracking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.BugDTO;

import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.exception.NotAdminException;
import com.cg.bugtracking.service.BugService;

@RestController
public class BugController {
	
	@Autowired
	private BugService bugService;
	
	@PostMapping("/bug/{adminId}/{id}")
	public ResponseEntity<BugDTO> createBug(@PathVariable("id")long id, @RequestBody BugDTO bugDTO,@PathVariable("adminId")long adminId) throws NoSuchBugFoundException, NotAdminException{
	 
	return new  ResponseEntity<>(bugService.updateBug(bugDTO, id,adminId),HttpStatus.OK);
   }
	
	@PutMapping("/bug/{adminId}/{id}")
	public ResponseEntity<BugDTO> updateBug(@PathVariable("id")long id, @RequestBody BugDTO bugDTO,@PathVariable("adminId")long adminId) throws NoSuchBugFoundException, NotAdminException{
	 
	return new  ResponseEntity<>(bugService.updateBug(bugDTO, id,adminId),HttpStatus.OK);
   }
	
	@GetMapping("/bug/{adminId}/{id}")
	public ResponseEntity<BugDTO> getBug(@PathVariable("id") long id,@PathVariable("adminId")long adminId) throws NoSuchBugFoundException, NotAdminException{
		
		return new ResponseEntity<>(bugService.getBug(id,adminId), HttpStatus.FOUND);
	}

	@GetMapping("/bugs")
	public ResponseEntity<List<BugDTO>> getAllBug(long adminId)throws NotAdminException{
		return new ResponseEntity<>(bugService.getAllBug(adminId), HttpStatus.OK);
	}
	
	@GetMapping("/bugstatus/{status}")
	public ResponseEntity<List<BugDTO>> getAllBugStatus(@PathVariable("status") String status){
		return new ResponseEntity<>(bugService.getAllBugStatus(status), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/bug/{id}")
	public ResponseEntity<BugDTO> deleteBug(@PathVariable("id")long id,@PathVariable("adminId")long adminId) throws NoSuchBugFoundException, NotAdminException{
		return new ResponseEntity<>(bugService.deleteBug(id,adminId), HttpStatus.OK);
	}
	
	
	@GetMapping("/bugbyproject/{id}")
	public ResponseEntity<List<BugDTO>> getAllBugsByProjectId(@PathVariable("id") long id){
		return new ResponseEntity<>(bugService.getAllBugsByProjectId(id), HttpStatus.OK);
	}
	
	@GetMapping("/bugbyemployee/{empName}")
	public ResponseEntity<List<BugDTO>> findBugByEmpName(@PathVariable("empName") String empName){
		return new ResponseEntity<>(bugService.findBugByEmpName(empName), HttpStatus.OK);
	}
}
