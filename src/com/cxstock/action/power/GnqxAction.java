package com.cxstock.action.power;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.power.MenuBiz;
import com.cxstock.biz.power.dto.MenuDTO;


@SuppressWarnings("serial")
public class GnqxAction extends BaseAction  {
	
	private Integer menuid;
	private String menuname;
	private Integer pid;
	private String menuurl;
	private Integer menutype;
	private Integer ordernum;
	private String icon;
	private MenuBiz menuBiz;
	/**
	 * 保存/修改功能表
	 */
	public String saveOrUpdateMenu() {
		try {
			MenuDTO dto = new MenuDTO();
			dto.setMenuid(menuid);
			dto.setMenuname(menuname);
			dto.setPid(pid);
			dto.setMenuurl(menuurl);
			dto.setMenutype(menutype);
			dto.setOrdernum(ordernum);
			dto.setIcon(icon);
			menuBiz.saveMenu(dto);
//			if(roleid==null){
				this.outString("{success:true,message:'保存成功!'}");
		//	}else{
			//	this.outString("{success:true,message:'修改成功!'}");
			//}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

 
	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Integer getMenutype() {
		return menutype;
	}

	public void setMenutype(Integer menutype) {
		this.menutype = menutype;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public MenuBiz getMenuBiz() {
		return menuBiz;
	}
	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
	}
	
}
