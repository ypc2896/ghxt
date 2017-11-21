package com.cxstock.biz.ziliao;

import java.util.List;

import com.cxstock.biz.ziliao.dto.XmlbDTO;

public interface XmlbBiz {
	
	
	/**
	 * 保存/修改商品类别
	 */
	public Integer saveOrUpdateXmlb(XmlbDTO dto);
	
	/**
	 * 删除商品类别
	 */
	public boolean deleteXmlb(Integer lbid);

	/**
	 * 商品类别树
	 */
	@SuppressWarnings("unchecked")
	public List findXmlbTree();

}
