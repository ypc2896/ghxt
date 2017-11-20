package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.RyjfxxBiz;
import com.cxstock.biz.ghxt.dto.RyjfxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Grjfb;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class RyjfxxBizImpl implements RyjfxxBiz {
	
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
	public void findPageRyjfxx(String aae001,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Grjfb t";
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
	public void saveOrUpdateRyjfxx(RyjfxxDTO dto) {
		Grjfb ryjfxx = new Grjfb();
		try {
			
			if(dto.getAae001().equals("")||dto.getAae001().equals(null)){  
			   String aae001 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(ryjfxx,dto);
			   ryjfxx.setAae001(aae001);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(ryjfxx, dto);
			}
			
			baseDao.saveOrUpdate(ryjfxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteRyjfxx(String aae001) {
		baseDao.deleteById(Grjfb.class, aae001);	
	}
	
	
	
	public void updateRyjfxxgx(String aae001,String aae007) {
		String hql = "update Grjfb t set ";
		hql+= " aae007="+aae007 ;
		hql+= " where aae001 = "+ aae001 ;
		baseDao.update(hql);	
	}

 

}
