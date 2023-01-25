package com.cg.bugtracking.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.cg.bugtracking.entity.Project;

public class BugDTO {

	@NotBlank(message = "Id is required")
	@Min(value = 1, message = "must be greater than zero")
	private long bugId;

	@Size(max = 20, min = 3, message = "atleast 3 characters are required ")
	@NotBlank(message = "Title is required")
	private String title;

	@Size(max = 20, min = 3)
	private String description;

	@Size(max = 10, min = 1)
	@NotBlank(message = "Type is required")
	private String type;

	@NotBlank(message = "Priority is required")
	@Size(max = 20, min = 3)
	private String priority;

	@Min(value = 1, message = "must be greater than zero")
	@NotBlank(message = "Progress is required")
	private int progress;

	@Size(min = 3, message = "atleast 3 charachters required")
	@NotBlank(message = "Name is required")
	private String empName;

	@Size(min = 3, message = "atleast 3 charachters required")
	@NotBlank(message = "Status is required")
	private String status;

	private LocalDate startDate;
	private LocalDate endDate;

	private Project project;

	public BugDTO() {
		super();
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

}
