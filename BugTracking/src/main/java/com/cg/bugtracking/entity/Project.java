package com.cg.bugtracking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

	@Id
	private long projId;
	private String projName;
	private Employee projManager;
	private String projStatus;
	
	public Project() {}

	public Project(long projId, String projName, Employee projManager, String projStatus) {
		super();
		this.projId = projId;
		this.projName = projName;
		this.projManager = projManager;
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

	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", projManager=" + projManager + ", projStatus="
				+ projStatus + "]";
	}
	
	
	
}
