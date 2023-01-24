package com.cg.bugtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bugtracking.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("Select e from Employee e Join e.project p where p.projId=:projId")
	public List<Employee> getEmployeeByProjectId(@Param("projId") long projId);

}
