package com.cxstock.biz.power;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.biz.power.dto.JgbDTO;
import com.cxstock.pojo.Dwxx;
import com.cxstock.utils.pubutil.Page;
import com.lbs.leaf.exception.BusinessException;
public interface JgbBiz {
	
	
	/*
	 * 机构树
	 */
	@SuppressWarnings("unchecked")
	public List findJgbTree();
	/**
	 * 分页查询机构列表
	 */
	public void findPageJgb(String pid,Page page);
	/*
	 * 保存/修改机构
	*/
	public void saveOrUpdateJgb(JgbDTO dto);
	
	/*
	 * 删除机构
	 */
	public void deleteJgb(String jgid);
	
	
}
