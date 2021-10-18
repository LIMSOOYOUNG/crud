package com.deft.crud.collection.model.dto;

import java.time.LocalDate;
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
public class ChargeDTO {

	private int chargeNo;
	private String orderNo;
	private String chargeStatus;
	private LocalDate chargeDate;
	private int chargeYear;
	private int chargeMonth;
	
	private PurchaseOrderDTO purchaseOrder;
	private CustomerDTO customer;
	private List<CollectBillDTO> collectBill;
	
	
}
