package com.deft.crud.organization.model.dto;

public class JobDTO implements java.io.Serializable{

	private String jobCode;
	private String jobName;
	private String jobStatus;
	
	public JobDTO() {}

	public JobDTO(String jobCode, String jobName, String jobStatus) {
		super();
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobStatus = jobStatus;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public String toString() {
		return "JobDTO [jobCode=" + jobCode + ", jobName=" + jobName + ", jobStatus=" + jobStatus + "]";
	}
	
	
	
}
