package com.cg.bugtracking.service;

import java.util.List;

import com.cg.bugtracking.dto.AdminDTO;
import com.cg.bugtracking.exception.IdAlreadyExistsException;
import com.cg.bugtracking.exception.NoAdminRoleFoundException;
import com.cg.bugtracking.exception.NoSuchAdminFoundException;
import com.cg.bugtracking.exception.NoSuchUserFoundException;
import com.cg.bugtracking.exception.NotAdminException;

public interface AdminService {

	public AdminDTO createAdmin(AdminDTO adminDto)
			throws NoSuchUserFoundException, NoAdminRoleFoundException, IdAlreadyExistsException;

	List<AdminDTO> findAllAdmins(long adminId) throws NotAdminException;

	public AdminDTO findAdminById(long id, long adminId) throws NoSuchAdminFoundException, NotAdminException;

	public AdminDTO updateAdmin(long id, AdminDTO adminDto, long adminId)
			throws NoSuchAdminFoundException, NoAdminRoleFoundException, NotAdminException;

	public AdminDTO deleteAdmin(long id, long adminId) throws NoSuchAdminFoundException, NotAdminException;

}
