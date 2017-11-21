package com.cxstock.biz.ghxt;

import java.util.List;

import com.cxstock.biz.ghxt.dto.AaacsxxDTO;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;

public interface AaacsxxBiz {
	
	/**
	 * 分页查询列表
	 */
	//public void findPageAaacsxx(Page page);
	
	/**
	 * 保存/修改
	 */
	public void saveOrUpdateAaacsxx(AaacsxxDTO dto);
	
	/**
	 * 删除
	 */
	public void deleteAaacsxx(String aaa001);

	
	
	
	
	
	
	/**
	 * 下拉列表
	 */
	public List<ComboData> findAaacsxxComb();	
	
	
	
	
	/**
	 *  客户下拉列表
	 */
	@SuppressWarnings("unchecked")
	//public List findAaacsxxComb();

	public void updateAaacsxxgx(String aaa001, String aaa014);

	public void findPageAaacsxx(Page page);


}
