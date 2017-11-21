package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.RylsxxBiz;
import com.cxstock.biz.ghxt.dto.RylsxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Balsb;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class RylsxxBizImpl implements RylsxxBiz {
	
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
	public void findPageRylsxx(String sae041,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Balsb t";
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
	public void saveOrUpdateRylsxx(RylsxxDTO dto) {
		Balsb rylsxx = new Balsb();
		try {
			
			if(dto.getSae041().equals("")||dto.getSae041().equals(null)){  
			   String sae041 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(rylsxx,dto);
			   rylsxx.setSae041(sae041);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(rylsxx, dto);
			}
			
			baseDao.saveOrUpdate(rylsxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteRylsxx(String sae041) {
		baseDao.deleteById(Balsb.class, sae041);	
	}
	
	
	
	public void updateRylsxxgx(String sae041,String sae075) {
		String hql = "update Balsb t set ";
		hql+= " sae075="+sae075 ;
		hql+= " where sae041 = "+ sae041 ;
		baseDao.update(hql);	
	}

 

}
