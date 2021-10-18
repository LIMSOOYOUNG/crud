package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderChargeDTO implements Serializable {

    private String orderNo;
    private String orderDate;
    private int discountRate;
    private String dueDate;
    private String deliveryPlace;
    private String paymentStatus;
    private String orderTitle;
    private int customerNo;
    private String providerRegistNo;
    private int sumPrice;

    private CustomerChargeDTO charge;
}
