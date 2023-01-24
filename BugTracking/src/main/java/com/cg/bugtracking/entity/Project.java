package com.cg.bugtracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;


import org.springframework.context.annotation.Scope;

@Entity
@Table(name= "projects")
@Scope("prototype")
public class Project {

	@Id
	@Column(name = "project_id")
	private long projId;
	
	@Column(name="project_name", nullable = false)
	private String projName;
	
	
	@Column(name = "project_status", nullable = false)
	private String projStatus;

	public Project() {
		
	}	
	
	public Project(long projId, String projName, String projStatus) {
		super();
		this.projId = projId;
		this.projName = projName;
		this.projStatus = projStatus;
	}


	

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


	public String getProjStatus() {
		return projStatus;
	}


	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", projStatus=" + projStatus + "]";
	}
	
}