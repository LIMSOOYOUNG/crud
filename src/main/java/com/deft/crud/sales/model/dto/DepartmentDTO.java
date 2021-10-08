package com.deft.crud.sales.model.dto;

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
public class DepartmentDTO {
	
	private String deptCode;
    private String deptName;
    private String deptFax;
    private String deptTel;
    private String deptStatus;
}
