package com.cxstock.biz.dagl.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.dagl.GwxxBiz;
import com.cxstock.biz.dagl.dto.GwxxDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Gwxx;
import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class GwxxBizImpl implements GwxxBiz {
	
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
	public void findPageGwxx(String dwbh00,Page page) {
/*		if(dwbh00==null){
			dwbh00 = "";
		}
		if(dwmc00==null){
			dwmc00 = "";
		}*/
		
		String hql = " from Gwxx t";
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
	public void saveOrUpdateGwxx(GwxxDTO dto) {
		Gwxx gwxx = new Gwxx();
		try {
			
			if(dto.getDwbh00().equals("")||dto.getDwbh00().equals(null)){  
			   String dwbh00 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(gwxx,dto);
			   gwxx.setDwbh00(dwbh00);
			}
			else
			{
			   //gwxx = (Gwxx)baseDao.loadById(Gwxx.class, dto.getDwbh00());
			   BeanUtils.copyProperties(gwxx, dto);
			}
			
			baseDao.saveOrUpdate(gwxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteGwxx(String dwbh00) {
		baseDao.deleteById(Gwxx.class, dwbh00);	
	}
	
	
	
	public void updateGwxxgx(String dwbh00,String gw015) {
		String hql = "update Gwxx t set ";
		hql+= " gw015="+gw015 ;
		hql+= " where dwbh00 = "+ dwbh00 ;
		baseDao.update(hql);	
	}
	/*
	 *  单位下拉拉列表
	 */
	public List<ComboData> findGwxxComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Gwxx> gysList = baseDao.listAll("Gwxx");
		for (Gwxx gwxx : gysList) {
			ComboData comb = new ComboData();
			comb.setValue(gwxx.getDwbh00());
			comb.setText(gwxx.getGw003());
			list.add(comb);
		}
		return list;
	}
 

}
