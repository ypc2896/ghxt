package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.RyxxBiz;
import com.cxstock.biz.ghxt.dto.RyxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class RyxxAction extends BaseAction  {

	  private String aac001;//公文编号
	  private String aac002;//传阅标志
	  private String aac003;//公文标题
	  private String aac004;//公文文号
	  private String aac005;//发文单位
	  private String aab001;//发文单位
	  private String aac006;//发文日期
	  private String aac007;//文件等级
	  private String aac008;//文件密级
	  private String aac009;//文件种类通知请示
	  private String aac010;//文件内容简述
	  private String aac011;//录入日期
 
	  
	  private RyxxBiz ryxxBiz;//-------------接口类
	  private String search;
		private String startdate;
		private String enddate;	  
		private String gwgl;
	 
	  /** 
 * 分页查询单位信息列表 
 */
	  
	  
	/**indPageGwxx
	 * com.cxstock.action.dagl.GwxxAction -------3 
	 */
	  
	  
public String findPageRyxx() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		//page.setGwgl(this.getGwgl());
		
		
		
		StringBuffer buf = new StringBuffer(" where 1=1 ");
		
		
		//发送公文模块带来的参数
		if(gwgl!=null&&"fsgw".equals(gwgl)){
			//buf.append(" and t.userid="+this.getUserDTO().getUserid());
		}
		//共享公文模块带来的参数,共享公文只能在本机构间公文
		if(gwgl!=null&&"gwgx".equals(gwgl)){
			//buf.append(" and t.gw010='1' and t.aab001="+this.getUserDTO().getLbid());
 		}
		
		if(startdate!=null&&enddate!=null){
			//wheres.append(" and t.riqi between '")
			//buf.append(" and to_char(t.aac006,'yyyy-mm-dd') between '");
			//buf.append(startdate);
			//buf.append("' and '");
			//buf.append(enddate);
			//buf.append("'");
		}
		
		//a.equals(b)比较a和b是否相等。
		//共享公文模块，左侧文种分类树，点击传的参数
		if(aac009!=null&&!"99".equals(aac009)){
			 
			//buf.append(" and t.aac009='"+aac009+"'");
		} 
		//发送公文模块 左侧树用到
		if(aac002!=null&&!"10".equals(aac002)){
			 
			//buf.append(" and t.aac002='"+aac002+"'");
		}
		//管理公文模块 左侧树用到,从左侧起使用模糊查询，可以实现上级单位看到下级单位的人员信息
		if(aab001!=null&&!"0".equals(aab001)){
			buf.append(" and t.aab001 like '"+aab001+"%'");
		}
		
		
		
		//是否参医保
		if(aac011!=null&&!"".equals(aac011)){
			buf.append(" and t.aac011='"+aac011+"'");
		}
		
		
        if(search!=null&&!"".equals(search)){
			buf.append(" and ( t.aac002 like '%");
			buf.append(search);
			buf.append("%'  or t.aac003 like '%");
			buf.append(search);
			buf.append("%'  or t.aac009 like '%");
			buf.append(search);
			buf.append("%')");

		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		ryxxBiz.findPageRyxx(aab001, page);
		this.outPageString(page);
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}	  
/**
 * 保存/修改单位信息
 */
public String saveOrUpdateRyxx() {
	try {
		RyxxDTO dto = new RyxxDTO(aac001,aac002,aac003,aac004,aac005,aab001,aac006,aac007,aac008,aac009,aac010,
				aac011);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		ryxxBiz.saveOrUpdateRyxx(dto);
		if(aac001.equals("")||aac001.equals(null)){
			this.outString("{success:true,message:'新增成功!'}");
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
 * 删除
 */
public String deleteRyxx() {
	try {
		ryxxBiz.deleteRyxx(aac001);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}

/**
 * 更新共享标志
 */
public String updateRyxxgx() {
	try {
		ryxxBiz.updateRyxxgx(aac001,aac010);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setRyxxBiz(RyxxBiz ryxxBiz) {
		this.ryxxBiz = ryxxBiz;
	}

	 
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	public void setGwgl(String gwgl) {
		this.gwgl = gwgl;
	}
	
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}	
	
	
	
	
	
	  

	public void setAac001(java.lang.String aac001) {
		this.aac001 = aac001;
	}

	public void setAac002(java.lang.String aac002) {
		this.aac002 = aac002;
	}

	public void setAac003(java.lang.String aac003) {
		this.aac003 = aac003;
	}

	public void setAac004(java.lang.String aac004) {
		this.aac004 = aac004;
	}

	public void setAac006(java.lang.String aac006) {
		this.aac006 = aac006;
	}

	public void setAab001(java.lang.String aab001) {
		this.aab001 = aab001;
	}

	public void setAac005(java.lang.String aac005) {
		this.aac005 = aac005;
	}

	public void setAac007(java.lang.String aac007) {
		this.aac007 = aac007;
	}

	public void setAac008(java.lang.String aac008) {
		this.aac008 = aac008;
	}

	public void setAac009(java.lang.String aac009) {
		this.aac009 = aac009;
	}

	public void setAac010(java.lang.String aac010) {
		this.aac010 = aac010;
	}

	public void setAac011(java.lang.String aac011) {
		this.aac011 = aac011;
	}
	
	
	
	
	
	

	
	
}

