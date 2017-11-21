package com.cxstock.biz.ghxt;

import com.cxstock.biz.ghxt.dto.RyjfxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface RyjfxxBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageRyjfxx(String aae001,Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateRyjfxx(RyjfxxDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteRyjfxx(String aae001);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateRyjfxxgx(String aae001,String aae007);
}
