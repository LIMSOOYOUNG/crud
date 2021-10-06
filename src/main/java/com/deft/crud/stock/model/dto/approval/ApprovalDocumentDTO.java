package com.deft.crud.stock.model.dto.approval;

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
public class ApprovalDocumentDTO {

	private int approvalDocumentNo;
	private String approvalDocumentType;
	private String approvalDocumentName;
	private String reqReason;
	private int empNo;
	private String empName;
	private int managerNo;
	private String managerName;
	
	
}
