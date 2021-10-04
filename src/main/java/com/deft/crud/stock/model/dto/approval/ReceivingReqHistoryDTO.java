package com.deft.crud.stock.model.dto.approval;

import java.time.LocalDate;

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
public class ReceivingReqHistoryDTO {

	private int receivingReqHisNo;
	private int productNo;
	private LocalDate documentProcessDate;
	private String approvalStatus;
	private int receivingReqNo;
}
