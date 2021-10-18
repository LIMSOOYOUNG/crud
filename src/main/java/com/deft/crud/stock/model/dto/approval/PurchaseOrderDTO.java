package com.deft.crud.stock.model.dto.approval;

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
public class PurchaseOrderDTO {

	private int orderNo;				//주문서번호
	private String orderDate;			//주문요청일자
	private int discountRate;			//할인율
	private String dueDate;				//납기일
	private String deliveryPlace;		//납품장소
	private String paymentStatus;		//대금결제조건
	private String orderTitle;			//주문서제목
	private int customerNo;				//고객번호
	private String customerName;		//고객명
	private String providerRegistNo;	//공급자등록번호	
	private int sumPrice;				//합계금액
	private String orderYn;				//주문완료여부	

	private PurchaseOrderProductDTO orderProductDTO;	//주문상품목록
}
