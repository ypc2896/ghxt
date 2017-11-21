package com.cxstock.action.power;


import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.system.Constants;

@SuppressWarnings("serial")
public class UserAction extends BaseAction  {
	
	private UserBiz userBiz;
	
	private Integer userid;
	private String logincode;
	private String password;
	private String username;
	private Integer roleid;
	private Integer state;
	private String bz;
	private String jgid;
	private Integer lbid;
	private String lbname;
	
	private String baiwei;   //用户排位
	private String zhiwu;   //职务
	private String zhengshuid;  //证书ID
	
	private String search;
	private String addupdate;
	
	
	private String gangwei;
	private String dengji;
	private Integer shangjiid;
	private String xianzhong;
	private String dengip;
	private Date dengdate;
	
	
	
	
	
	
	
	
	
	
	/** 登录验证 */
	public String login() {
		try{
			String code = logincode.trim().toLowerCase();
			String pass = password.trim().toLowerCase();
			UserDTO userInfo = userBiz.login(code, pass);
			if (userInfo != null) {
				this.getSession().setAttribute(Constants.USERINFO, userInfo);
				return "success";
			} else{
				this.getRequest().setAttribute("error", "用户名或密码错误");
				return "input";
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("error", "连接失败");
			return "login";
		}
	}
	
	/** 用户权限菜单 */
	public String getMenuBuf() {
		UserDTO userInfo = this.getUserDTO();
		try {
			if(userInfo!=null){
				this.outString(userInfo.getUsermenu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/** 
	 * 分页查询用户列表 
	 */
	public String findPageUser() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			
			

			
			
			page.setWheres(" where 1=1 ");
			StringBuffer buf = new StringBuffer(" where 1=1 ");
			
			//用户管理模块，点类别树时，要判断
			if(lbid!=null&&lbid!=0){
				buf.append(" and t.lbid="+lbid);
			}else if(search!=null&&!"".equals(search)){
				buf.append(" and t.lbid like '%");
				buf.append(search);
				buf.append("%' or t.lbname like '%");
				buf.append(search);
				buf.append("%'");			
			}
			//在选择用户窗体发放岗位分类时判断
			if(gangwei!=null&&!"0".equals(gangwei)){
				buf.append(" and t.gangwei="+gangwei);
			}
			
			page.setWheres(buf.toString());
			
			
			userBiz.findPageUser(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}	

	/**
	 * 保存/修改用户
	 */
	public String saveOrUpdateUser() {
		try {
			UserDTO dto = new UserDTO(userid,logincode,password,username,roleid,null,state,bz,jgid,lbid,lbname,baiwei,zhiwu,zhengshuid,
					gangwei,dengji,shangjiid,xianzhong,dengip,dengdate);
			boolean bool = userBiz.saveOrUpdateUser(dto);
			if(bool){
				if(userid==null){
					this.outString("{success:true,message:'保存成功!'}");
				}else{
					this.outString("{success:true,message:'修改成功!'}");
				}
			}else{
				this.outString("{success:false,errors:'用户账号已存在!'}");
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
    
	/**
	 * 删除用户
	 */
	public String deleteUser() {
		try {
			userBiz.deleteUser(userid);
			this.outString("{success:true}");
			
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
			
		}
		return null;
	}
	/**
	 * 重置用户密码
	 */
	public String updatemimaUser() {
		
		if(userid==null){
			this.outString("{success:true,message:'重置失败!'}");
		}else{
			this.outString("{success:true,message:'重置成功，密码为123456!'}");
		}
		
		try {
			userBiz.updatemimaUser(userid);

		} catch (Exception e) {
			e.printStackTrace();
			this.outError();

		}
		return null;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}		
	public void setLbname(String lbname) {
		this.lbname = lbname;
	}

	
	
	public void setBaiwei(String baiwei) {
		this.baiwei = baiwei;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	public void setZhengshuid(String zhengshuid) {
		this.zhengshuid = zhengshuid;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public void setAddupdate(String addupdate) {
		this.addupdate = addupdate;
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
