package com.deft.crud.organization.model.dto;

public class DepartmentDTO implements java.io.Serializable{

	private String deptCode;
	private String deptName;
	private String deptFax;
	private String deptTel;
	private String deptStatus;
	
	public DepartmentDTO() {}

	public DepartmentDTO(String deptCode, String deptName, String deptFax, String deptTel, String deptStatus) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptFax = deptFax;
		this.deptTel = deptTel;
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

	public String getDeptTel() {
		return deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public String getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}

	@Override
	public String toString() {
		return "DepartmentDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptFax=" + deptFax + ", deptTel="
				+ deptTel + ", deptStatus=" + deptStatus + "]";
	}

	
	
}
