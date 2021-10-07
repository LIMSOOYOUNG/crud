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
public class ApprovalModifyDTO {

	private int receivingReqNo;
	private int approvalDocumentNo;
	private String approvalDocumentType;
	private String approvalStatus;
	private String documentProcessDate;
}
