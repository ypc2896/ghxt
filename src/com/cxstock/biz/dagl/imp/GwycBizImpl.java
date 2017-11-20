package com.cxstock.biz.dagl.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.dagl.GwycBiz;
import com.cxstock.biz.dagl.dto.GwycDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Gwyc;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class GwycBizImpl implements GwycBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 分页查询单位列表
	 */
	
	/**4
	 * 5  findPageGwyc
	 */
	public void findPageGwyc(Page page) {
		String hql = " from Vgwyc t";            //对就为视图进行查询
		//String hql = " from Gwyc t";   //此处的Gwyc，是定义的于数据表对应的对象实体名，并不是数据库表名称。
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
	 * 保存/修改
	 */
	public void saveOrUpdateGwyc(GwycDTO dto) {
		Gwyc gwyc = new Gwyc();
		int totalyc=0;
		
		//查询一个公文是否已经发给用户,公文ID 和接收人ID
		String hqlyc = " select count(*)  from Vgwyc t where 1=1 "; 
		hqlyc+=" and dwbh00='"+dto.getYc002();
		hqlyc+="' and yc004='"+dto.getYc004();
		hqlyc+="'";
		
		
		try {
			 totalyc = baseDao.countQuery(hqlyc.toString());   //查询公文发给某一用户的数量
			 
			 
			if(dto.getYc001().equals("")||dto.getYc001().equals(null)){  
			    //如果阅处信息ID为空，说明是新增，如果是新增就判断是否给某一用户发送过公文
				if (totalyc<1) {
		 			    BeanUtils.copyProperties(gwyc,dto);
		 			    
		 			    String yc001 = baseDao.getSequence("SEQ_YCID");  //获取的 为字符型，通过序列获得公文阅处信息的ID，
					    gwyc.setYc001(yc001);        //将ID赋给对象的对应值。
					    baseDao.saveOrUpdate(gwyc);  //调用存储过程进行保存
				}	
			}
			else
			{    
				BeanUtils.copyProperties(gwyc, dto);
				baseDao.saveOrUpdate(gwyc);  //调用存储过程进行保存
			}

			
			
			
			

			
			
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除
	 */
	public void deleteGwyc(String yc001) {
		baseDao.deleteById(Gwyc.class, yc001);	
	}
	/*
	 * 删除 
	 */
	public void deleteGwycgwid(String yc002) {
		baseDao.deleteById(Gwyc.class, yc002);	
	}
	
 
	
	
	
	
	
	
	/*
	 *  单位下拉拉列表
	 */
	public List<ComboData> findGwycComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Gwyc> gysList = baseDao.listAll("Gwyc");
		for (Gwyc gwyc : gysList) {
			ComboData comb = new ComboData();
			comb.setValue(gwyc.getYc001());
			comb.setText(gwyc.getYc003());
			list.add(comb);
		}
		return list;
	}
 

}
