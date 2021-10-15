package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExtCustomerDetailDTO implements Serializable {

    private int customerNo;
    private String customerStatus;
    private String customizationDate;

}
