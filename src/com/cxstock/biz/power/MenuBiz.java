package com.cxstock.biz.power;


import com.cxstock.biz.power.dto.MenuDTO;
@SuppressWarnings("unchecked")
public interface MenuBiz {

	
	/**
	 * 保存/修改功能权限
	 */
	public Integer saveMenu(MenuDTO dto);
	
	/**
	 * 删除功能菜单
	 */
	public boolean deleteMenu(Integer menuid);
	
	
}
