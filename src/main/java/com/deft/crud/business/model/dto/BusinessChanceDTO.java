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
public class BusinessChanceDTO implements Serializable {

	private int businessChanceNo;
	private String progressStatus;
	private String salesLevel;
	private int successPosibillity;
	private String businessTitle;
	private java.sql.Date dueDate;
	private String empName;
	private int empNo;
	private String customerName;
	private int customerNo;
	private String productName;
	private String customerCompanyName;
	

}
