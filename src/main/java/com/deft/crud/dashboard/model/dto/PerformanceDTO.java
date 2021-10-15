package com.deft.crud.dashboard.model.dto;

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
	private EmpInfoDTO empInfo;
	private CollectBillDTO collect;
}
