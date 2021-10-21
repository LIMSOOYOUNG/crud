package com.deft.crud.employee.model.dto;

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
public class EmployeeDTO implements java.io.Serializable {
							
	private int employeeNo;
	private String employeeName;
	private String employeeId;
	private String employeePwd;
	private java.util.Date employeeBirth;
	private String gender;
	private String email;
	private String address;
	private String phone;
	private String tel;
	private String deptName;
	private String jobName;
	private String authority;
	private int mangerNo;
	private String fax;
	private String hireYn;
	private java.util.Date hireDate;
	private java.util.Date entDate;
	
	private EmployeeImageDTO empImage;
	
}
