package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO implements Serializable {

    private int customerNo;
    private int customerCompanyNo;
    private int empNo;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
    private String customerFax;

    private ExtCustomerDetailDTO extCustomerDetail;
    private AnaCustomerDetailDTO anaCustomerDetail;
    private EmpInfoDTO empInfo;

    private List<CustomerProductDTO> customerProductList;
}
