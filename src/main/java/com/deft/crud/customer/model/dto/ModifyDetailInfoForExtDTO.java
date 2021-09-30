package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ModifyDetailInfoForExtDTO implements Serializable {

    private int customerNo;
    private String customerName;
    private String companyRegistNo;
    private String customerPhone;
    private String businessStatus;
    private String customerEmail;
    private String businessType;
    private String customerAddress;
    private int numberOfEmployees;
    private java.sql.Date customizationDate;
    private int companyRevenue;
    private String homepage;
    private String customerFax;
    private int customerCompanyNo;

    public ModifyDetailInfoForExtDTO() {}

    public ModifyDetailInfoForExtDTO(int customerNo, String customerName, String companyRegistNo, String customerPhone, String businessStatus, String customerEmail, String businessType, String customerAddress, int numberOfEmployees, Date customizationDate, int companyRevenue, String homepage, String customerFax, int customerCompanyNo) {
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.companyRegistNo = companyRegistNo;
        this.customerPhone = customerPhone;
        this.businessStatus = businessStatus;
        this.customerEmail = customerEmail;
        this.businessType = businessType;
        this.customerAddress = customerAddress;
        this.numberOfEmployees = numberOfEmployees;
        this.customizationDate = customizationDate;
        this.companyRevenue = companyRevenue;
        this.homepage = homepage;
        this.customerFax = customerFax;
        this.customerCompanyNo = customerCompanyNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyRegistNo() {
        return companyRegistNo;
    }

    public void setCompanyRegistNo(String companyRegistNo) {
        this.companyRegistNo = companyRegistNo;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Date getCustomizationDate() {
        return customizationDate;
    }

    public void setCustomizationDate(Date customizationDate) {
        this.customizationDate = customizationDate;
    }

    public int getCompanyRevenue() {
        return companyRevenue;
    }

    public void setCompanyRevenue(int companyRevenue) {
        this.companyRevenue = companyRevenue;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public int getCustomerCompanyNo() {
        return customerCompanyNo;
    }

    public void setCustomerCompanyNo(int customerCompanyNo) {
        this.customerCompanyNo = customerCompanyNo;
    }

    @Override
    public String toString() {
        return "ModifyDetailInfoForExtDTO{" +
                "customerNo=" + customerNo +
                ", customerName='" + customerName + '\'' +
                ", companyRegistNo='" + companyRegistNo + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", businessType='" + businessType + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", customizationDate=" + customizationDate +
                ", companyRevenue=" + companyRevenue +
                ", homepage='" + homepage + '\'' +
                ", customerFax='" + customerFax + '\'' +
                ", customerCompanyNo=" + customerCompanyNo +
                '}';
    }
}
