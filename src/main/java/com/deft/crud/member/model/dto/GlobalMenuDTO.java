package com.deft.crud.member.model.dto;

public class GlobalMenuDTO {
	
	private int code;
	private String name;
	private String url;
	private int type;
	private Integer refMenuCode;
	
	public GlobalMenuDTO() {}

	public GlobalMenuDTO(int code, String name, String url, int type, Integer refMenuCode) {
		super();
		this.code = code;
		this.name = name;
		this.url = url;
		this.type = type;
		this.refMenuCode = refMenuCode;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getRefMenuCode() {
		return refMenuCode;
	}

	public void setRefMenuCode(Integer refMenuCode) {
		this.refMenuCode = refMenuCode;
	}

	@Override
	public String toString() {
		return "GlobalMenuDTO [code=" + code + ", name=" + name + ", url=" + url + ", type=" + type + ", refMenuCode="
				+ refMenuCode + "]";
	}
}
