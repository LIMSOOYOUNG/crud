package com.deft.crud.admin.adminemployee.model.dto;

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
public class DepartmentDTO implements java.io.Serializable{

	private String deptCode;
	private String deptName;
	private String deptTel;
	private String deptFax;
	private String deptStatus;
	
	
	
}
