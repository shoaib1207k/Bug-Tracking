package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bugtracking.dao.BugRepository;
import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.exception.NoSuchBugFoundException;

@Service
public class BugServiceImpl implements BugService{
	
	
	@Autowired
	private BugRepository bRepo;

	@Override
	@Transactional
	public Bug createBug(Bug bug) {
		
		return bRepo.save(bug);
	}

	@Override
	public Bug updateBug( Bug bug,long id) throws NoSuchBugFoundException{
		Optional<Bug> bugUpdate = bRepo.findById(id);
		
		if(bugUpdate.isPresent() ) {
			
			bugUpdate.get().setBugId(bug.getBugId());
			bugUpdate.get().setType(bug.getType());
			bugUpdate.get().setPriority(bug.getPriority());
			bugUpdate.get().setProgress(bug.getProgress());
			bugUpdate.get().setEndDate(bug.getEndDate());
		    bRepo.save(bugUpdate.get());
		    return bug;
		}else {
			throw new NoSuchBugFoundException("No Bug with this id");
		}
	}
	@Override
	public Bug getBug(long id) throws NoSuchBugFoundException {
			Optional<Bug> buglist =bRepo.findById(id);
			if(buglist.isPresent())
				return buglist.get();
			else throw new NoSuchBugFoundException("No Such Bug");
	}

	@Override
	public List<Bug> getAllBug() {
		return bRepo.findAll();
	}

//	@Override
//	public List <Bug> getAllBugsByStatus(String status) throws NoSuchBugFoundException {
//		List<Bug> bfind=bRepo.getAllBugsByStatus(status);
//		return bfind;
//	}

	@Override
	public boolean DeleteBug(long id) throws NoSuchBugFoundException {
		bRepo.deleteById( id);
		Optional<Bug> bug=bRepo.findById(id);
		if(bug.isPresent()) {
		return false;
		}
		else return true;
	}

	
}
