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

/* 사원 실적을 조회하기 위한 DTO */
public class PerformanceDTO {
	
	private int performanceNo;
	private int empNo;
	private int performanceCount;			// 계약한 횟수를 별칭으로 처리해야되서 디비 컬럼 명과 같지 않지만 필드에 선언을 해줌
	
	private CollectBillDTO collect;
	private ChargeDTO charge;
	private PurchaseOrderDTO purchaseOrder;
	private EmployeeDTO employeeInfo;
	
}
