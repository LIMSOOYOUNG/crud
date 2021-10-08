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
public class ProductDTO {
	
	private int productNo;
	private String sellStatus;
	private String productName;
	private int purchasePrice;
	private int sellingPrice;
	private int discountedPrice;
	private int subtotal;
	private int tax;
	private String unit;
	private int categoryCode;
	private int accountNo;
	private int manufacturerNo;

	private ProductCategoryDTO category;
	private ManufacturerDTO manufacturer;
	private AccountDTO account;
	
}
