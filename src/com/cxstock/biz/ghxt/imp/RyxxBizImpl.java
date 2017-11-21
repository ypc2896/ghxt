package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.RyxxBiz;
import com.cxstock.biz.ghxt.dto.RyxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Person;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class RyxxBizImpl implements RyxxBiz {
	
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
	public void findPageRyxx(String aac001,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Person t";
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
	public void saveOrUpdateRyxx(RyxxDTO dto) {
		Person ryxx = new Person();
		try {
			
			if(dto.getAac001().equals("")||dto.getAac001().equals(null)){  
			   String aac001 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(ryxx,dto);
			   ryxx.setAac001(aac001);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(ryxx, dto);
			}
			
			baseDao.saveOrUpdate(ryxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteRyxx(String aac001) {
		baseDao.deleteById(Person.class, aac001);	
	}
	
	
	
	public void updateRyxxgx(String aac001,String gw010) {
		String hql = "update Person t set ";
		hql+= " gw010="+gw010 ;
		hql+= " where aac001 = "+ aac001 ;
		baseDao.update(hql);	
	}

 

}
