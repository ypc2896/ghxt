package com.cxstock.biz.dagl;

import com.cxstock.biz.dagl.dto.GwycDTO;
import com.cxstock.utils.pubutil.Page;
public interface GwycBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageGwyc(Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateGwyc(GwycDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteGwyc(String yc001);
	/**
	 * 根据公文ID删除单位公文阅处信息id
	 */
	public void deleteGwycgwid(String yc002);
}
