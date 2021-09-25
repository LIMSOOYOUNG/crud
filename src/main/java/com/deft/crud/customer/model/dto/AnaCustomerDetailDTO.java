package com.deft.crud.customer.model.dto;

import java.io.Serializable;

public class AnaCustomerDetailDTO implements Serializable {

    private int customerNo;
    private String customerStatus;
    private String customizationLevel;
    private int customizationPosibillity;

    public AnaCustomerDetailDTO() {}

    public AnaCustomerDetailDTO(int customerNo, String customerStatus, String customizationLevel, int customizationPosibillity) {
        this.customerNo = customerNo;
        this.customerStatus = customerStatus;
        this.customizationLevel = customizationLevel;
        this.customizationPosibillity = customizationPosibillity;
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

    public String getCustomizationLevel() {
        return customizationLevel;
    }

    public void setCustomizationLevel(String customizationLevel) {
        this.customizationLevel = customizationLevel;
    }

    public int getCustomizationPosibillity() {
        return customizationPosibillity;
    }

    public void setCustomizationPosibillity(int customizationPosibillity) {
        this.customizationPosibillity = customizationPosibillity;
    }

    @Override
    public String toString() {
        return "AnaCustomerDetailDTO{" +
                "customerNo=" + customerNo +
                ", customerStatus='" + customerStatus + '\'' +
                ", customizationLevel='" + customizationLevel + '\'' +
                ", customizationPosibillity=" + customizationPosibillity +
                '}';
    }
}
