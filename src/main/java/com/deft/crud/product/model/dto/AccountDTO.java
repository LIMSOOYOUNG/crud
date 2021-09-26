package com.deft.crud.product.model.dto;

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
	
	public AccountDTO() {}

	public AccountDTO(int accountNo, String companyName, String accountCeo, String accountAddress, String accountStatus,
			String accountBusinessItem, String resName, String resPhone, String resEmail) {
		super();
		this.accountNo = accountNo;
		this.companyName = companyName;
		this.accountCeo = accountCeo;
		this.accountAddress = accountAddress;
		this.accountStatus = accountStatus;
		this.accountBusinessItem = accountBusinessItem;
		this.resName = resName;
		this.resPhone = resPhone;
		this.resEmail = resEmail;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccountCeo() {
		return accountCeo;
	}

	public void setAccountCeo(String accountCeo) {
		this.accountCeo = accountCeo;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountBusinessItem() {
		return accountBusinessItem;
	}

	public void setAccountBusinessItem(String accountBusinessItem) {
		this.accountBusinessItem = accountBusinessItem;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getResEmail() {
		return resEmail;
	}

	public void setResEmail(String resEmail) {
		this.resEmail = resEmail;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountNo=" + accountNo + ", companyName=" + companyName + ", accountCeo=" + accountCeo
				+ ", accountAddress=" + accountAddress + ", accountStatus=" + accountStatus + ", accountBusinessItem="
				+ accountBusinessItem + ", resName=" + resName + ", resPhone=" + resPhone + ", resEmail=" + resEmail
				+ "]";
	}
	
	
}
