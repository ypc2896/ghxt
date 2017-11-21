package com.cxstock.biz.ziliao.dto;

public class ZdflDTO {
	
	private Integer dwid;
	private String dwname;
	
	public ZdflDTO() {
		super();
	}

	public ZdflDTO(Integer dwid, String dwname) {
		super();
		this.dwid = dwid;
		this.dwname = dwname;
	}

	public Integer getDwid() {
		return dwid;
	}

	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}

	public String getDwname() {
		return dwname;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}

}
