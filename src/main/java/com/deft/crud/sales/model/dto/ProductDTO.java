package com.deft.crud.sales.model.dto;

import com.deft.crud.product.model.dto.ProductCategoryDTO;

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
public class ProductDTO {
	
	private int productNo;
	private String sellStatus;
	private String productName;
	private int purchasePrice;
	private int sellingPrice;
	private String unit;
	private int chargeCount; 					// 청구건수 합계를 위해서 상품번호를 count함수로 조회
	
	private ProductCategoryDTO category;
	
}
