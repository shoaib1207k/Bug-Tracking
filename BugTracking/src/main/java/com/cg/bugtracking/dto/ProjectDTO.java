package com.cg.bugtracking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import java.util.List;
//
//import com.cg.bugtracking.entity.Bug;
import com.cg.bugtracking.entity.Employee;

public class ProjectDTO {

	private long projId;
	
	@NotBlank(message = "Name is required")
	@Size(max = 25, min = 5, message = "atleast 5 characters")
	private String projName;
	
	
	private Employee projManager;
	
	@NotBlank(message = "Status is required")
	@Size(max = 15, min = 2, message = "atleast 2 characters")
	private String projStatus;
	
//	private List<Bug> bugList;
	
	public ProjectDTO() {}

	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Employee getProjManager() {
		return projManager;
	}

	public void setProjManager(Employee projManager) {
		this.projManager = projManager;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

//	public List<Bug> getBugList() {
//		return bugList;
//	}
//
//	public void setBugList(List<Bug> bugList) {
//		this.bugList = bugList;
//	}

	
}
