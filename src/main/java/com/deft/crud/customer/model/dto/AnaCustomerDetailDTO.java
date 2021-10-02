package com.deft.crud.customer.model.dto;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnaCustomerDetailDTO implements Serializable {

    private int customerNo;
    private String customerStatus;
    private String customizationLevel;
    private int customizationPosibillity;
}
