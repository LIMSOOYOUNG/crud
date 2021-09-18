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

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(MemberDTO member) {
		this.empNo = member.getEmpNo();
		this.empName = member.getEmpName();
		this.empId = member.getEmpId();
		this.empPwd = member.getEmpPwd();
		this.empEmail = member.getEmpEmail();
		this.gender = member.getGender();
		this.empBirth = member.getEmpBirth();
		this.hireYn = member.getHireYn();
		this.authority = member.getAuthority();
		this.hireDate = member.getHireDate();
		this.entDate = member.getEntDate();
		this.empAddress = member.getEmpAddress();
		this.empPhone = member.getEmpPhone();
		this.empTel = member.getEmpTel();
		this.empFax = member.getEmpFax();
		this.jobNo = member.getJobNo();
		this.deptNo = member.getDeptNo();
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

	public String getGender() {
		return gender;
	}

	public String getEmpBirth() {
		return empBirth;
	}

	public String getHireYn() {
		return hireYn;
	}

	public String getAuthority() {
		return authority;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public java.sql.Date getEntDate() {
		return entDate;
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

	public String getEmpFax() {
		return empFax;
	}

	public String getJobNo() {
		return jobNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public int getManagerNo() {
		return managerNo;
	}
}
