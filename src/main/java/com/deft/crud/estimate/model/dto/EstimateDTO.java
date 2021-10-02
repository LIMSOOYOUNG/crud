package com.deft.crud.estimate.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.deft.crud.customer.model.dto.CustomerDTO;

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
public class EstimateDTO implements Serializable {
	
	private String estimateNo;
	private String estimateTitle;
	private LocalDate estimateDate;
	private int customerNo;
	private double discountRate;
	private String estimateStatus;
	private String providerRegistNo;
	
	private CustomerDTO customer;
	private ProviderDTO provider;
}
