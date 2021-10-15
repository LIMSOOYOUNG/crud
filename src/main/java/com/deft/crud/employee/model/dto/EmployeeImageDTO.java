package com.deft.crud.employee.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmployeeImageDTO {

	private int empImgNo;
	private int empNo;
	private String originalName;
	private String savedName;
	private String savedPath;
}
