package com.deft.crud.organization.model.dto;

public class DepartmentDTO implements java.io.Serializable{

	private String deptCode;
	private String deptName;
	private String deptFax;
	private String depttel;
	private String deptStatus;
	
	public DepartmentDTO() {}

	public DepartmentDTO(String deptCode, String deptName, String deptFax, String depttel, String deptStatus) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptFax = deptFax;
		this.depttel = depttel;
		this.deptStatus = deptStatus;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptFax() {
		return deptFax;
	}

	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}

	public String getDepttel() {
		return depttel;
	}

	public void setDepttel(String depttel) {
		this.depttel = depttel;
	}

	public String getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}

	@Override
	public String toString() {
		return "OrganizationDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptFax=" + deptFax
				+ ", depttel=" + depttel + ", deptStatus=" + deptStatus + "]";
	}
	
	
}
