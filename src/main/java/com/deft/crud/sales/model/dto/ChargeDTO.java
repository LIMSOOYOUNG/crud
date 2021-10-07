package com.deft.crud.sales.model.dto;

import java.sql.Date;

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
public class ChargeDTO {
	
	private int collectBillNo;
	private int collectBillAmount;
	private Date collectBillDate;
	private int chargeNo; 
	private String chargeYear;
	private String chargeMonth;
	
}
