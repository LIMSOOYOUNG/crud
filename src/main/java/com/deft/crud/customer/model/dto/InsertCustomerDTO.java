package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InsertCustomerDTO implements Serializable {

    private int customerNo;
    private int customerCompanyNo;
    private int empNo;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
    private String customerFax;
    private int productNo;
}
