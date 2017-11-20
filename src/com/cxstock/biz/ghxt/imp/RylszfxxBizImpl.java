package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.RylszfxxBiz;
import com.cxstock.biz.ghxt.dto.RylszfxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Balsb;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class RylszfxxBizImpl implements RylszfxxBiz {
	
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
	public void findPageRylszfxx(String zf001,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Lszfb t";
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
	public void saveOrUpdateRylszfxx(RylszfxxDTO dto) {
		Balsb rylszfxx = new Balsb();
		try {
			
			if(dto.getZf001().equals("")||dto.getZf001().equals(null)){  
			   String zf001 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(rylszfxx,dto);
			   rylszfxx.setSae041(zf001);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(rylszfxx, dto);
			}
			
			baseDao.saveOrUpdate(rylszfxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteRylszfxx(String zf001) {
		baseDao.deleteById(Balsb.class, zf001);	
	}
	
	
	
	public void updateRylszfxxgx(String zf001,String zf032) {
		String hql = "update Lszfb t set ";
		hql+= " zf032="+zf032 ;
		hql+= " where zf001 = "+ zf001 ;
		baseDao.update(hql);	
	}

 

}
