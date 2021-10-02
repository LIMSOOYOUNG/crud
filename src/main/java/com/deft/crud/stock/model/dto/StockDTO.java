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
public class StockDTO implements Serializable {

	private int productNo;			//상품번호
	private String sellStatus;		//판매상태
	private String productName;		//상품명
	private String unit;			//단위
	private String categoryName;	//카테고리명
	private String storageSection;	//구역
	private String storageSpace;	//칸
	private int productStock;		//재고
	
}
