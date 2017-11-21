package com.cxstock.biz.dagl;

import com.cxstock.biz.dagl.dto.GwxxDTO;
import com.cxstock.utils.pubutil.Page;
public interface GwxxBiz {
	
	/**
	 * 分页查询单位列表
	 */
	public void findPageGwxx(String dwbh00,Page page);
	
	/**
	 * 保存/修改单位
	 */
	public void saveOrUpdateGwxx(GwxxDTO dto);
	
	/**
	 * 删除单位
	 */
	public void deleteGwxx(String dwbh00);
	
	/**
	 * 共享公文
	 * @param dwbh00
	 */
	public void updateGwxxgx(String dwbh00,String gw015);
}
