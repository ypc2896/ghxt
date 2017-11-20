package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.XmxxBiz;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Xmxx;
import com.cxstock.utils.pubutil.Page;

public class XmxxBizImpl implements XmxxBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 商品编号
	 */
	@SuppressWarnings("unchecked")
	public String getXmxxCode() {
		String hql = "select max(spid) from Xmxx";
		List list = baseDao.findByHql(hql);
		Object obj = list.get(0);
		if(obj!=null){
			Integer code = Integer.valueOf(obj.toString())+1;
			String codes = code.toString();
			int length = codes.length();
			for (int i = 4; i > length; i--) {
				codes = "0"+codes;
			}
			return codes;
		}else{
			return "0001";
		}
	}
	
	/*
	 * 分页查询商品列表
	 */
	@SuppressWarnings("unchecked")
	public void findPageXmxx(Page page) {
		//String hql = "select *	 from Xmxx t";
		String hql = " from Xmxx t";
		if(page.getWheres()!=null){
			hql+=page.getWheres();
			//hql1+=page.getWheres();
		}
		List list = baseDao.findByHql(hql, page.getStart(), page.getLimit());
		int total = baseDao.countQuery("select count(*) "+hql.toString());
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存商品
	 */
	public void save(Xmxx dto) {
		baseDao.save(dto);
	}
	
	/*
	 * 修改商品
	 */
	public void updateXmxx(Xmxx dto) {
		Xmxx xmxx = (Xmxx)baseDao.loadById(Xmxx.class, dto.getSpid());
		xmxx.setSpid(dto.getSpid());
		xmxx.setSpname(dto.getSpname());
		xmxx.setXinghao(dto.getXinghao());
		xmxx.setDw(dto.getDw());
		if(dto.getJhprice()!= null)
			xmxx.setJhprice(dto.getJhprice());
		if(dto.getChprice()!= null)
			xmxx.setChprice(dto.getChprice());
		if(dto.getMinnum()!=null)
			xmxx.setMinnum(dto.getMinnum());
		xmxx.setCsname(dto.getCsname());
		xmxx.setBz(dto.getBz());
		xmxx.setLbid(dto.getLbid());
		xmxx.setLbname(dto.getLbname());
		baseDao.save(xmxx);
	}
	
	/*
	 * 删除商品
	 */
	public boolean deleteXmxx(String spid) {
		Xmxx xmxx = (Xmxx)baseDao.loadById(Xmxx.class, spid);
		if("2".equals(xmxx.getState())){
			return false;
		}
		baseDao.delete(xmxx);	
		return true;
	}

}
