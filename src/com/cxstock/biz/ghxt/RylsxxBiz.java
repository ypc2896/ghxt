package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.RylsxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface RylsxxBiz {
	
	/**
	 * 分页查询 
	 */
	public void findPageRylsxx(String sae041,Page page);
	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateRylsxx(RylsxxDTO dto);
	
	/**
	 * 删除 
	 */
	public void deleteRylsxx(String sae041);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateRylsxxgx(String sae041,String sae075);
}
