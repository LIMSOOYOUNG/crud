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
public class BusinessActivityDTO implements Serializable {

	private int activityNo;
	private String activityName;
	private String customerName;
	private int customerNo;
	private String empName;
	private int empNo;
	private String businessSubject;
	private String businessType;
	private String businessLocation;
	private String businessPurpose;
	private String businessContents;
	private java.sql.Date writingTime;
	private java.sql.Date activityStartTime;
	private java.sql.Date activityEndTime;

}
