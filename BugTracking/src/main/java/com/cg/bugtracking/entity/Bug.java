package com.cg.bugtracking.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bugs")
public class Bug {
	
	@Id
	@Column(name="Bug_id")
	private long bugId;
	private String title;
	private String description;
    private String type;
    
    @Column(name = "Bug_priority", nullable = false)
	private String priority;
	private int progress;
	private String empName; 
	private String status;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne
	private Project project;

	public Bug() {
		super();
	}

	public Bug(long bugId, String title, String description, String type, String priority, int progress, String empName,
			String status, LocalDate startDate, LocalDate endDate, Project project) {
		super();
		this.bugId = bugId;
		this.title = title;
		this.description = description;
		this.type = type;
		this.priority = priority;
		this.progress = progress;
		this.empName = empName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
	}

	public long getBugId() {
		return bugId;
	}

	public void setBugId(long bugId) {
		this.bugId = bugId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Bug [bugId=" + bugId + ", title=" + title + ", description=" + description + ", type=" + type
				+ ", priority=" + priority + ", progress=" + progress + ", empName=" + empName + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", project=" + project + "]";
	}

}
