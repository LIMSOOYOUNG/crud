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
public class JobDTO {
	
	private String jobCode;
    private String jobName;
    private String jobStatus;
}
