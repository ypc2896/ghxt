package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.RylszfxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface RylszfxxBiz {
	
	/**
	 * 分页查询 
	 */
	public void findPageRylszfxx(String zf001,Page page);
	
	/**
	 * 保存/修改 
	 */
	public void saveOrUpdateRylszfxx(RylszfxxDTO dto);
	
	/**
	 * 删除 
	 */
	public void deleteRylszfxx(String zf001);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateRylszfxxgx(String zf001,String zf032);
}
