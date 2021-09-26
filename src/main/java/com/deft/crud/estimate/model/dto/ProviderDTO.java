package com.deft.crud.estimate.model.dto;

import java.io.Serializable;

public class ProviderDTO implements Serializable {
	
	private String providerRegistNo;
	private String providerName;
	private String providerCeo;
	private String providerAddress;
	private String providerBusinessStatus;
	private String providerBusinessType;
	
	public ProviderDTO() {}

	public ProviderDTO(String providerRegistNo, String providerName, String providerCeo, String providerAddress,
			String providerBusinessStatus, String providerBusinessType) {
		super();
		this.providerRegistNo = providerRegistNo;
		this.providerName = providerName;
		this.providerCeo = providerCeo;
		this.providerAddress = providerAddress;
		this.providerBusinessStatus = providerBusinessStatus;
		this.providerBusinessType = providerBusinessType;
	}

	public String getProviderRegistNo() {
		return providerRegistNo;
	}

	public void setProviderRegistNo(String providerRegistNo) {
		this.providerRegistNo = providerRegistNo;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderCeo() {
		return providerCeo;
	}

	public void setProviderCeo(String providerCeo) {
		this.providerCeo = providerCeo;
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}

	public String getProviderBusinessStatus() {
		return providerBusinessStatus;
	}

	public void setProviderBusinessStatus(String providerBusinessStatus) {
		this.providerBusinessStatus = providerBusinessStatus;
	}

	public String getProviderBusinessType() {
		return providerBusinessType;
	}

	public void setProviderBusinessType(String providerBusinessType) {
		this.providerBusinessType = providerBusinessType;
	}

	@Override
	public String toString() {
		return "ProviderDTO [providerRegistNo=" + providerRegistNo + ", providerName=" + providerName + ", providerCeo="
				+ providerCeo + ", providerAddress=" + providerAddress + ", providerBusinessStatus="
				+ providerBusinessStatus + ", providerBusinessType=" + providerBusinessType + "]";
	}
}
