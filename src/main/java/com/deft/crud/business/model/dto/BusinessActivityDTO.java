package com.deft.crud.business.model.dto;

import java.sql.Date;

public class BusinessActivityDTO {

	private int activityNo;
	private String activityName;
	private String customerName;
	private int customerNo;
	private String empName;
	private int empNo;
	private String businessSubject;
	private String businessType;
	private String businessLocation;
	private String businessPurpose;
	private String businessContents;
	private java.sql.Date writingTime;
	private java.sql.Date activityStartTime;
	private java.sql.Date activityEndTime;
	
	public BusinessActivityDTO() {}

	public BusinessActivityDTO(int activityNo, String activityName, String customerName, int customerNo, String empName,
			int empNo, String businessSubject, String businessType, String businessLocation, String businessPurpose,
			String businessContents, Date writingTime, Date activityStartTime, Date activityEndTime) {
		super();
		this.activityNo = activityNo;
		this.activityName = activityName;
		this.customerName = customerName;
		this.customerNo = customerNo;
		this.empName = empName;
		this.empNo = empNo;
		this.businessSubject = businessSubject;
		this.businessType = businessType;
		this.businessLocation = businessLocation;
		this.businessPurpose = businessPurpose;
		this.businessContents = businessContents;
		this.writingTime = writingTime;
		this.activityStartTime = activityStartTime;
		this.activityEndTime = activityEndTime;
	}

	public int getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(int activityNo) {
		this.activityNo = activityNo;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getBusinessSubject() {
		return businessSubject;
	}

	public void setBusinessSubject(String businessSubject) {
		this.businessSubject = businessSubject;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessLocation() {
		return businessLocation;
	}

	public void setBusinessLocation(String businessLocation) {
		this.businessLocation = businessLocation;
	}

	public String getBusinessPurpose() {
		return businessPurpose;
	}

	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}

	public String getBusinessContents() {
		return businessContents;
	}

	public void setBusinessContents(String businessContents) {
		this.businessContents = businessContents;
	}

	public java.sql.Date getWritingTime() {
		return writingTime;
	}

	public void setWritingTime(java.sql.Date writingTime) {
		this.writingTime = writingTime;
	}

	public java.sql.Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(java.sql.Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public java.sql.Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(java.sql.Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	@Override
	public String toString() {
		return "BusinessActivityDTO [activityNo=" + activityNo + ", activityName=" + activityName + ", customerName="
				+ customerName + ", customerNo=" + customerNo + ", empName=" + empName + ", empNo=" + empNo
				+ ", businessSubject=" + businessSubject + ", businessType=" + businessType + ", businessLocation="
				+ businessLocation + ", businessPurpose=" + businessPurpose + ", businessContents=" + businessContents
				+ ", writingTime=" + writingTime + ", activityStartTime=" + activityStartTime + ", activityEndTime="
				+ activityEndTime + "]";
	}

	
	
	
}
