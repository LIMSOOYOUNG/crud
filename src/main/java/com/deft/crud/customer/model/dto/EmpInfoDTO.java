package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpInfoDTO implements Serializable {

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
