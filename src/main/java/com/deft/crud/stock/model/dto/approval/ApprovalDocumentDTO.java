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

	private int approvalDocumentNo;			//결재문서번호
	private String approvalDocumentType;	//결재문서종류	
	private String approvalDocumentName;	//제목
	private String reqReason;				//요청사유
	private int empNo;						//사원번호
	private String empName;					//사원명
	private int managerNo;					//매니저번호
	private String managerName;				//매니저명
	private String documentStatus; 			//결재상태
	private String documentProcessDate;		//결재일
	private String documentWriteDate; 		//작성일
	
	
}
