package com.deft.crud.product.model.dto;

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
public class ProductImageDTO {
	
	private int productImageNo;
	private String originalName;
	private String savedName;
	private String savedPath;
	private int productNo;
	private String thumbNailPath;
	
}
