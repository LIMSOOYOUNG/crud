package com.deft.crud.customer.model.dto;

import java.io.Serializable;

public class ModifyDetailInfoForAnaDTO implements Serializable {

    private int customerNo;
    private int customerCompanyNo;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
    private String customerFax;
    private String companyRegistNo;
    private String businessStatus;
    private String businessType;
    private int numberOfEmployees;
    private int companyRevenue;
    private String homepage;

    public ModifyDetailInfoForAnaDTO() {}

    public ModifyDetailInfoForAnaDTO(int customerNo, int customerCompanyNo, String customerName, String customerEmail, String customerAddress, String customerPhone, String customerFax, String companyRegistNo, String businessStatus, String businessType, int numberOfEmployees, int companyRevenue, String homepage) {
        this.customerNo = customerNo;
        this.customerCompanyNo = customerCompanyNo;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerFax = customerFax;
        this.companyRegistNo = companyRegistNo;
        this.businessStatus = businessStatus;
        this.businessType = businessType;
        this.numberOfEmployees = numberOfEmployees;
        this.companyRevenue = companyRevenue;
        this.homepage = homepage;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getCustomerCompanyNo() {
        return customerCompanyNo;
    }

    public void setCustomerCompanyNo(int customerCompanyNo) {
        this.customerCompanyNo = customerCompanyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public String getCompanyRegistNo() {
        return companyRegistNo;
    }

    public void setCompanyRegistNo(String companyRegistNo) {
        this.companyRegistNo = companyRegistNo;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
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

    @Override
    public String toString() {
        return "ModifyDetailInfoForAnaDTO{" +
                "customerNo=" + customerNo +
                ", customerCompanyNo=" + customerCompanyNo +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerFax='" + customerFax + '\'' +
                ", companyRegistNo='" + companyRegistNo + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", businessType='" + businessType + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", companyRevenue=" + companyRevenue +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}
