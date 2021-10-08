package com.deft.crud.admin.adminemployee.model.dto;

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
public class JobDTO implements java.io.Serializable{

	private String jobCode;
	private String jobName;
	private String jobStatus;
	
	
}
