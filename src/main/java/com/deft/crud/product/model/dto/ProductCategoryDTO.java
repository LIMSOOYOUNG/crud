package com.deft.crud.product.model.dto;

public class ProductCategoryDTO {
	
	int categoryCode;
	String categoryName;
	String refCategoryName;
	int refCategoryCode;
	String categoryStatus;
	
	public ProductCategoryDTO() {}

	public ProductCategoryDTO(int categoryCode, String categoryName, String refCategoryName, int refCategoryCode,
			String categoryStatus) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.refCategoryName = refCategoryName;
		this.refCategoryCode = refCategoryCode;
		this.categoryStatus = categoryStatus;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRefCategoryName() {
		return refCategoryName;
	}

	public void setRefCategoryName(String refCategoryName) {
		this.refCategoryName = refCategoryName;
	}

	public int getRefCategoryCode() {
		return refCategoryCode;
	}

	public void setRefCategoryCode(int refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	@Override
	public String toString() {
		return "ProductCategoryDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName
				+ ", refCategoryName=" + refCategoryName + ", refCategoryCode=" + refCategoryCode + ", categoryStatus="
				+ categoryStatus + "]";
	}

	
}
