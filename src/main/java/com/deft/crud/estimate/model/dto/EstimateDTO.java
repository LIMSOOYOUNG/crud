package com.deft.crud.estimate.model.dto;

import java.io.Serializable;
import java.sql.Date;

import com.deft.crud.customer.model.dto.CustomerDTO;

public class EstimateDTO implements Serializable {
	
	private String estimateNo;
	private String estimateTitle;
	private java.sql.Date estimateDate;
	private int customerNo;
	private double discountRate;
	private String estimateStatus;
	private String providerRegistNo;
	
	private CustomerDTO customer;
	private ProviderDTO provider;
	
	public EstimateDTO() {}

	public EstimateDTO(String estimateNo, String estimateTitle, Date estimateDate, int customerNo, double discountRate,
			String estimateStatus, String providerRegistNo, CustomerDTO customer, ProviderDTO provider) {
		super();
		this.estimateNo = estimateNo;
		this.estimateTitle = estimateTitle;
		this.estimateDate = estimateDate;
		this.customerNo = customerNo;
		this.discountRate = discountRate;
		this.estimateStatus = estimateStatus;
		this.providerRegistNo = providerRegistNo;
		this.customer = customer;
		this.provider = provider;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getEstimateTitle() {
		return estimateTitle;
	}

	public void setEstimateTitle(String estimateTitle) {
		this.estimateTitle = estimateTitle;
	}

	public java.sql.Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(java.sql.Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public String getEstimateStatus() {
		return estimateStatus;
	}

	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	public String getProviderRegistNo() {
		return providerRegistNo;
	}

	public void setProviderRegistNo(String providerRegistNo) {
		this.providerRegistNo = providerRegistNo;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public ProviderDTO getProvider() {
		return provider;
	}

	public void setProvider(ProviderDTO provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "EstimateDTO [estimateNo=" + estimateNo + ", estimateTitle=" + estimateTitle + ", estimateDate="
				+ estimateDate + ", customerNo=" + customerNo + ", discountRate=" + discountRate + ", estimateStatus="
				+ estimateStatus + ", providerRegistNo=" + providerRegistNo + ", customer=" + customer + ", provider="
				+ provider + "]";
	}
}
