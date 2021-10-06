package com.deft.crud.stock.model.dto.approval;

import java.util.List;

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
public class ReceivingReqDTO {

	private int receivingReqNo;			//입고요청서번호
	private int approvalDocumentNo;		//결재문서번호
	
	private ApprovalDocumentDTO approvalDocumentDTO;
	private ReceivingReqHistoryDTO receivingReqHistoryDTO;
	private List<ReceivingReqProductDTO> receivingReqProductList;
}
