package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.AabcsxxBiz;
import com.cxstock.biz.ghxt.dto.AabcsxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Aabcsb;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class AabcsxxBizImpl implements AabcsxxBiz {
	
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
	public void findPageAabcsxx(Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Aabcsb t";
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
	public void saveOrUpdateAabcsxx(AabcsxxDTO dto) {
		Aabcsb aabcsxx = new Aabcsb();
		try {
			
			if(dto.getAab001().equals("")||dto.getAab001().equals(null)){  
			   String aab001 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(aabcsxx,dto);
			   aabcsxx.setAab001(aab001);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(aabcsxx, dto);
			}
			
			baseDao.saveOrUpdate(aabcsxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteAabcsxx(String aab001) {
		baseDao.deleteById(Aabcsb.class, aab001);	
	}
	
	
	
	public void updateAabcsxxgx(String aab001,String aab005) {
		String hql = "update Aabcsb t set ";
		hql+= " aab005="+aab005 ;
		hql+= " where aab001 = "+ aab001 ;
		baseDao.update(hql);	
	}

	public List findAabcsxxComb() {
		return null;
	}


 

 
 

}
