package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.DwjfxxBiz;
import com.cxstock.biz.ghxt.dto.DwjfxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Person;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class DwjfxxBizImpl implements DwjfxxBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 分页查询单位列表
	 */
	
	/**4
	 * 5  findPageGwxx
	 */
	public void findPageDwjfxx(String aac001,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Dwysb t";
		if(page.getWheres()!=null){
			hql+=page.getWheres();
			 
		}
		
		
		//hql+=" and dwbh00 like '%"+dwbh00+"%' and dwmc00 like '%"+dwmc00+"%'";

		List list = baseDao.findByHql(hql,page.getStart(), page.getLimit());
		
		
		//hql="select count(*)from Gwxx where dwbh00 like '%"+dwbh00+"%' and dwmc00 like '%"+dwmc00+"%'";
		
		int total = baseDao.countQuery("select count(*) "+hql.toString());
		
		
		
		//int total = baseDao.countQuery(hql);
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改单位
	 */
	public void saveOrUpdateDwjfxx(DwjfxxDTO dto) {
		Person dwjfxx = new Person();
		try {
			
			if(dto.getAae061().equals("")||dto.getAae061().equals(null)){  
			   String aae061 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(dwjfxx,dto);
			  dwjfxx.setAac001(aae061);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(dwjfxx, dto);
			}
			
			baseDao.saveOrUpdate(dwjfxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteDwjfxx(String aae061) {
		baseDao.deleteById(Person.class, aae061);	
	}
	
	
	
	public void updateDwjfxxgx(String aae061,String gw010) {
		String hql = "update Dwysb t set ";
		hql+= " gw010="+gw010 ;
		hql+= " where aae061 = "+ aae061 ;
		baseDao.update(hql);	
	}

 

}
