package com.cxstock.action.ziliao;


import com.cxstock.action.BaseAction;
import com.cxstock.biz.ziliao.XmxxBiz;
import com.cxstock.pojo.Xmxx;
import com.cxstock.utils.pubutil.Page;

@SuppressWarnings("serial")
public class XmxxAction extends BaseAction  {
	
	private XmxxBiz xmxxBiz;
	
	private String spid;
	private String spname;
	private String xinghao;
	private String dw;
	private Double jhprice;
	private Double chprice;
	private Integer minnum;
	private String csname;
	private String bz;
	
	private Integer lbid;
	private String lbname;
	
	private String search;
	private String addupdate;
	
	/*
	 * 商品编号
	 */
	@SuppressWarnings("unchecked")
	public String getXmxxCode() {
		try {
			String code = xmxxBiz.getXmxxCode();
			this.outString(code);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}
	
	/** 
	 * 分页查询商品信息列表 
	 */
	public String findPageXmxx() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			if(lbid!=null&&lbid!=0){
				page.setWheres(" where t.lbid="+lbid);
			}else if(search!=null&&!"".equals(search)){
				StringBuffer buf = new StringBuffer(" where t.spid like '%");
				buf.append(search);
				buf.append("%' or t.spname like '%");
				buf.append(search);
				buf.append("%'");
				
				
				
				
				page.setWheres(buf.toString());
			}
			xmxxBiz.findPageXmxx(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}	
	
	/** 
	 * 期初库存备选商品列表
	 */
	public String findKcPageXmxx() {
		try {
			Page page = new Page();
			page.setStart(this.getStart());
			page.setLimit(this.getLimit());
			StringBuffer buf = new StringBuffer();
			if(search!=null&&!"".equals(search)){
				buf = new StringBuffer(" where t.spid like '%");
				buf.append(search);
				buf.append("%' or t.spname like '%");
				buf.append(search);
				buf.append("%'");
				page.setWheres(buf.toString());
			}else{
				buf = new StringBuffer(" where t.state=0");
			}
			page.setWheres(buf.toString());
			xmxxBiz.findPageXmxx(page);
			this.outPageString(page);
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	/**
	 * 保存/修改商品信息
	 */
	public String saveOrUpdateXmxx() {
		try {
			Xmxx xmxx = new Xmxx(spid, spname, xinghao, lbid, lbname, dw, 
					jhprice, chprice, jhprice, 0, 0d, minnum, csname, "0", bz);
			if("add".equals(addupdate)){
				if(jhprice==null){
					xmxx.setJhprice(0d);
					xmxx.setScjj(0d);
				}
				if(chprice==null)
					xmxx.setChprice(0d);
				if(minnum==null)
					xmxx.setMinnum(0);
				xmxxBiz.save(xmxx);
				this.outString("{success:true,message:'保存成功!'}");
			}else{
				xmxxBiz.updateXmxx(xmxx);
				this.outString("{success:true,message:'修改成功!'}");
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 this.outError();
		}
		return null;
	}
    
	/**
	 * 删除商品信息
	 */
	public String deleteXmxx() {
		try {
			boolean bool = xmxxBiz.deleteXmxx(spid);
			if(bool){
				this.outString("{success:true}");
			}else{
				this.outString("{success:false,error:'该商品已经发生单据，不能删除。'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outError();
		}
		return null;
	}

	public void setXmxxBiz(XmxxBiz xmxxBiz) {
		this.xmxxBiz = xmxxBiz;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public void setXinghao(String xinghao) {
		this.xinghao = xinghao;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public void setJhprice(Double jhprice) {
		this.jhprice = jhprice;
	}

	public void setChprice(Double chprice) {
		this.chprice = chprice;
	}

	public void setMinnum(Integer minnum) {
		this.minnum = minnum;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public void setLbname(String lbname) {
		this.lbname = lbname;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setAddupdate(String addupdate) {
		this.addupdate = addupdate;
	}

}
