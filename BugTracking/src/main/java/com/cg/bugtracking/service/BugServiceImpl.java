package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bugtracking.dao.AdminRepository;
import com.cg.bugtracking.dao.BugRepository;
import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.entity.Admin;
import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.exception.NotAdminException;


@Service
public class BugServiceImpl implements BugService{
	
	private static final String NOT_ADMIN = "You are not an Admin!";
	
	@Autowired
	private BugRepository bRepo;
	
	@Autowired
	private AdminRepository aRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public BugDTO createBug(BugDTO bugDTO) {
		Bug bug = modelMapper.map(bugDTO,Bug.class); 
		bRepo.save(bug);
		return bugDTO;
	}

	@Override
	public BugDTO updateBug(BugDTO bugDTO ,long id, long adminId) throws NoSuchBugFoundException , NotAdminException{
		Optional<Bug> bugUpdate = bRepo.findById(id);
		Bug bug = modelMapper.map(bugDTO, Bug.class);
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
		   if(bugUpdate.isPresent()) {
			
			bugUpdate.get().setBugId(bug.getBugId());
			bugUpdate.get().setType(bug.getType());
			bugUpdate.get().setPriority(bug.getPriority());
			bugUpdate.get().setProgress(bug.getProgress());
			bugUpdate.get().setEndDate(bug.getEndDate());
		    bRepo.save(bugUpdate.get());
			return bugDTO;
		}else {
			throw new NoSuchBugFoundException("No Bug with this id");
		}
		
		}else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}
	@Override
	public BugDTO getBug(long id,long adminId) throws NoSuchBugFoundException, NotAdminException {
			Optional<Bug> buglist =bRepo.findById(id);
			
			Optional<Admin> findAdmin = aRepo.findById(adminId);
			if (findAdmin.isPresent()) {
				if(buglist.isPresent())
					return  modelMapper.map(buglist.get(),BugDTO.class);
				else throw new NoSuchBugFoundException("No Such Bug");
			} else {
				throw new NotAdminException(NOT_ADMIN);
			}
	}

	@Override
	public List<BugDTO> getAllBug(long adminId)throws NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
		return  bRepo.findAll()
				.stream().map(bug ->modelMapper.map(bug,BugDTO.class))
				.collect(Collectors.toList());
	}
		else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}


	@Override
	public
	List <BugDTO> getAllBugStatus(String status)  {
		return  bRepo.getAllBugsByStatus(status)
				.stream().map(bug ->modelMapper.map(status,BugDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public BugDTO deleteBug(long id, long adminId) throws NoSuchBugFoundException,NotAdminException{
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
		Optional<Bug> bugDel = bRepo.findById(id);
			if(bugDel.isPresent()) 
				bRepo.delete(bugDel.get());	
			else
				throw new NoSuchBugFoundException("No Bug with this id");
			return modelMapper.map(bugDel.get(), BugDTO.class);
		}else {
			throw new NotAdminException(NOT_ADMIN);
		}

	}

	@Override
	public List<BugDTO> getAllBugsByProjectId(long id) {
		return bRepo.getAllBugsByProjectId(id)
				.stream().map(bug ->modelMapper.map(id,BugDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<BugDTO> findBugByEmpName(String empName) {
		return bRepo.findBugByEmpName(empName)
				.stream().map(bug -> modelMapper.map(empName,BugDTO.class))
				.collect(Collectors.toList());
	}
}
