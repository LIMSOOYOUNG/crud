package com.deft.crud.member.model.dto;

import java.sql.Date;

public class MemberDTO {
	
	private int empNo;
	private String empName;
	private String empId;
	private String empPwd;
	private String empEmail;
	private String gender;
	private String empBirth;
	private String hireYn;
	private String authority;
	private java.sql.Date hireDate;
	private java.sql.Date entDate;
	private String empAddress;
	private String empPhone;
	private String empTel;
	private String empFax;
	private String jobNo;
	private String deptNo;
	private int managerNo;
	
	public MemberDTO() {}

	public MemberDTO(int empNo, String empName, String empId, String empPwd, String empEmail, String gender,
			String empBirth, String hireYn, String authority, Date hireDate, Date entDate, String empAddress,
			String empPhone, String empTel, String empFax, String jobNo, String deptNo, int managerNo) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empId = empId;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.gender = gender;
		this.empBirth = empBirth;
		this.hireYn = hireYn;
		this.authority = authority;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.empAddress = empAddress;
		this.empPhone = empPhone;
		this.empTel = empTel;
		this.empFax = empFax;
		this.jobNo = jobNo;
		this.deptNo = deptNo;
		this.managerNo = managerNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpBirth() {
		return empBirth;
	}

	public void setEmpBirth(String empBirth) {
		this.empBirth = empBirth;
	}

	public String getHireYn() {
		return hireYn;
	}

	public void setHireYn(String hireYn) {
		this.hireYn = hireYn;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public java.sql.Date getEntDate() {
		return entDate;
	}

	public void setEntDate(java.sql.Date entDate) {
		this.entDate = entDate;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpFax() {
		return empFax;
	}

	public void setEmpFax(String empFax) {
		this.empFax = empFax;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	@Override
	public String toString() {
		return "MemberDTO [empNo=" + empNo + ", empName=" + empName + ", empId=" + empId + ", empPwd=" + empPwd
				+ ", empEmail=" + empEmail + ", gender=" + gender + ", empBirth=" + empBirth + ", hireYn=" + hireYn
				+ ", authority=" + authority + ", hireDate=" + hireDate + ", entDate=" + entDate + ", empAddress="
				+ empAddress + ", empPhone=" + empPhone + ", empTel=" + empTel + ", empFax=" + empFax + ", jobNo="
				+ jobNo + ", deptNo=" + deptNo + ", managerNo=" + managerNo + "]";
	}
}
