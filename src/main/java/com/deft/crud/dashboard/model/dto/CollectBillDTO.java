package com.deft.crud.dashboard.model.dto;

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
public class CollectBillDTO {
	
	private int collectBillNo; 
	private int collectBillAmount;
	private int collectBillYear;			 // 디비에는 Date 타입으로 설정되어 있는 수금 날짜를 연도 별로 조회하기 위해 별칭 선언
	private int collectBillMonth;         // 위의 내용과 같음 월을 조회하기 위해 별칭 선언
	private String cllectBillStatus;
	
	
}
