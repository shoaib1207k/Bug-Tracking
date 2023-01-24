package com.cg.bugtracking.entity;



//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name= "projects")
public class Project {

	@Id
	@Column(name = "project_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projId;
	
	@Column(name="project_name", nullable = false)
	private String projName;
	
	@OneToOne
	private Employee projManager;
	
	@Column(name = "project_status", nullable = false)
	private String projStatus;
	
//	@OneToMany
//	private List<Bug> bugList;
	
	

	public Project() {}

	public Project(long projId, String projName, Employee projManager, String projStatus) {
		super();
		this.projId = projId;
		this.projName = projName;
		this.projManager = projManager;
		this.projStatus = projStatus;
//		this.bugList = bugList;
	}

//	public List<Bug> getBugList() {
//		return bugList;
//	}
//
//	public void setBugList(List<Bug> bugList) {
//		this.bugList = bugList;
//	}

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
//		this.projManager.setProjId(this.projId);
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
