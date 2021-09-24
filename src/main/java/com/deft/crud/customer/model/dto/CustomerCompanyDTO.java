package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerCompanyDTO implements Serializable {

    private int customerCompanyNo;
    private String companyRegistNo;
    private String businessStatus;
    private String businessType;
    private int numberOfEmployees;
    private int companyRevenue;
    private String homepage;
    private String companyAddress;
    private String companyName;
    private String ceoName;

    private List<CustomerDTO> customerList;

    public CustomerCompanyDTO() {}

    public CustomerCompanyDTO(int customerCompanyNo, String companyRegistNo, String businessStatus, String businessType, int numberOfEmployees, int companyRevenue, String homepage, String companyAddress, String companyName, String ceoName, List<CustomerDTO> customerList) {
        this.customerCompanyNo = customerCompanyNo;
        this.companyRegistNo = companyRegistNo;
        this.businessStatus = businessStatus;
        this.businessType = businessType;
        this.numberOfEmployees = numberOfEmployees;
        this.companyRevenue = companyRevenue;
        this.homepage = homepage;
        this.companyAddress = companyAddress;
        this.companyName = companyName;
        this.ceoName = ceoName;
        this.customerList = customerList;
    }

    public int getCustomerCompanyNo() {
        return customerCompanyNo;
    }

    public void setCustomerCompanyNo(int customerCompanyNo) {
        this.customerCompanyNo = customerCompanyNo;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public List<CustomerDTO> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerDTO> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "CustomerCompanyDTO{" +
                "customerCompanyNo=" + customerCompanyNo +
                ", companyRegistNo='" + companyRegistNo + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", businessType='" + businessType + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", companyRevenue=" + companyRevenue +
                ", homepage='" + homepage + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ceoName='" + ceoName + '\'' +
                ", customerList=" + customerList +
                '}';
    }
}
