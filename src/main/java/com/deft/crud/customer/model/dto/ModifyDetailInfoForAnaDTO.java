package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
