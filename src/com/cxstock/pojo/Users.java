package com.cxstock.pojo;
import java.util.Date;
/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Role role;
	private String logincode;
	private String password;
	private String username;
	private Integer state;
	private String bz;
	private String jgid;
	private Integer lbid;
	private String lbname;
	
	private String baiwei;   //用户排位
	private String zhiwu;   //职务
	private String zhengshuid;  //证书ID
	

	private String gangwei;
	private String dengji;
	private Integer shangjiid;
	private String xianzhong;
	private String dengip;
	private Date dengdate;
	
	
	// Constructors      

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Role role, String logincode, String password, String username,
			Integer state) {
		this.role = role;
		this.logincode = logincode;
		this.password = password;
		this.username = username;
		this.state = state;
	}

	/** full constructor */
	public Users(Role role, String logincode, String password, String username,
			Integer state, String bz, String jgid,Integer lbid,String lbname,String baiwei,String zhiwu,String zhengshuid,
			String gangwei,String dengji,Integer shangjiid,String xianzhong,String dengip,Date dengdate) {
		this.role = role;
		this.logincode = logincode;
		this.password = password;
		this.username = username;
		this.state = state;
		this.bz = bz;
		this.jgid = jgid;
		this.lbid=lbid;
		this.lbname=lbname;
		this.baiwei=baiwei;
		this.zhiwu=zhiwu;
		this.zhengshuid=zhengshuid;
		
		this.gangwei=gangwei;
		this.dengji=dengji;
		this.shangjiid=shangjiid;
		this.xianzhong=xianzhong;
		this.dengip=dengip;
		this.dengdate=dengdate;
		
		
		
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogincode() {
		return this.logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
	public String getJgid() {
		
		return this.jgid;
	} 
	
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	public String getLbname() {
		
		return this.lbname;
	} 
	
	public void setLbname(String lbname) {
		this.lbname = lbname;
	}
	public Integer getLbid() {
		return this.lbid;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}
	
	
	public String getBaiwei() {
		return baiwei;
	}	
	public void setBaiwei(String baiwei) {
		this.baiwei = baiwei;
	}	
	
	public String getZhiwu() {
		return zhiwu;
	}	
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}		
	
	public String getZhengshuid() {
		return zhengshuid;
	}	
	public void setZhengshuid(String zhengshuid) {
		this.zhengshuid = zhengshuid;
	}	
	

	
	
	public String getGangwei() {
		return gangwei;
	}

	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}

	public String getDengji() {
		return dengji;
	}

	public void setDengji(String dengji) {
		this.dengji = dengji;
	}

	public Integer getShangjiid() {
		return shangjiid;
	}

	public void setShangjiid(Integer shangjiid) {
		this.shangjiid = shangjiid;
	}

	public String getXianzhong() {
		return xianzhong;
	}

	public void setXianzhong(String xianzhong) {
		this.xianzhong = xianzhong;
	}

	public String getDengip() {
		return dengip;
	}

	public void setDengip(String dengip) {
		this.dengip = dengip;
	}

	public Date getDengdate() {
		return dengdate;
	}

	public void setDengdate(Date dengdate) {
		this.dengdate = dengdate;
	}
	
}