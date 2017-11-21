package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.AaacsxxBiz;
import com.cxstock.biz.ghxt.dto.AaacsxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class AaacsxxAction extends BaseAction  {

	private java.lang.String aaa001;    //ID号                                                                                     
	private java.lang.String aaa002;    //期别                                                                                     
	private java.lang.String aaa003;    //报名起始时间                                                                             
	private java.lang.String aaa004;    //报名截止时间                                                                             
	private java.lang.String aaa005;    //报销起始时间                                                                             
	private java.lang.String aaa006;    //报名截止时间                                                                             
	private java.math.BigDecimal aaa007;    //缴费标准元                                                                               
	private java.math.BigDecimal aaa008;    //单笔最大报销限额                                                                         
	private java.math.BigDecimal aaa009;    //期内最大报销限额                                                                         
	private java.math.BigDecimal aaa010;    //单笔最低报销限额                                                                         
	private java.math.BigDecimal aaa011;    //期内最大报销次数                                                                         
	private java.lang.String aaa012;    //配置依据文件说明                                                                         
	private java.lang.String aaa013;    //对应年度                                                                                 
	private java.lang.String aaa014;    //标志0默认选择1正常2过期3无效                                                             
	private java.lang.String aaa015;    //配置用户                                                                                 
	private java.lang.String aaa016;    //配置时间  
 
	  
	  private AaacsxxBiz aaacsxxBiz;//-------------接口类
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
	  
	  
public String findPageAaacsxx() {
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
		if(aaa013!=null&&!"".equals(aaa013)){
			 
			buf.append(" and t.aaa013='"+aaa013+"'");
	     }
		 
		 if(aaa002!=null&&!"".equals(aaa002)){
 		 	buf.append(" and t.aaa002='"+aaa002+"'");
			 
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
		
		
		
		
		
		aaacsxxBiz.findPageAaacsxx(page);
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
public String saveOrUpdateAaacsxx() {
	try {
		AaacsxxDTO dto = new AaacsxxDTO(aaa001,aaa002,aaa003,aaa004,aaa005,aaa006,aaa007,aaa008,aaa009,aaa010,
				aaa011,aaa012,aaa013,aaa014,aaa015,aaa016);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		aaacsxxBiz.saveOrUpdateAaacsxx(dto);
		if(aaa001.equals("")||aaa001.equals(null)){
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
public String deleteAaacsxx() {
	try {
		aaacsxxBiz.deleteAaacsxx(aaa001);
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
public String updateAaacsxxgx() {
	try {
		aaacsxxBiz.updateAaacsxxgx(aaa001,aaa014);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}

/** 
 * 下拉列表
 */
public String findAaacsxxComb() {
	try {
		this.outListString(aaacsxxBiz.findAaacsxxComb());
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}
	


	public void setAaacsxxBiz(AaacsxxBiz aaacsxxBiz) {
		this.aaacsxxBiz = aaacsxxBiz;
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
	
	
	
	
	
	  

	public void setAaa001(java.lang.String aaa001) {
		this.aaa001 = aaa001;
	}
	public void setAaa002(java.lang.String aaa002) {
		this.aaa002 = aaa002;
	}
	public void setAaa003(java.lang.String aaa003) {
		this.aaa003 = aaa003;
	}
	public void setAaa004(java.lang.String aaa004) {
		this.aaa004 = aaa004;
	}
	public void setAaa005(java.lang.String aaa005) {
		this.aaa005 = aaa005;
	}
	public void setAaa006(java.lang.String aaa006) {
		this.aaa006 = aaa006;
	}
	public void setAaa007(java.math.BigDecimal aaa007) {
		this.aaa007 = aaa007;
	}
	public void setAaa008(java.math.BigDecimal aaa008) {
		this.aaa008 = aaa008;
	}
	public void setAaa009(java.math.BigDecimal aaa009) {
		this.aaa009 = aaa009;
	}
	public void setAaa010(java.math.BigDecimal aaa010) {
		this.aaa010 = aaa010;
	}
	public void setAaa011(java.math.BigDecimal aaa011) {
		this.aaa011 = aaa011;
	}
	public void setAaa012(java.lang.String aaa012) {
		this.aaa012 = aaa012;
	}
	public void setAaa013(java.lang.String aaa013) {
		this.aaa013 = aaa013;
	}
	public void setAaa014(java.lang.String aaa014) {
		this.aaa014 = aaa014;
	}
	public void setAaa015(java.lang.String aaa015) {
		this.aaa015 = aaa015;
	}
	public void setAaa016(java.lang.String aaa016) {
		this.aaa016 = aaa016;
	}
	
	
	
	
	
	

	
	
}

