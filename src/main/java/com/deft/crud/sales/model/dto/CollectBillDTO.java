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
public class CollectBillDTO {
	
	private int collectBillNo;
	private int chargeNo;
	private int collectBillAmount;
	private String collectBillStatus;
	private Date collectBillDate;
	
}
