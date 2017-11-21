package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.DwjfxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface DwjfxxBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageDwjfxx(String aac001,Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateDwjfxx(DwjfxxDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteDwjfxx(String aac001);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateDwjfxxgx(String aac001,String gw010);
}
