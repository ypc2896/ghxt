package com.cxstock.biz.power.imp;

import java.util.List;

import net.sf.json.JSONArray;

import com.cxstock.biz.power.UserBiz;
import com.cxstock.biz.power.dto.UserDTO;
import com.cxstock.biz.power.dto.UserMenuDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Role;
import com.cxstock.pojo.Users;
import com.cxstock.utils.pubutil.Page;

public class UserBizImpl implements UserBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 登录验证
	 * @see com.cxstock.biz.power.UserBiz#login(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public UserDTO login(String code, String pass) {
		String hql="from Users as t where t.logincode='"+code+"' and t.password='"+pass+"'";
		Users user = (Users)baseDao.loadObject(hql);
		if(user!=null){
			UserDTO dto = UserDTO.createDto(user);
			hql = "from Vusermenu t where t.userid="+user.getUserid();
			List list = baseDao.findByHql(hql);
			JSONArray jsong = JSONArray.fromObject(new UserMenuDTO().getTree(0,list));
			dto.setUsermenu(jsong.toString());
			return dto;
		}
		return null;
	}
	
	/*
	 * 分页查询用户列表
	 * @see com.cxstock.biz.power.UserBiz#findPageUser(com.cxstock.utils.system.Page)
	 */
	@SuppressWarnings("unchecked")
	public void findPageUser(Page page) {
		String hql = "from Users t ";
		
	//增加查询条件，链接左侧类别树的查询条件	
		if(page.getWheres()!=null){
			hql+=page.getWheres();
			//hql1+=page.getWheres();
		}		
		
		hql+=" order by t.lbid asc,t.userid asc";
		
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		List dtoList = UserDTO.createDtos(list);
		int total = baseDao.countAll("Users");
		page.setRoot(dtoList);
		page.setTotal(total);
	}
	/*
	 * 保存/修改用户
	 * @see com.cxstock.biz.power.UserBiz#saveOrUpdateUser(com.cxstock.biz.power.dto.UserDTO)
	 */
	public boolean saveOrUpdateUser(UserDTO dto) {
		Users user = new Users();
		if(dto.getUserid()!=null){
			user = (Users)baseDao.loadById(Users.class, dto.getUserid());
		}else{
			Users u = (Users)baseDao.loadObject("from Users where logincode='"+dto.getLogincode()+"'");
			if(u!=null){
				return false;
			}
			
			user.setUserid(Integer.parseInt(baseDao.getSequence("seq_jgid")));
			user.setLogincode(dto.getLogincode());
			//user.setState(0); //用户状态值在新建用户时为0，表是正常
		}
		//user.setUserid(2);
		user.setPassword(dto.getPassword());
		 
		user.setUsername(dto.getUsername());
		user.setRole(new Role(dto.getRoleid()));
		user.setBz(dto.getBz());
		user.setJgid(dto.getJgid());
		user.setState(dto.getState());
		user.setLbid(dto.getLbid());
		user.setLbname(dto.getLbname());
		user.setBaiwei(dto.getBaiwei());
		user.setZhiwu(dto.getZhiwu());
		user.setZhengshuid(dto.getZhengshuid());
		
		
		
		user.setGangwei(dto.getGangwei());
		user.setDengji(dto.getDengji());
		user.setShangjiid(dto.getShangjiid());
		user.setXianzhong(dto.getXianzhong());
		user.setDengip(dto.getDengip());
		user.setDengdate(dto.getDengdate());
		
		baseDao.saveOrUpdate(user);
		return true;
	}
	
	/*
	 * 删除用户
	 * @see com.cxstock.biz.power.UserBiz#deleteUser(java.lang.String)
	 */
	public void deleteUser(Integer userid) {
		baseDao.deleteById(Users.class, userid);	
	}
	/*
	 * 重置用户密码
	 * @see com.cxstock.biz.power.UserBiz#updatemimaUser(java.lang.String)
	 */
	public void updatemimaUser(Integer userid) {
		String hql = "update Users t set t.password='123456' where t.userid= ";
		
		
		hql+=userid;
		
		
		baseDao.update(hql);
		
	}
}
