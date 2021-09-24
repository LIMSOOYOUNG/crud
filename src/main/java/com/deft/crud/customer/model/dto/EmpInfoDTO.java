package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.sql.Date;

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

    public EmpInfoDTO() {}

    public EmpInfoDTO(int empNo, String empName, String empId, String empPwd, String empEmail, String empGender, Date empBirth, String empAddress, String empPhone, String empTel, Date hireDate, Date entDate, String entYn, String authority, String jobCode, String deptCode, int managerNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.empId = empId;
        this.empPwd = empPwd;
        this.empEmail = empEmail;
        this.empGender = empGender;
        this.empBirth = empBirth;
        this.empAddress = empAddress;
        this.empPhone = empPhone;
        this.empTel = empTel;
        this.hireDate = hireDate;
        this.entDate = entDate;
        this.entYn = entYn;
        this.authority = authority;
        this.jobCode = jobCode;
        this.deptCode = deptCode;
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

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public Date getEmpBirth() {
        return empBirth;
    }

    public void setEmpBirth(Date empBirth) {
        this.empBirth = empBirth;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getEntDate() {
        return entDate;
    }

    public void setEntDate(Date entDate) {
        this.entDate = entDate;
    }

    public String getEntYn() {
        return entYn;
    }

    public void setEntYn(String entYn) {
        this.entYn = entYn;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(int managerNo) {
        this.managerNo = managerNo;
    }

    @Override
    public String toString() {
        return "EmpInfoDTO{" +
                "empNo=" + empNo +
                ", empName='" + empName + '\'' +
                ", empId='" + empId + '\'' +
                ", empPwd='" + empPwd + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empGender='" + empGender + '\'' +
                ", empBirth=" + empBirth +
                ", empAddress='" + empAddress + '\'' +
                ", empPhone='" + empPhone + '\'' +
                ", empTel='" + empTel + '\'' +
                ", hireDate=" + hireDate +
                ", entDate=" + entDate +
                ", entYn='" + entYn + '\'' +
                ", authority='" + authority + '\'' +
                ", jobCode='" + jobCode + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", managerNo=" + managerNo +
                '}';
    }
}
