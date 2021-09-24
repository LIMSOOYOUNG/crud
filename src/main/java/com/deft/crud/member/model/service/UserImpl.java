package com.deft.crud.member.model.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.deft.crud.member.model.dto.MemberDTO;

public class UserImpl extends User {
	
	private int empNo;
	private String empName;
	private String empId;
	private String empPwd;
	private String empEmail;
	private String empGender;
	private String empBirth;
	private String empAddress;
	private String empPhone;
	private String empTel;
	private java.sql.Date hireDate;
	private java.sql.Date entDate;
	private String entYn;
	private String authority;
	private String jobCode;
	private String deptCode;
	private int managerNo;

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(MemberDTO member) {
		this.empNo = member.getEmpNo();
		this.empName = member.getEmpName();
		this.empId = member.getEmpId();
		this.empPwd = member.getEmpPwd();
		this.empEmail = member.getEmpEmail();
		this.empGender = member.getEmpGender();
		this.empBirth = member.getEmpBirth();
		this.empAddress = member.getEmpAddress();
		this.empPhone = member.getEmpPhone();
		this.empTel = member.getEmpTel();
		this.hireDate = member.getHireDate();
		this.entDate = member.getEntDate();
		this.entYn = member.getEntYn();
		this.authority = member.getAuthority();
		this.jobCode = member.getJobCode();
		this.deptCode = member.getDeptCode();
		this.managerNo = member.getManagerNo();
	}

	public int getEmpNo() {
		return empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpId() {
		return empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public String getEmpGender() {
		return empGender;
	}

	public String getEmpBirth() {
		return empBirth;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public String getEmpTel() {
		return empTel;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public java.sql.Date getEntDate() {
		return entDate;
	}

	public String getEntYn() {
		return entYn;
	}

	public String getAuthority() {
		return authority;
	}

	public String getJobCode() {
		return jobCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public int getManagerNo() {
		return managerNo;
	}
}
