package com.deft.crud.product.model.dto;

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
public class AccountDTO {
	
	private int accountNo;
	private String companyName;
	private String accountCeo;
	private String accountAddress;
	private String accountStatus;
	private String accountBusinessItem;
	private String resName;
	private String resPhone;
	private String resEmail;
	
	
}
