package com.cg.bugtracking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.service.BugService;




@RestController
public class BugController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BugService bugService;
	
	@PostMapping("/bug")
	
	public ResponseEntity<BugDTO> createBug(@RequestBody BugDTO bugDTO){
		
		Bug bug = modelMapper.map(bugDTO, Bug.class);
		
		Bug bugDB = bugService.createBug(bug);
		
		return new ResponseEntity<>(modelMapper.map(bugDB, BugDTO.class), HttpStatus.CREATED);
	}
	
	@PostMapping("/updatebug/{id}")
	
	public ResponseEntity<BugDTO> updateBug(@PathVariable("id")long id, @RequestBody BugDTO bugDTO) throws NoSuchBugFoundException{
		
		Bug bugUpdate = modelMapper.map(bugDTO, Bug.class);
		
		bugService.updateBug(bugUpdate, id);
		
		return new ResponseEntity<>(bugDTO, HttpStatus.OK);
	}
	
	@GetMapping("/bug/{id}")
	
	public ResponseEntity<BugDTO> getBug(@PathVariable("id") long id) throws NoSuchBugFoundException{
		
		Bug bug = bugService.getBug(id);
		
		BugDTO bugDTO = modelMapper.map(bug, BugDTO.class);
		
		return new ResponseEntity<>(bugDTO, HttpStatus.FOUND);
	}
	
	
	@GetMapping("/bugs")
	
	public ResponseEntity<List<BugDTO>> getAllBug(){
		
		List<Bug> bugList = bugService.getAllBug();
		
		List<BugDTO> bugDTOList = bugList.stream()
										.map(bug->modelMapper.map(bug, BugDTO.class))
										.collect(Collectors.toList());
		
		return new ResponseEntity<>(bugDTOList, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deletebug/{id}")
	
	public ResponseEntity<BugDTO> DeleteBug(@PathVariable("id")long id) throws NoSuchBugFoundException{
		
		boolean bug = bugService.DeleteBug(id);
		
		BugDTO bugDTO = modelMapper.map(bug, BugDTO.class);
		
		return new ResponseEntity<>(bugDTO, HttpStatus.OK);
	}
}
