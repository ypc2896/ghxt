package com.cxstock.pojo;

/**
 * Spdw entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Zdfl implements java.io.Serializable {

	// Fields

	private Integer dwid;
	private String dwname;

	// Constructors

	/** default constructor */
	public Zdfl() {
	}

	/** full constructor */
	public Zdfl(String dwname) {
		this.dwname = dwname;
	}

	// Property accessors

	public Integer getDwid() {
		return this.dwid;
	}

	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}

	public String getDwname() {
		return this.dwname;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}

}