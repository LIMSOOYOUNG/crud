package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO implements Serializable {

    private int productNo;
    private String sellStatus;
    private String productName;
    private int purchasePrice;
    private int sellingPrice;
    private String unit;
    private int categoryCode;
    private int accountNo;
    private int manufacturerNo;

}
