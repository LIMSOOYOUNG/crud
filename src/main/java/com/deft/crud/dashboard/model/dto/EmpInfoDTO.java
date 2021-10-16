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
public class EmpInfoDTO {
	
	private int empNo;
	private String empName;
	private String authority;
	private String deptCode;
	private DepartmentDTO department;
	
}
