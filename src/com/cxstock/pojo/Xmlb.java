package com.cxstock.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Splb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Xmlb implements java.io.Serializable {

	// Fields

	private Integer lbid;
	private String lbname;
	private Integer pid;
	private Set xmxxes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Xmlb() {
	}

	public Xmlb(Integer lbid) {
		this.lbid = lbid;
	}

	/** full constructor */
	public Xmlb(String lbname, Integer pid, Set xmxxes) {
		this.lbname = lbname;
		this.pid = pid;
		this.xmxxes = xmxxes;
	}

	// Property accessors

	public Integer getLbid() {
		return this.lbid;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public String getLbname() {
		return this.lbname;
	}

	public void setLbname(String lbname) {
		this.lbname = lbname;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Set getXmxxes() {
		return this.xmxxes;
	}

	public void setXmxxes(Set xmxxes) {
		this.xmxxes = xmxxes;
	}

}