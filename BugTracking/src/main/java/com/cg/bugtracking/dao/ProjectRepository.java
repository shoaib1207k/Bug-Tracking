package com.cg.bugtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bugtracking.entity.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long>{

}
