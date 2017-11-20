package com.cxstock.biz.ghxt.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.ghxt.AaacsxxBiz;
import com.cxstock.biz.ghxt.dto.AaacsxxDTO;
import com.cxstock.dao.BaseDAO;
import com.ghxt.pojo.Aaacsb;
//import com.cxstock.pojo.Gwyc;
import com.cxstock.pojo.Gys;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class AaacsxxBizImpl implements AaacsxxBiz {
	
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
	public void findPageAaacsxx(Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Aaacsb t";
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
	public void saveOrUpdateAaacsxx(AaacsxxDTO dto) {
		Aaacsb aaacsxx = new Aaacsb();
		try {
			
			if(dto.getAaa001().equals("")||dto.getAaa001().equals(null)){  
			   String aaa001 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(aaacsxx,dto);
			   aaacsxx.setAaa001(aaa001);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(aaacsxx, dto);
			}
			
			baseDao.saveOrUpdate(aaacsxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteAaacsxx(String aaa001) {
		baseDao.deleteById(Aaacsb.class, aaa001);	
	}
	
	
	
	public void updateAaacsxxgx(String aaa001,String aaa014) {
		String hql = "update Aaacsb t set ";
		hql+= " aaa014="+aaa014 ;
		hql+= " where aaa001 = "+ aaa001 ;
		baseDao.update(hql);	
	}

 

	/*
	 *  供应商下拉拉列表
	 */
	public List<ComboData> findAaacsxxComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Aaacsb> aaacsbList = baseDao.listAll("Aaacsb");
		for (Aaacsb aaacsb : aaacsbList) {
			ComboData comb = new ComboData();
			 //comb.setValue(aaacsb.getAaa001().toString());
			comb.setValue(aaacsb.getAaa002().toString());
			comb.setText("第"+aaacsb.getAaa002()+"期");
			list.add(comb);
		}
		return list;
	}
 

 
 

}
