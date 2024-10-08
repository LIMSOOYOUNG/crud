package com.deft.crud.customer.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerProductDTO implements Serializable {

    private int customerNo;
    private int productNo;

    private ProductDTO product;

}
