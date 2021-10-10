package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AnaCustomerDetailHisDTO implements Serializable {

    private int anaCustomerHisNo;
    private int customerNo;
    private String customerStatus;
    private String customizationLevel;
    private int customizationPosibillity;
    private String customizationChangeDate;
}
