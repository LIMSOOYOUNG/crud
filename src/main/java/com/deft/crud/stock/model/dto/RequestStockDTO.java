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

	private int stockProductNo;				//상품번호
	private String storageSection;			//구역
	private String storageSpace;			//칸
	private int productStock;				//상품재고수량
	private String unit;					//상품단위
	private int orderStockAmount;			//추가or삭제할 재고수량
	private String orderContent;			//요청내용
	private String orderTitle;
	
	
}
