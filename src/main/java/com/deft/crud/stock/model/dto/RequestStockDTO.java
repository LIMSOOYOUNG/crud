package com.deft.crud.stock.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestStockDTO implements Serializable {

	private int productNo;
	private String productName;
	private String unit;
	private int requestOrderStock;

	
	
}
