package com.deft.crud.member.model.dto;

import com.deft.crud.organization.model.dto.DepartmentDTO;
import com.deft.crud.organization.model.dto.JobDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
	
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
	private String jobName;
	private String deptCode;
	private String deptName;
	private int managerNo;
	
	private JobDTO job;
	private DepartmentDTO dept;
	private MemberDTO manager;
}
