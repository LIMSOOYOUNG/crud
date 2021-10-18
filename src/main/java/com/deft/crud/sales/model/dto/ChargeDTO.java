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
	
	private int chargeNo;
	private String orderNo;
	private String chargeStatus;
	private Date chargeDate; 
	private int chargeCount; 	//부서 실적에서 청구건수를 조회하기 위한 별칭 사용
	
}
