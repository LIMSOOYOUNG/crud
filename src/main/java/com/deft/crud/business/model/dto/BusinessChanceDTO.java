package com.deft.crud.business.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class BusinessChanceDTO implements Serializable {

	private int businessChanceNo;
	private String progressStatus;
	private String salesLevel;
	private int successPosibillity;
	private String businessTitle;
	private java.sql.Date dueDate;
	private String empName;
	private int empNo;
	private String customerName;
	private int customerNo;
	private String productName;
	private String customerCompanyName;
	
	public BusinessChanceDTO() {}

	public BusinessChanceDTO(int businessChanceNo, String progressStatus, String salesLevel, int successPosibillity,
			String businessTitle, Date dueDate, String empName, int empNo, String customerName, int customerNo,
			String productName, String customerCompanyName) {
		super();
		this.businessChanceNo = businessChanceNo;
		this.progressStatus = progressStatus;
		this.salesLevel = salesLevel;
		this.successPosibillity = successPosibillity;
		this.businessTitle = businessTitle;
		this.dueDate = dueDate;
		this.empName = empName;
		this.empNo = empNo;
		this.customerName = customerName;
		this.customerNo = customerNo;
		this.productName = productName;
		this.customerCompanyName = customerCompanyName;
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

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	@Override
	public String toString() {
		return "BusinessChanceDTO [businessChanceNo=" + businessChanceNo + ", progressStatus=" + progressStatus
				+ ", salesLevel=" + salesLevel + ", successPosibillity=" + successPosibillity + ", businessTitle="
				+ businessTitle + ", dueDate=" + dueDate + ", empName=" + empName + ", empNo=" + empNo
				+ ", customerName=" + customerName + ", customerNo=" + customerNo + ", productName=" + productName
				+ ", customerCompanyName=" + customerCompanyName + "]";
	}

	
	
	
}
