package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class BusinessActivityDTO implements Serializable {

    private int activityNo;
    private String businessSubject;
    private String businessType;
    private String businessLocation;
    private String businessPurpose;
    private String businessContents;
    private java.sql.Date writingDate;
    private java.sql.Date activityStartTime;
    private java.sql.Date activityEndTime;
    private int customerNo;
    private int empNo;

    private EmpInfoDTO empInfo;

    public BusinessActivityDTO() {}

    public BusinessActivityDTO(int activityNo, String businessSubject, String businessType, String businessLocation, String businessPurpose, String businessContents, Date writingDate, Date activityStartTime, Date activityEndTime, int customerNo, int empNo, EmpInfoDTO empInfo) {
        this.activityNo = activityNo;
        this.businessSubject = businessSubject;
        this.businessType = businessType;
        this.businessLocation = businessLocation;
        this.businessPurpose = businessPurpose;
        this.businessContents = businessContents;
        this.writingDate = writingDate;
        this.activityStartTime = activityStartTime;
        this.activityEndTime = activityEndTime;
        this.customerNo = customerNo;
        this.empNo = empNo;
        this.empInfo = empInfo;
    }

    public int getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(int activityNo) {
        this.activityNo = activityNo;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public String getBusinessPurpose() {
        return businessPurpose;
    }

    public void setBusinessPurpose(String businessPurpose) {
        this.businessPurpose = businessPurpose;
    }

    public String getBusinessContents() {
        return businessContents;
    }

    public void setBusinessContents(String businessContents) {
        this.businessContents = businessContents;
    }

    public Date getWritingDate() {
        return writingDate;
    }

    public void setWritingDate(Date writingDate) {
        this.writingDate = writingDate;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public EmpInfoDTO getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(EmpInfoDTO empInfo) {
        this.empInfo = empInfo;
    }

    @Override
    public String toString() {
        return "BusinessActivityDTO{" +
                "activityNo=" + activityNo +
                ", businessSubject='" + businessSubject + '\'' +
                ", businessType='" + businessType + '\'' +
                ", businessLocation='" + businessLocation + '\'' +
                ", businessPurpose='" + businessPurpose + '\'' +
                ", businessContents='" + businessContents + '\'' +
                ", writingDate=" + writingDate +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", customerNo=" + customerNo +
                ", empNo=" + empNo +
                ", empInfo=" + empInfo +
                '}';
    }
}
