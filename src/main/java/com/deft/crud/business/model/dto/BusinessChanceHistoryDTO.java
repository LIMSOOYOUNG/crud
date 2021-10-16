package com.deft.crud.business.model.dto;

import java.io.Serializable;

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
public class BusinessChanceHistoryDTO implements Serializable {

	private int businessChanceHisNo;
	private String businessTitle;
	private int businessChanceNo;
	private String progressStatus;
	private String salesLevel;
	private int successPosibillity;
	private String empName;
	private String businessChanceChangeDate;
	private String dueDate;
	

}
