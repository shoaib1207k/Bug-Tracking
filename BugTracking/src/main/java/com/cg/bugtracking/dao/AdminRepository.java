package com.cg.bugtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bugtracking.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
