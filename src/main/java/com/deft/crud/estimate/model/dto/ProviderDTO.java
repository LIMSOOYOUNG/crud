package com.deft.crud.estimate.model.dto;

import java.io.Serializable;

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
public class ProviderDTO implements Serializable {
	
	private String providerRegistNo;
	private String providerName;
	private String providerCeo;
	private String providerAddress;
	private String providerBusinessStatus;
	private String providerBusinessType;
}
