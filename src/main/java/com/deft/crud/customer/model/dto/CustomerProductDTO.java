package com.deft.crud.customer.model.dto;

import java.io.Serializable;

public class CustomerProductDTO implements Serializable {

    private int customerNo;
    private int productNo;

    private ProductDTO product;

    public CustomerProductDTO() {}

    public CustomerProductDTO(int customerNo, int productNo, ProductDTO product) {
        this.customerNo = customerNo;
        this.productNo = productNo;
        this.product = product;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CustomerProductDTO{" +
                "customerNo=" + customerNo +
                ", productNo=" + productNo +
                ", product=" + product +
                '}';
    }
}
