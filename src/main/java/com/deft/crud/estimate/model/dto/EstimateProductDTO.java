package com.deft.crud.estimate.model.dto;

import java.io.Serializable;

import com.deft.crud.product.model.dto.ProductDTO;

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
public class EstimateProductDTO implements Serializable {
	
	private String estimateNo;
	private int productNo;
	private int productAmount;
	
	private ProductDTO product;
}
