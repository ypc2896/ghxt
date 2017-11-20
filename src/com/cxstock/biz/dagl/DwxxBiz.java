package com.cxstock.biz.dagl;

import java.util.List;

import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface DwxxBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageDwxx(String aab001,String aab003,Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateDwxx(DwxxDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteDwxx(String dwbh00);

	/**
	 * 商品类别树
	 */
	@SuppressWarnings("unchecked")
	public List findDwlbTree();
	
	
	
	
	
	
}
