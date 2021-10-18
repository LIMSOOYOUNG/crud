package com.deft.crud.order.model.dto;

import java.time.LocalDate;

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
public class OrderDTO {

	private int orderNo;
	private String orderTitle;
	private int customerNo;
	private int sumPrice;
	private int discountRate;
	private String paymentStatus;
	private LocalDate orderDate;
	private LocalDate dueDate;
	private String deliveryPlace;
	private String providerRegistNo;
}
	
