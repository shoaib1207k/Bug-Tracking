package com.cg.bugtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bugtracking.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
