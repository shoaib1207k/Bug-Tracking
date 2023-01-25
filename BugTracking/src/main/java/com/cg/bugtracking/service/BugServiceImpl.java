package com.cg.bugtracking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
public class BugServiceImpl implements BugService {

	private static final Logger LOG = LogManager.getLogger(BugServiceImpl.class);

	private static final String NO_BUG_FOUND = "Bug not found.";

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
		Bug bug = modelMapper.map(bugDTO, Bug.class);
		LOG.info("Saving bug");
		bRepo.save(bug);
		LOG.info("Saved. Returning bug");
		return bugDTO;
	}

	@Override
	public BugDTO updateBug(BugDTO bugDTO, long id, long adminId) throws NoSuchBugFoundException, NotAdminException {
		Optional<Bug> bugUpdate = bRepo.findById(id);
		Bug bug = modelMapper.map(bugDTO, Bug.class);
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			if (bugUpdate.isPresent()) {
				LOG.info("Bug present. Updating...");
				bugUpdate.get().setBugId(bug.getBugId());
				bugUpdate.get().setType(bug.getType());
				bugUpdate.get().setPriority(bug.getPriority());
				bugUpdate.get().setProgress(bug.getProgress());
				bugUpdate.get().setEndDate(bug.getEndDate());
				LOG.info("Updating bug....");
				bRepo.save(bugUpdate.get());
				LOG.info("Saved. Returning bug");
				return bugDTO;
			} else {
				throw new NoSuchBugFoundException(NO_BUG_FOUND);
			}

		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public BugDTO getBug(long id, long adminId) throws NoSuchBugFoundException, NotAdminException {
		Optional<Bug> buglist = bRepo.findById(id);

		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			if (buglist.isPresent()) {
				LOG.info("Returning bug using id");
				return modelMapper.map(buglist.get(), BugDTO.class);
			} else {
				throw new NoSuchBugFoundException(NO_BUG_FOUND);
			}
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public List<BugDTO> getAllBug(long adminId) throws NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			LOG.info("Returning all bugs");
			return bRepo.findAll().stream().map(bug -> modelMapper.map(bug, BugDTO.class)).collect(Collectors.toList());
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}
	}

	@Override
	public List<BugDTO> getAllBugStatus(String status) {
		LOG.info("Returning  bugs with status");
		return modelMapper.map(bRepo.getAllBugsByStatus(status), new TypeToken<List<BugDTO>>() {}.getType());
	}

	@Override
	public BugDTO deleteBug(long id, long adminId) throws NoSuchBugFoundException, NotAdminException {
		Optional<Admin> findAdmin = aRepo.findById(adminId);
		if (findAdmin.isPresent()) {
			Optional<Bug> bugDel = bRepo.findById(id);
			if (bugDel.isPresent()) {
				LOG.info("Deleting...");
				bRepo.delete(bugDel.get());
				LOG.info("Deleted.");
			} else
				throw new NoSuchBugFoundException(NO_BUG_FOUND);
			return modelMapper.map(bugDel.get(), BugDTO.class);
		} else {
			throw new NotAdminException(NOT_ADMIN);
		}

	}

	@Override
	public List<BugDTO> getAllBugsByProjectId(long id) {
		LOG.info("Returning  bugs with projectId");
		return modelMapper.map(bRepo.getAllBugsByProjectId(id), new TypeToken<List<BugDTO>>() {}.getType());
	}

	@Override
	public List<BugDTO> findBugByEmpName(String empName) {
		LOG.info("Returning bugs with Employee name");
		return modelMapper.map(bRepo.findBugByEmpName(empName), new TypeToken<List<BugDTO>>() {}.getType());

	}
}
