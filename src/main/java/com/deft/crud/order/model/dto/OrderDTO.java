package com.deft.crud.order.model.dto;

import java.io.Serializable;
import java.util.List;

import com.deft.crud.customer.model.dto.CustomerDTO;
import com.deft.crud.estimate.model.dto.ProviderDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;

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
public class OrderDTO implements Serializable {

	private String orderNo;
	private String orderTitle;
	private int customerNo;
	private int sumPrice;
	private String discountStatus;
	private int discountRate;
	private String paymentStatus;
	private String orderDate;
	private String dueDate;
	private String orderYn;
	private String deliveryPlace;
	private String zipCode;
	private String address;
	private String addressDetail;
	private String providerRegistNo;
	
	private CustomerDTO customer;
	private ProviderDTO provider;
	private List<OrderProductDTO> orderProductList;
}
	
