package com.deft.crud.customer.model.dto;

import java.io.Serializable;

public class ModifyBasicInfoDTO implements Serializable {

    private int customerNo;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String businessStatus;
    private int numberOfEmployees;
    private String customerFax;
    private int companyRevenue;

    public ModifyBasicInfoDTO() {}

    public ModifyBasicInfoDTO(int customerNo, String customerName, String customerEmail, String customerPhone, String businessStatus, int numberOfEmployees, String customerFax, int companyRevenue) {
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.businessStatus = businessStatus;
        this.numberOfEmployees = numberOfEmployees;
        this.customerFax = customerFax;
        this.companyRevenue = companyRevenue;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public int getCompanyRevenue() {
        return companyRevenue;
    }

    public void setCompanyRevenue(int companyRevenue) {
        this.companyRevenue = companyRevenue;
    }

    @Override
    public String toString() {
        return "ModifyBasicInfoDTO{" +
                "customerNo=" + customerNo +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", customerFax='" + customerFax + '\'' +
                ", companyRevenue=" + companyRevenue +
                '}';
    }
}
