package com.deft.crud.business.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BusinessChanceHistoryDTO implements Serializable {

	private int businessChanceHisNo;
	private int businessChanceNo;
	private String progressStatus;
	private String salesLevel;
	private int successPosibillity;
	private String empName;
	private LocalDateTime businessChanceChangeDate;
	
	public BusinessChanceHistoryDTO() {}

	public BusinessChanceHistoryDTO(int businessChanceHisNo, int businessChanceNo, String progressStatus,
			String salesLevel, int successPosibillity, String empName, LocalDateTime businessChanceChangeDate) {
		super();
		this.businessChanceHisNo = businessChanceHisNo;
		this.businessChanceNo = businessChanceNo;
		this.progressStatus = progressStatus;
		this.salesLevel = salesLevel;
		this.successPosibillity = successPosibillity;
		this.empName = empName;
		this.businessChanceChangeDate = businessChanceChangeDate;
	}

	public int getBusinessChanceHisNo() {
		return businessChanceHisNo;
	}

	public void setBusinessChanceHisNo(int businessChanceHisNo) {
		this.businessChanceHisNo = businessChanceHisNo;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDateTime getBusinessChanceChangeDate() {
		return businessChanceChangeDate;
	}

	public void setBusinessChanceChangeDate(LocalDateTime businessChanceChangeDate) {
		this.businessChanceChangeDate = businessChanceChangeDate;
	}

	@Override
	public String toString() {
		return "BusinessChanceHistoryDTO [businessChanceHisNo=" + businessChanceHisNo + ", businessChanceNo="
				+ businessChanceNo + ", progressStatus=" + progressStatus + ", salesLevel=" + salesLevel
				+ ", successPosibillity=" + successPosibillity + ", empName=" + empName + ", businessChanceChangeDate="
				+ businessChanceChangeDate + "]";
	}

	
	
	
}
