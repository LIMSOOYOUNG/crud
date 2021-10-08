package com.deft.crud.sales.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 부서별 실적 조회를 위한 DTO */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
	
	private int empNo;
    private String empName;
    private String empId;
    private String empPwd;
    private String empEmail;
    private String empGender;
    private java.sql.Date empBirth;
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
    
    private JobDTO job;
    private DepartmentDTO dept;
	
}
