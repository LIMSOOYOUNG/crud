package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerChargeDTO implements Serializable {

    private int chargeNo;
    private String orderNo;
    private String chargeStatus;
    private String collectBillStatus;
    private String chargeDate;
}
