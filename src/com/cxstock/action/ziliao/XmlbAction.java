package com.cxstock.action.ziliao;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.ziliao.XmlbBiz;
import com.cxstock.biz.ziliao.dto.XmlbDTO;

@SuppressWarnings("serial")
public class XmlbAction extends BaseAction  {
	
	private XmlbBiz xmlbBiz;
	
	private Integer lbid;
	private String lbname;
	private Integer pid;
	
	/** 
	 * 商品类别树
	 */
	public String findXmlbTree() {
		try {
			this.outTreeJsonList(xmlbBiz.findXmlbTree());
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}	

	/**
	 * 保存/修改商品类别
	 */
	public String saveOrUpdateXmlb() {
		try {
			XmlbDTO dto = new XmlbDTO(lbid,lbname,pid);
			int id = xmlbBiz.saveOrUpdateXmlb(dto);
			if(lbid==null){
				this.outString("{success:true,message:"+id+"}");
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
	 * 删除商品类别
	 */
	public String deleteXmlb() {
		try {
			if(xmlbBiz.deleteXmlb(lbid)){
				this.outString("true");
			}else{
				this.outString("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void setXmlbBiz(XmlbBiz xmlbBiz) {
		this.xmlbBiz = xmlbBiz;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public void setLbname(String lbname) {
		this.lbname = lbname;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
