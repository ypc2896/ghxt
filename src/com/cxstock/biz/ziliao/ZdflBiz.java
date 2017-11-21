package com.cxstock.biz.ziliao;

import java.util.List;

import com.cxstock.biz.ziliao.dto.ZdflDTO;

public interface ZdflBiz {
	
	/**
	 * 单位列表 
	 */
	@SuppressWarnings("unchecked")
	public List findAllZdfl();
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateZdfl(ZdflDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteZdfl(Integer dwid);




}
