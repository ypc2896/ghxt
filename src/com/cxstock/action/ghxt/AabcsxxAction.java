package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.AabcsxxBiz;
import com.cxstock.biz.ghxt.dto.AabcsxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class AabcsxxAction extends BaseAction  {

	private java.lang.String aab001;      //ID                                     
	private java.lang.String aab002;      //期别                                   
	private java.lang.String aab003;      //救助方式名称                           
	private java.math.BigDecimal aab004;      //救助比例                           
	private java.lang.String aab005;      //状态0正常1为禁用                       
	private java.lang.String aab006;      //备注                                   
	private java.lang.String aab007;      //使用等级0为普通用户1中层领导2高级领导  
	private java.lang.String aab008;      //是否要求有医保                         
	private java.math.BigDecimal aab009;      //基数下限                           
	private java.math.BigDecimal aab010;      //基数上限                           

 
	  
	  private AabcsxxBiz aabcsxxBiz;//-------------接口类
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
	  
	  
public String findPageAabcsxx() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		//page.setGwgl(this.getGwgl());
		
		
		
		StringBuffer buf = new StringBuffer(" where 1=1 ");
		
		
		//发送公文模块带来的参数
		//if(gwgl!=null&&"fsgw".equals(gwgl)){
			//buf.append(" and t.userid="+this.getUserDTO().getUserid());
		//}
		//共享公文模块带来的参数,共享公文只能在本机构间公文
		//if(gwgl!=null&&"gwgx".equals(gwgl)){
			//buf.append(" and t.gw010='1' and t.aab001="+this.getUserDTO().getLbid());
 		//}
		
		//if(startdate!=null&&enddate!=null){
			//wheres.append(" and t.riqi between '")
			//buf.append(" and to_char(t.aac006,'yyyy-mm-dd') between '");
			//buf.append(startdate);
			//buf.append("' and '");
			//buf.append(enddate);
			//buf.append("'");
		//}
		
		//a.equals(b)比较a和b是否相等。
		//共享公文模块，左侧文种分类树，点击传的参数
		//if(aac009!=null&&!"99".equals(aac009)){
			 
			//buf.append(" and t.aac009='"+aac009+"'");
		//} 
		//发送公文模块 左侧树用到
		if(aab002!=null&&!"".equals(aab002)){
			 
			buf.append(" and t.aab002='"+aab002+"'");
	     }
		 
 
		
		
		
		//是否参医保
		//if(aac011!=null&&!"".equals(aac011)){
		//	buf.append(" and t.aac011='"+aac011+"'");
		//}
		
		
//        if(search!=null&&!"".equals(search)){
//			buf.append(" and ( t.aac002 like '%");
//			buf.append(search);
//			buf.append("%'  or t.aac003 like '%");
//			buf.append(search);
//			buf.append("%'  or t.aac009 like '%");
//			buf.append(search);
//			buf.append("%')");
//
//		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		aabcsxxBiz.findPageAabcsxx(page);
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
public String saveOrUpdateAabcsxx() {
	try {
		AabcsxxDTO dto = new AabcsxxDTO(aab001,aab002,aab003,aab004,aab005,aab006,aab007,aab008,aab009,aab010);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		aabcsxxBiz.saveOrUpdateAabcsxx(dto);
		if(aab001.equals("")||aab001.equals(null)){
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
public String deleteAabcsxx() {
	try {
		aabcsxxBiz.deleteAabcsxx(aab001);
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
public String updateAabcsxxgx() {
	try {
		aabcsxxBiz.updateAabcsxxgx(aab001,aab005);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setAabcsxxBiz(AabcsxxBiz aabcsxxBiz) {
		this.aabcsxxBiz = aabcsxxBiz;
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
	
	
	
	
	
	  

	public void setAab001(java.lang.String aab001) {
		this.aab001 = aab001;
	}
	public void setAab002(java.lang.String aab002) {
		this.aab002 = aab002;
	}
	public void setAab003(java.lang.String aab003) {
		this.aab003 = aab003;
	}
	public void setAab004(java.math.BigDecimal aab004) {
		this.aab004 = aab004;
	}
	public void setAab005(java.lang.String aab005) {
		this.aab005 = aab005;
	}
	public void setAab006(java.lang.String aab006) {
		this.aab006 = aab006;
	}
	public void setAab007(java.lang.String aab007) {
		this.aab007 = aab007;
	}
	public void setAab008(java.lang.String aab008) {
		this.aab008 = aab008;
	}
	public void setAab009(java.math.BigDecimal aab009) {
		this.aab009 = aab009;
	}
	public void setAab010(java.math.BigDecimal aab010) {
		this.aab010 = aab010;
	}	
	
	
	
}

