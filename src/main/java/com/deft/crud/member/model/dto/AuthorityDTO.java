package com.deft.crud.member.model.dto;

import java.util.List;

public class AuthorityDTO {
	
	private int code;
	private String name;
	
	private List<AuthenticatedMenuDTO> authenticatedMenuList;
	
	public AuthorityDTO() {}

	public AuthorityDTO(int code, String name, List<AuthenticatedMenuDTO> authenticatedMenuList) {
		super();
		this.code = code;
		this.name = name;
		this.authenticatedMenuList = authenticatedMenuList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AuthenticatedMenuDTO> getAuthenticatedMenuList() {
		return authenticatedMenuList;
	}

	public void setAuthenticatedMenuList(List<AuthenticatedMenuDTO> authenticatedMenuList) {
		this.authenticatedMenuList = authenticatedMenuList;
	}

	@Override
	public String toString() {
		return "AuthorityDTO [code=" + code + ", name=" + name + ", authenticatedMenuList=" + authenticatedMenuList
				+ "]";
	}
}
