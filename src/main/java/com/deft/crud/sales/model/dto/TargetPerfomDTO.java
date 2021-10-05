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
public class TargetPerfomDTO {
	
	private int targetNo;
	private int empNo;
	private int targetSales;
	private int targetContract;
	private String performYear;
	private String performMonth;
	
}
