package com.cg.bugtracking.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bugtracking.dto.BugDTO;

import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.service.BugService;






@RestController
public class BugController {
	

	
	@Autowired
	private BugService bugService;
	
	@PostMapping("/bug")
	public ResponseEntity<BugDTO> createBug(@RequestBody BugDTO bugDTO) {
		
		return new ResponseEntity<>(bugService.createBug(bugDTO), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/bug/{id}")
	public ResponseEntity<BugDTO> updateBug(@PathVariable("id")long id, @RequestBody BugDTO bugDTO) throws NoSuchBugFoundException{
	 
	return new  ResponseEntity<>(bugService.updateBug(bugDTO, id),HttpStatus.OK);
   }
	
	@GetMapping("/bug/{id}")
	public ResponseEntity<BugDTO> getBug(@PathVariable("id") long id) throws NoSuchBugFoundException{
		
		return new ResponseEntity<>(bugService.getBug(id), HttpStatus.FOUND);
	}

	@GetMapping("/bugs")
	public ResponseEntity<List<BugDTO>> getAllBug(){
		return new ResponseEntity<>(bugService.getAllBug(), HttpStatus.OK);
	}
	
	@DeleteMapping("/bug/{id}")
	public ResponseEntity<BugDTO> deleteEmployee(@PathVariable("id")long id) throws NoSuchBugFoundException{
		return new ResponseEntity<>(bugService.deleteBug(id), HttpStatus.OK);
	}
}
