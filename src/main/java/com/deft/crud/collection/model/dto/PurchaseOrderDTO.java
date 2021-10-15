package com.deft.crud.collection.model.dto;

import java.time.LocalDate;

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
	private LocalDate orderDate;
	private int discountDate;
	private LocalDate dueDate;
	private String deliveryPlace;
	private String paymentStatus;
	private String orderTitle;
	private int customerNo;
	private int providerRegistNo;
	private int sumPrice;
}
