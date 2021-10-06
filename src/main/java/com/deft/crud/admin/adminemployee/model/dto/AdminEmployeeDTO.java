package com.deft.crud.admin.adminemployee.model.dto;

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
public class AdminEmployeeDTO implements java.io.Serializable{

	private int employeeNo;
	private String employeeName;
	private String employeeId;
	private String employeePwd;
	private java.util.Date employeeBirth;
	private String gender;
	private String email;
	private String empAddress;
	private String empPhone;
	private String empTel;
	private String deptName;
	private String jobName;
	private String authority;
	private int managerNo;
	private String entYn;
	private java.util.Date hireDate;
	private java.util.Date entDate;
	
	
}