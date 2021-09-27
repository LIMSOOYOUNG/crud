package com.deft.crud.product.model.dto;

public class ProductDTO {
	
	private int productNo;
	private String sellStatus;
	private String productName;
	private int purchasePrice;
	private int sellingPrice;
	private String unit;
	private int accountNo;
	private int manufacturerNo;
	private ProductCategoryDTO category;
	private ManufacturerDTO manufacturer;
	private AccountDTO account;
	
	public ProductDTO() {}

	public ProductDTO(int productNo, String sellStatus, String productName, int purchasePrice, int sellingPrice,
			String unit, int accountNo, int manufacturerNo, ProductCategoryDTO category, ManufacturerDTO manufacturer,
			AccountDTO account) {
		super();
		this.productNo = productNo;
		this.sellStatus = sellStatus;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.unit = unit;
		this.accountNo = accountNo;
		this.manufacturerNo = manufacturerNo;
		this.category = category;
		this.manufacturer = manufacturer;
		this.account = account;
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

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(int manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public ProductCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryDTO category) {
		this.category = category;
	}

	public ManufacturerDTO getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDTO manufacturer) {
		this.manufacturer = manufacturer;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "ProductDTO [productNo=" + productNo + ", sellStatus=" + sellStatus + ", productName=" + productName
				+ ", purchasePrice=" + purchasePrice + ", sellingPrice=" + sellingPrice + ", unit=" + unit
				+ ", accountNo=" + accountNo + ", manufacturerNo=" + manufacturerNo + ", category=" + category
				+ ", manufacturer=" + manufacturer + ", account=" + account + "]";
	}

}
