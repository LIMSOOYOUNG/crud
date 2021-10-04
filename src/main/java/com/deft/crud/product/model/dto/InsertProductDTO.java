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
public class InsertProductDTO {
	
	private int productNo;
	private String productName;
	private int purchasePrice;
	private int sellingPrice;
	private String unit;
	private String sellStatus;
	
	private int refCategoryCode;
	
	private int categoryCode;
	
	private int accountNo;

	private int manufacturerNo;
	
}
