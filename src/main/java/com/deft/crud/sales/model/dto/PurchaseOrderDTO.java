package com.deft.crud.sales.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchaseOrderDTO {
	
	private int orderNo;
	private Date orderDate;
	private int discountDate;
	private Date dueDate;
	private String deliveryPlace;
	private String paymentStatus;
	private String orderTitle;
	private int customerNo;
	private int providerRegistNo;
	private int sumPrice;
	
	List<ProductDTO> productList;
}
