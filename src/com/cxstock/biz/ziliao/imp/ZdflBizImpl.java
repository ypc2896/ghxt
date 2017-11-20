package com.cxstock.biz.ziliao.imp;

import java.util.List;

import com.cxstock.biz.ziliao.ZdflBiz;
import com.cxstock.biz.ziliao.dto.ZdflDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Zdfl;

@SuppressWarnings("unchecked")
public class ZdflBizImpl implements ZdflBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}

	public List findAllZdfl() {
		return baseDao.listAll("Zdfl");
	}

	/*
	 * 保存/修改商品
	 */
	public void saveOrUpdateZdfl(ZdflDTO dto) {
		Zdfl zdfl = new Zdfl();
		if(dto.getDwid()!=null){
			zdfl = (Zdfl)baseDao.loadById(Zdfl.class, dto.getDwid());
		}
		zdfl.setDwname(dto.getDwname());
		baseDao.saveOrUpdate(zdfl);
	}
	
	/*
	 * 删除商品
	 */
	public void deleteZdfl(Integer dwid) {
		baseDao.deleteById(Zdfl.class, dwid);	
	}

}
