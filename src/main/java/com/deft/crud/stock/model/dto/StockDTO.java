package com.deft.crud.stock.model.dto;

public class StockDTO {

	private int productNo;
	private String sellStatus;
	private String ProductName;
	private String unit;
	private int categoryCode;
	private String storageSection;
	private String storageSpace;
	private int productStock;
	
	public StockDTO() {}

	public StockDTO(int productNo, String sellStatus, String productName, String unit, int categoryCode,
			String storageSection, String storageSpace, int productStock) {
		super();
		this.productNo = productNo;
		this.sellStatus = sellStatus;
		ProductName = productName;
		this.unit = unit;
		this.categoryCode = categoryCode;
		this.storageSection = storageSection;
		this.storageSpace = storageSpace;
		this.productStock = productStock;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
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

	@Override
	public String toString() {
		return "StockDTO [productNo=" + productNo + ", sellStatus=" + sellStatus + ", ProductName=" + ProductName
				+ ", unit=" + unit + ", categoryCode=" + categoryCode + ", storageSection=" + storageSection
				+ ", storageSpace=" + storageSpace + ", productStock=" + productStock + "]";
	}
	
	
}
