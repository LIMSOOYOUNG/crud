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
public class CollectBillDTO {
	
	private int collectBillNo;
	private String collectBillAmount;
	private LocalDate collectBillDate;
	private String collectBillStatus;
	private String chargeNo;
	
	
	
	
}
