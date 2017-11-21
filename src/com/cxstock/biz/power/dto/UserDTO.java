package com.cxstock.biz.power.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.Users;

public class UserDTO {

	private Integer userid;
	private String logincode;
	private String password;
	private String username;
	private Integer roleid;
	private String rolename;
	private Integer state;
	private String bz;
	private String usermenu;
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
	
	
	
	public UserDTO() {
		super();
	}

	public UserDTO(Integer userid, String logincode, String password,
			String username, Integer state, String bz, String jgid, Integer lbid, 
			String lbname,String baiwei,String zhiwu,String zhengshuid,
			String gangwei,String dengji,Integer shangjiid,String xianzhong,String dengip,Date dengdate) {
		super();
		this.userid = userid;
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

	public UserDTO(Integer userid, String logincode, String password,
			String username, Integer roleid, String rolename, Integer state,
			String bz, String jgid, Integer lbid, String lbname,String baiwei,String zhiwu,String zhengshuid,
			String gangwei,String dengji,Integer shangjiid,String xianzhong,String dengip,Date dengdate) {
		super();
		this.userid = userid;
		this.logincode = logincode;
		this.password = password;
		this.username = username;
		this.roleid = roleid;
		this.rolename = rolename;
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

	public static UserDTO createDto(Users pojo) {
		UserDTO dto = null;
		if (pojo != null) {
			dto = new UserDTO(pojo.getUserid(), pojo.getLogincode(),
					pojo.getPassword(), pojo.getUsername(),pojo.getRole().getRoleid(),pojo.getRole().getRolename(),
					pojo.getState(),pojo.getBz(),pojo.getJgid(),pojo.getLbid(),pojo.getLbname(),
					pojo.getBaiwei(),pojo.getZhiwu(),pojo.getZhengshuid(),
					pojo.getGangwei(),pojo.getDengji(),pojo.getShangjiid(),pojo.getXianzhong(),pojo.getDengip(),pojo.getDengdate());
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				Users user = (Users)iterator.next();
				UserDTO dto = createDto(user);
				if(user.getRole()!=null){
					dto.setRoleid(user.getRole().getRoleid());
					dto.setRolename(user.getRole().getRolename());
				}
				list.add(dto);
			}
		}
		return list;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBz() {
		return bz;
	}
	
	public String getJgid() {
		return jgid;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	
	
	public String getUsermenu() {
		return usermenu;
	}

	public void setUsermenu(String usermenu) {
		this.usermenu = usermenu;
	}
	
	
	
	
	public Integer getLbid() {
		return lbid;
	}
	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}
	
	public String getLbname() {
		return lbname;
	}	
	public void setLbname(String lbname) {
		this.lbname = lbname;
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
