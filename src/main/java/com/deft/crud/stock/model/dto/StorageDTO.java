package com.deft.crud.stock.model.dto;

import java.io.Serializable;

public class StorageDTO implements Serializable {

	private String storageSection;
	private String storageSpace;
	private int productStock;
	private int productNo;
	
	public StorageDTO() {}

	public StorageDTO(String storageSection, String storageSpace, int productStock, int productNo) {
		super();
		this.storageSection = storageSection;
		this.storageSpace = storageSpace;
		this.productStock = productStock;
		this.productNo = productNo;
	}

	public String getStorageSection() {
		return storageSection;
	}

	public void setStorageSection(String storageSection) {
		this.storageSection = storageSection;
	}

	public String getStorageSpace() {
		return storageSpace;
	}

	public void setStorageSpace(String storageSpace) {
		this.storageSpace = storageSpace;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "StockDTO [storageSection=" + storageSection + ", storageSpace=" + storageSpace + ", productStock="
				+ productStock + ", productNo=" + productNo + "]";
	}
	
	
}


