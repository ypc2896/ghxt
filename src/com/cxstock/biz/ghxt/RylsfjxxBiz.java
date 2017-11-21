package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.RylsfjxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface RylsfjxxBiz {
	
	/**
	 * 分页查询 
	 */
	public void findPageRylsfjxx(String sae201,Page page);
	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateRylsfjxx(RylsfjxxDTO dto);
	
	/**
	 * 删除 
	 */
	public void deleteRylsfjxx(String sae201);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateRylsfjxxgx(String sae201,String sae012);
}
