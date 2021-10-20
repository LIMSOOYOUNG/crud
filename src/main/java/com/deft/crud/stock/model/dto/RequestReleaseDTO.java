package com.deft.crud.stock.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestReleaseDTO {

	private String orderNo;
	private int orderProductNo;
	private int empNo;
	private int productAmount;
	private String orderReason;
	private String orderTitle;
	private int managerNo;
}
