package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.RyxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface RyxxBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageRyxx(String aac001,Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateRyxx(RyxxDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteRyxx(String aac001);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateRyxxgx(String aac001,String gw010);
}
