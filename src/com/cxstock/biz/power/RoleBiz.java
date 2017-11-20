package com.cxstock.biz.power;

import java.util.List;

import com.cxstock.biz.power.dto.RoleDTO;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("unchecked")
public interface RoleBiz {

	/**
	 * 分页查询角色列表
	 */
	public void findPageRole(Page page);
	
	/**
	 * 保存/修改角色
	 */
	public void saveOrUpdateRole(RoleDTO dto);
	
	/**
	 * 删除角色
	 */
	public boolean deleteRole(Integer roleid);
	
	/**
	 * 角色下拉数据
	 */
	
	public List findRoleType();
	
	/**
	 * 查询角色菜单
	 */
	public List findRoleMenu(Integer roleid);
	/**
	 * 查询角色顶层菜单
	 */
	public List findRoleTopMenu(Integer roleid);
	/**
	 * 查询菜单
	 */
	public List findMenu();
	/**
	 * 查询功能菜单
	 */
	public List findRoleFunction(Integer roleid);
	/**
	 * 查询单条菜单
	 */
	public List findMenuByMenuid(Integer menuid);
	/**
	 * 保存角色权限
	 */
	public void saveRoleMenu(Integer roleid, String menuids);
	
}
