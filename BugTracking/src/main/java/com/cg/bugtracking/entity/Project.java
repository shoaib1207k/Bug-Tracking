package com.cg.bugtracking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name= "projects")
public class Project {

	@Id
	@Column(name = "project_id" , updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projId;
	
	@Column(name="project_name", nullable = false)
	@NotBlank(message = "Name is required")
	@Size(max = 25, min = 5, message = "atleast 5 characters")
	private String projName;
	
	
	
	@ManyToOne
	private Employee projManager;
	
	@Column(name = "project_status", nullable = false, unique = true)
	@NotBlank(message = "Status is required")
	@Size(max = 15, min = 2, message = "atleast 2 characters")
	private String projStatus;
	
	@OneToMany
	private List<Bug> bugList;
	
	

	public Project() {}

	public Project(long projId, String projName, Employee projManager, String projStatus ,List<Bug> bugList) {
		super();
		this.projId = projId;
		this.projName = projName;
		this.projManager = projManager;
		this.projStatus = projStatus;
		this.bugList = bugList;
	}

	public List<Bug> getBugList() {
		return bugList;
	}

	public void setBugList(List<Bug> bugList) {
		this.bugList = bugList;
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