package com.deft.crud.product.model.dto;

public class ManufacturerDTO {
	
	private int manufacturerNo;
	private String manufacturerName;
	
	public ManufacturerDTO() {}

	public ManufacturerDTO(int manufacturerNo, String manufacturerName) {
		super();
		this.manufacturerNo = manufacturerNo;
		this.manufacturerName = manufacturerName;
	}

	public int getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(int manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	@Override
	public String toString() {
		return "ManufacturerDTO [manufacturerNo=" + manufacturerNo + ", manufacturerName=" + manufacturerName + "]";
	}
	
	
	
}
