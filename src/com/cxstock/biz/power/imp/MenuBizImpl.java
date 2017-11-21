package com.cxstock.biz.power.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxstock.biz.power.MenuBiz;
import com.cxstock.biz.power.RoleBiz;
import com.cxstock.biz.power.dto.MenuDTO;
import com.cxstock.biz.power.dto.RoleDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Menu;
import com.cxstock.pojo.Role;
import com.cxstock.pojo.Rolemenu;
import com.cxstock.pojo.RolemenuId;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.pubutil.TreeNodeChecked;

@SuppressWarnings("unchecked")
public class MenuBizImpl implements MenuBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	 
	
	/*
	 * 保存/修改功能列表
	 * @see com.cxstock.biz.power.MenuBiz#saveOrUpdateRole(com.cxstock.biz.power.dto.MenuDTO)
	 */
	public Integer saveMenu(MenuDTO dto) {
		Menu menu = new Menu();
		if(dto.getMenuid()!=null){
			menu = (Menu)baseDao.loadById(Menu.class, dto.getMenuid());
		}
		menu.setMenuid(dto.getMenuid());
		menu.setMenuname(dto.getMenuname());
		menu.setMenutype(dto.getMenutype());
		menu.setMenuurl(dto.getMenuurl());
		menu.setMenutype(dto.getMenutype());
		menu.setIcon(dto.getIcon());
		menu.setPid(dto.getPid());
		menu.setOrdernum(6);
		baseDao.save(menu);
		return menu.getMenuid();
	}
	
	/*
	 * 删除角色
	 * @see com.cxstock.biz.power.RoleBiz#deleteRole(java.lang.Integer)
	 */
	public boolean deleteMenu(Integer menuid) {
	
			baseDao.deleteById(Menu.class, menuid);
			return true;
	}

}
