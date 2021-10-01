package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
