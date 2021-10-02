package com.deft.crud.customer.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExtCustomerDTO implements Serializable {
	
	private int customerNo;
    private int customerCompanyNo;
    private int empNo;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
    private String customerFax;
    
    private CustomerCompanyDTO company;
    private EmpInfoDTO empInfo;
    private ExtCustomerDetailDTO extCustomerDetail;
    private AnaCustomerDetailDTO anaCustomerDetail;
}
