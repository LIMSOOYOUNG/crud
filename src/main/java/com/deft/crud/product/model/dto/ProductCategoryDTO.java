package com.deft.crud.product.model.dto;

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
public class ProductCategoryDTO {
	
	private int categoryCode;
	private String categoryName;
	private int refCategoryCode;
	private String refCategoryName;
	private String categoryStatus;
	
}
