package com.cxstock.biz.ziliao;

import com.cxstock.pojo.Xmxx;
import com.cxstock.utils.pubutil.Page;

public interface XmxxBiz {
	
	/**
	 * 商品编号
	 */
	public String getXmxxCode();
	
	/**
	 * 分页查询商品列表
	 */
	public void findPageXmxx(Page page);
	
	/**
	 * 保存商品
	 */
	public void save(Xmxx dto);
	
	/**
	 * 修改改商品
	 */
	public void updateXmxx(Xmxx dto);
	
	/**
	 * 删除商品
	 */
	public boolean deleteXmxx(String spid);


}
