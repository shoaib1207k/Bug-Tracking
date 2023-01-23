package com.cg.bugtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bugtracking.entity.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug,Long>{

//	@Query("Select b from Bug where b.status= :status")
//	public List<Bug> getAllBugsByStatus(@Param("status")String status);

}
