package com.deft.crud.business.model.dto;

import java.sql.Date;

public class BusinessDTO {

	private int businessChanceNo;
	private String progressStatus;
	private String salesLevel;
	private int successPosibillity;
	private String businessTitle;
	private java.sql.Date dueDate;
	private String empName;
	private String customerName;
	
	public BusinessDTO() {}

	public BusinessDTO(int businessChanceNo, String progressStatus, String salesLevel, int successPosibillity,
			String businessTitle, Date dueDate, String empName, String customerName) {
		super();
		this.businessChanceNo = businessChanceNo;
		this.progressStatus = progressStatus;
		this.salesLevel = salesLevel;
		this.successPosibillity = successPosibillity;
		this.businessTitle = businessTitle;
		this.dueDate = dueDate;
		this.empName = empName;
		this.customerName = customerName;
	}

	public int getBusinessChanceNo() {
		return businessChanceNo;
	}

	public void setBusinessChanceNo(int businessChanceNo) {
		this.businessChanceNo = businessChanceNo;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getSalesLevel() {
		return salesLevel;
	}

	public void setSalesLevel(String salesLevel) {
		this.salesLevel = salesLevel;
	}

	public int getSuccessPosibillity() {
		return successPosibillity;
	}

	public void setSuccessPosibillity(int successPosibillity) {
		this.successPosibillity = successPosibillity;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public java.sql.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.sql.Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "BusinessDTO [businessChanceNo=" + businessChanceNo + ", progressStatus=" + progressStatus
				+ ", salesLevel=" + salesLevel + ", successPosibillity=" + successPosibillity + ", businessTitle="
				+ businessTitle + ", dueDate=" + dueDate + ", empName=" + empName + ", customerName=" + customerName
				+ "]";
	}

	
	
}
