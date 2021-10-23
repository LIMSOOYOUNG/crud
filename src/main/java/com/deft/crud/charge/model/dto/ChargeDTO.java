package com.deft.crud.charge.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.deft.crud.order.model.dto.OrderDTO;
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
public class ChargeDTO implements Serializable {

	private String chargeNo;
	private String orderNo;
	private String chargeStatus;
	private LocalDate chargeDate;
	
	private OrderDTO order;
}
	
