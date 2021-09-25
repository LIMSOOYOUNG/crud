package com.deft.crud.customer.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ExtCustomerDetailDTO implements Serializable {

    private int customerNo;
    private String customerStatus;
    private java.sql.Date customizationDate;

    public ExtCustomerDetailDTO() {}

    public ExtCustomerDetailDTO(int customerNo, String customerStatus, Date customizationDate) {
        this.customerNo = customerNo;
        this.customerStatus = customerStatus;
        this.customizationDate = customizationDate;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Date getCustomizationDate() {
        return customizationDate;
    }

    public void setCustomizationDate(Date customizationDate) {
        this.customizationDate = customizationDate;
    }

    @Override
    public String toString() {
        return "ExtCustomerDetailDTO{" +
                "customerNo=" + customerNo +
                ", customerStatus='" + customerStatus + '\'' +
                ", customizationDate=" + customizationDate +
                '}';
    }
}
