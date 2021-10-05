package com.deft.crud.sales.model.dto;

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
public class PerformanceDTO {
	
	private int performanceNo;
	private CollectBillDTO collect;
	private int empNo;
	private int chargeNo;
	private int performanceCount;
	
}
