package com.cg.bugtracking.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bugtracking.dao.BugRepository;
import com.cg.bugtracking.dto.BugDTO;
import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;
import com.cg.bugtracking.service.BugService;


@Service
public class BugServiceImpl implements BugService{
	
	
	@Autowired
	private BugRepository bRepo;
	
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
	public BugDTO updateBug(BugDTO bugDTO ,long id) throws NoSuchBugFoundException {
		Optional<Bug> bugUpdate = bRepo.findById(id);
		Bug bug = modelMapper.map(bugDTO, Bug.class);

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
		
	}
	@Override
	public BugDTO getBug(long id) throws NoSuchBugFoundException {
			Optional<Bug> buglist =bRepo.findById(id);
			if(buglist.isPresent())
				return  modelMapper.map(buglist.get(),BugDTO.class);
			else throw new NoSuchBugFoundException("No Such Bug");
	}

	@Override
	public List<BugDTO> getAllBug() {
		return  bRepo.findAll()
				.stream().map(bug ->modelMapper.map(bug,BugDTO.class))
				.collect(Collectors.toList());
	}

//	@Override
//	public List <Bug> getAllBugsByStatus(String status) throws NoSuchBugFoundException {
//		List<Bug> bfind=bRepo.getAllBugsByStatus(status);
//		return bfind;
//	}

	@Override
	public BugDTO deleteBug(long id) throws NoSuchBugFoundException{
			Optional<Bug> bugDel = bRepo.findById(id);
			if(bugDel.isPresent()) 
				bRepo.delete(bugDel.get());	
			else
				throw new NoSuchBugFoundException("No Bug with this id");
			return modelMapper.map(bugDel.get(), BugDTO.class);
	

	}
}
