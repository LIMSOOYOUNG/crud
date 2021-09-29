package com.deft.crud.stock.model.dto;

public class StockDTO {

	private int productNo;			//상품번호
	private String sellStatus;		//판매상태
	private String productName;		//상품명
	private String unit;			//단위
	private String categoryName;	//카테고리명
	private String storageSection;	//구역
	private String storageSpace;	//칸
	private int productStock;		//재고
	
	public StockDTO() {}

	public StockDTO(int productNo, String sellStatus, String productName, String unit, String categoryName,
			String storageSection, String storageSpace, int productStock) {
		super();
		this.productNo = productNo;
		this.sellStatus = sellStatus;
		this.productName = productName;
		this.unit = unit;
		this.categoryName = categoryName;
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
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		return "StockDTO [productNo=" + productNo + ", sellStatus=" + sellStatus + ", productName=" + productName
				+ ", unit=" + unit + ", categoryName=" + categoryName + ", storageSection=" + storageSection
				+ ", storageSpace=" + storageSpace + ", productStock=" + productStock + "]";
	}


	
}
