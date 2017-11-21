package com.cxstock.action.ziliao;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.ziliao.ZdflBiz;
import com.cxstock.biz.ziliao.dto.ZdflDTO;

@SuppressWarnings("serial")
public class ZdflAction extends BaseAction  {
	
	private ZdflBiz zdflBiz;
	
	private Integer dwid;
	private String dwname;
	
	/** 
	 * 单位列表 
	 */
	public String findAllZdfl() {
		try {
			this.outListString(zdflBiz.findAllZdfl());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改单位
	 */
	public String saveOrUpdateZdfl() {
		try {
			ZdflDTO dto = new ZdflDTO(dwid,dwname);
			zdflBiz.saveOrUpdateZdfl(dto);
			if(dwid==null){
				this.outString("{success:true,message:'保存成功!'}");
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
	 * 删除单位
	 */
	public String deleteZdfl() {
		try {
			zdflBiz.deleteZdfl(dwid);
			this.outString("{success:true}");
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void setZdflBiz(ZdflBiz zdflBiz) {
		this.zdflBiz = zdflBiz;
	}

	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}
}
