package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.RylsfjxxBiz;
import com.cxstock.biz.ghxt.dto.RylsfjxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Badab;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;


// rylsfjxx  意思为人员理算附件信息 上传的扫描文档。

@SuppressWarnings("unchecked")
public class RylsfjxxBizImpl implements RylsfjxxBiz {
	
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
	public void findPageRylsfjxx(String sae201,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Badab t";
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
	public void saveOrUpdateRylsfjxx(RylsfjxxDTO dto) {
		Badab rylsfjxx = new Badab();
		try {
			
			if(dto.getSae201().equals("")||dto.getSae201().equals(null)){  
			   String sae201 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(rylsfjxx,dto);
			   rylsfjxx.setSae201(sae201);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(rylsfjxx, dto);
			}
			
			baseDao.saveOrUpdate(rylsfjxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteRylsfjxx(String sae201) {
		baseDao.deleteById(Badab.class, sae201);	
	}
	
	
	
	public void updateRylsfjxxgx(String sae201,String sae012) {
		String hql = "update Badab t set ";
		hql+= " sae012="+sae012 ;
		hql+= " where sae201 = "+ sae201 ;
		baseDao.update(hql);	
	}

 

}
