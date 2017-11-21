package com.cxstock.action.power;
import com.cxstock.action.BaseAction;
import com.cxstock.biz.dagl.DwxxBiz;
import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.biz.power.JgbBiz;
import com.cxstock.biz.power.dto.JgbDTO;
import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 单位信息管理
 */
@SuppressWarnings("serial")
public class JgbAction extends BaseAction  {
	private  java.lang.String jgid; //机构ID
	private  java.lang.String jgmc; //机构名称
    private  java.lang.String pid; //父ID
    private JgbBiz jgbBiz;
    public void setJgid(java.lang.String jgid) {
		this.jgid = jgid;
	}
	public void setJgmc(java.lang.String jgmc) {
		this.jgmc = jgmc;
	}
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}
	/** 
	 * 机构树
	 */
	public String findJgbTree() {
		try {
			this.outTreeJsonList(jgbBiz.findJgbTree());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}	
	/** 
	 * 分页查询机构信息列表 
	 */
	public String findPageJgb() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			jgbBiz.findPageJgb(pid, page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}	  
/**
 * 保存/修改机构信息
 */
public String saveOrUpdateJgb() {
	try {
		JgbDTO dto = new JgbDTO(jgid,jgmc,pid);
		jgbBiz.saveOrUpdateJgb(dto);
		if(jgid.equals("")||jgid.equals(null)){
			this.outString("{success:true,message:'新增成功!',jgid:'"+dto.getJgid()+"'}");
		}else{
			this.outString("{success:true,message:'修改成功!'}");
		}
	} catch (Exception e) {
		 e.printStackTrace();
		 this.outError();
	}
	return null;
} 
/**
 * 删除机构
 */
public String deleteJgb() {
	try {
		jgbBiz.deleteJgb(jgid);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();	}
	return null;
}
public void setJgbBiz(JgbBiz jgbBiz) {
	this.jgbBiz = jgbBiz;
}

	 
	

}

