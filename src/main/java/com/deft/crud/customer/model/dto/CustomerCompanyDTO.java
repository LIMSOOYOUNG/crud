package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
