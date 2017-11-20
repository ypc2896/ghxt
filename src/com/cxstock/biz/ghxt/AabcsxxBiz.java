package com.cxstock.biz.ghxt;

import java.util.List;

import com.cxstock.biz.ghxt.dto.AabcsxxDTO;
import com.cxstock.utils.pubutil.Page;

public interface AabcsxxBiz {
	
	/**
	 * 分页查询列表
	 */
	//public void findPageAaacsxx(Page page);
	
	/**
	 * 保存/修改
	 */
	public void saveOrUpdateAabcsxx(AabcsxxDTO dto);
	
	/**
	 * 删除
	 */
	public void deleteAabcsxx(String aab001);

	/**
	 *  客户下拉列表
	 */
	@SuppressWarnings("unchecked")
	public List findAabcsxxComb();

	public void updateAabcsxxgx(String aab001, String aab014);

	public void findPageAabcsxx(Page page);


}
