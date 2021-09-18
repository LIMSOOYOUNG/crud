package com.deft.crud.member.model.dto;

public class EmpRoleDTO {
	
	private int empNo;
	private int authorityCode;
	
	private AuthorityDTO authority;
	
	public EmpRoleDTO() {}

	public EmpRoleDTO(int empNo, int authorityCode, AuthorityDTO authority) {
		super();
		this.empNo = empNo;
		this.authorityCode = authorityCode;
		this.authority = authority;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "EmpRoleDTO [empNo=" + empNo + ", authorityCode=" + authorityCode + ", authority=" + authority
				+ "]";
	}
}
