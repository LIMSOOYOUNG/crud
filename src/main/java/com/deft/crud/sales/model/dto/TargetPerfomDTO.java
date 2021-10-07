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

/* 사원의 목표 실적을 조회, 등록을 위한 DTO*/
public class TargetPerfomDTO {
	
	private int targetNo;
	private int empNo;
	private int targetSales;
	private int targetContract;
	private String performYear;
	private String performMonth;
	
}
