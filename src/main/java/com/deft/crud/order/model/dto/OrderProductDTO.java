package com.deft.crud.order.model.dto;

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
public class OrderProductDTO implements Serializable {
	
	private String orderNo;
	private int productNo;
	private int productAmount;
	private int productStock;
	
	private ProductDTO product;
}
