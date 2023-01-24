package com.cg.bugtracking.dto;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class ProjectDTO {

	@NotNull
	@Min(value = 1, message="must be greater than zero")
	private long projId;
	
	@NotBlank(message = "Name is required")
	@Size(max = 25, min = 5, message = "atleast 5 characters")
	private String projName;
	
	
	@NotBlank(message = "Status is required")
	@Size(max = 15, min = 2, message = "atleast 2 characters")
	private String projStatus;


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

}
