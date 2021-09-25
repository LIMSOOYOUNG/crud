package com.deft.crud.customer.model.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private int productNo;
    private String sellStatus;
    private String productName;
    private int purchasePrice;
    private int sellingPrice;
    private String unit;
    private int categoryCode;
    private int accountNo;
    private int manufacturerNo;

    public ProductDTO() {}

    public ProductDTO(int productNo, String sellStatus, String productName, int purchasePrice, int sellingPrice, String unit, int categoryCode, int accountNo, int manufacturerNo) {
        this.productNo = productNo;
        this.sellStatus = sellStatus;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.unit = unit;
        this.categoryCode = categoryCode;
        this.accountNo = accountNo;
        this.manufacturerNo = manufacturerNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getManufacturerNo() {
        return manufacturerNo;
    }

    public void setManufacturerNo(int manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNo=" + productNo +
                ", sellStatus='" + sellStatus + '\'' +
                ", productName='" + productName + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", unit='" + unit + '\'' +
                ", categoryCode=" + categoryCode +
                ", accountNo=" + accountNo +
                ", manufacturerNo=" + manufacturerNo +
                '}';
    }
}
