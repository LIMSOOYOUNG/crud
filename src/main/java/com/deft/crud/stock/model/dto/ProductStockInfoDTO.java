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
public class ProductStockInfoDTO implements Serializable {

	private String stockRefCategoryName;	//카테고리명(중)
	private String stockCategoryName;		//카테고리명(소)
	private int stockProductNo;				//상품번호
	private String stockProductName;		//상품명
	private String manufacturerName;		//제조사명
	private String companyName;				//거래처명
	private String storageSection;			//구역
	private String storageSpace;			//칸
	private int sellingPrice;				//판매가
	private String sellStatus;				//판매상태
	private int productStock;				//상품재고수량
	private String unit;					//상품단위
	private String thumbnailPath;			//상품이미지파일경로
	
}
