package com.cg.bugtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bugtracking.entity.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long>{
	
	@Query("select e.project from Employee e where e.empId = :empId")
	public List<Project> getProjectByEmployeId(@Param("empId") long empId);
}
