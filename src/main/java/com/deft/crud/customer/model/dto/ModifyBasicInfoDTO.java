package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModifyBasicInfoDTO implements Serializable {

    private int customerNo;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String businessStatus;
    private int numberOfEmployees;
    private String customerFax;
    private int companyRevenue;

}
