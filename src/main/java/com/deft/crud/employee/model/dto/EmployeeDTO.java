package com.deft.crud.employee.model.dto;

import java.util.Date;

public class EmployeeDTO implements java.io.Serializable {
							
	private int employeeNo;
	private String employeeName;
	private String employeeId;
	private String employeePwd;
	private java.util.Date employeeBirth;
	private String gender;
	private String email;
	private String address;
	private String phone;
	private String tel;
	private String deptName;
	private String jobName;
	private String authority;
	private int mangerNo;
	private String fax;
	private String hireYn;
	private java.util.Date hireDate;
	private java.util.Date entDate;
	
	public EmployeeDTO() {}

	public EmployeeDTO(int employeeNo, String employeeName, String employeeId, String employeePwd, Date employeeBirth,
			String gender, String email, String address, String phone, String tel, String deptName, String jobName,
			String authority, int mangerNo, String fax, String hireYn, Date hireDate, Date entDate) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeePwd = employeePwd;
		this.employeeBirth = employeeBirth;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.tel = tel;
		this.deptName = deptName;
		this.jobName = jobName;
		this.authority = authority;
		this.mangerNo = mangerNo;
		this.fax = fax;
		this.hireYn = hireYn;
		this.hireDate = hireDate;
		this.entDate = entDate;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePwd() {
		return employeePwd;
	}

	public void setEmployeePwd(String employeePwd) {
		this.employeePwd = employeePwd;
	}

	public java.util.Date getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(java.util.Date employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getMangerNo() {
		return mangerNo;
	}

	public void setMangerNo(int mangerNo) {
		this.mangerNo = mangerNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHireYn() {
		return hireYn;
	}

	public void setHireYn(String hireYn) {
		this.hireYn = hireYn;
	}

	public java.util.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.util.Date hireDate) {
		this.hireDate = hireDate;
	}

	public java.util.Date getEntDate() {
		return entDate;
	}

	public void setEntDate(java.util.Date entDate) {
		this.entDate = entDate;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeNo=" + employeeNo + ", employeeName=" + employeeName + ", employeeId=" + employeeId
				+ ", employeePwd=" + employeePwd + ", employeeBirth=" + employeeBirth + ", gender=" + gender
				+ ", email=" + email + ", address=" + address + ", phone=" + phone + ", tel=" + tel + ", deptName="
				+ deptName + ", jobName=" + jobName + ", authority=" + authority + ", mangerNo=" + mangerNo + ", fax="
				+ fax + ", hireYn=" + hireYn + ", hireDate=" + hireDate + ", entDate=" + entDate + "]";
	}

	
	
}
