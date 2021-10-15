package com.deft.crud.admin.adminemployee.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate employeeBirth;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate entDate;
	private String managerName;
	private String jobCode;
	private String deptCode;
	
	private DepartmentDTO dept;
	private JobDTO job;
	private EmployeeImageDTO empImage;
}