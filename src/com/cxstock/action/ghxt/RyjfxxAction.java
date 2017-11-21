package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.RyjfxxBiz;
import com.cxstock.biz.ghxt.dto.RyjfxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class RyjfxxAction extends BaseAction  {


	private  java.lang.String aae001; //缴费ID
	    private  java.lang.String aac001; //个人编号
	    private  java.lang.String aab001; //单位编号
	    private  java.lang.String aae005; //缴费期别
	    private  java.lang.String aae061; //应收单号单位汇总表ID
	    private  java.lang.String aae007; //是否实收是否实收0未实收，1财务已实收
	    private  java.lang.String aae008; //经办人
	    private  java.lang.String aae009; //经办日期
	    private  java.lang.String aab003; //单位名称

	    private java.lang.String aac009; //医保卡号                                                                                 
	    private java.lang.String aac002; //身份号码                                                                                 
	    private java.lang.String aac003; //姓名                                                                                     
	    private java.lang.String aac004; //性别                                                                                     
	    private java.lang.String aae010; //报名表序号                                                                               
	    private java.lang.String aae011; //报名备注                                                                                 
	    private java.lang.String aae012; //填表人                                                                                   
	    private java.lang.String aae013; //填表日期                                                                                 
	    private java.lang.String aae014; //报名表盖章办事处名称
	  
	  private RyjfxxBiz ryjfxxBiz;//-------------接口类
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
	  
	  
public String findPageRyjfxx() {
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
		//病案表点击行时，身份证号查询明细
		if(aac002!=null&&!"".equals(aac002)){
			 
			buf.append(" and t.aac002='"+aac002+"'");
		}
		//单位编号，上级可以看到下级的所以式左侧起用模糊查询，
		if(aab001!=null&&!"0".equals(aab001)){
			buf.append(" and t.aab001 like '"+aab001+"%'");
		}
		
		//单位缴费ID
		if(aae061!=null&&!"".equals(aae061)){
			buf.append(" and t.aae061='"+aae061+"'");
		}	
		//期别
		if(aae005!=null&&!"".equals(aae005)){
			buf.append(" and t.aae005='"+aae005+"'");
		}			
		//是否参医保
		//if(aac011!=null&&!"".equals(aac011)){
			//buf.append(" and t.aac011='"+aac011+"'");
		//}
		
		
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
		
		
		
		
		
		ryjfxxBiz.findPageRyjfxx(aae001, page);
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
public String saveOrUpdateRyjfxx() {
	try {
		RyjfxxDTO dto = new RyjfxxDTO(aae001,aac001,aab001,aae005,aae061,
				aae007,aae008,aae009,aab003,aac009,
				aac002,aac003,aac004,aae010,aae011,
				aae012,aae013,aae014);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		ryjfxxBiz.saveOrUpdateRyjfxx(dto);
		if(aae001.equals("")||aae001.equals(null)){
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
public String deleteRyjfxx() {
	try {
		ryjfxxBiz.deleteRyjfxx(aae001);
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
public String updateRyjfxxgx() {
	try {
		ryjfxxBiz.updateRyjfxxgx(aae001,aae007);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setRyjfxxBiz(RyjfxxBiz ryjfxxBiz) {
		this.ryjfxxBiz = ryjfxxBiz;
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
	
	
	
	   public void setAae001(java.lang.String aae001) {
			this.aae001 = aae001;
		}
		public void setAac001(java.lang.String aac001) {
			this.aac001 = aac001;
		}
		public void setAab001(java.lang.String aab001) {
			this.aab001 = aab001;
		}
		public void setAae005(java.lang.String aae005) {
			this.aae005 = aae005;
		}
		public void setAae061(java.lang.String aae061) {
			this.aae061 = aae061;
		}
		public void setAae007(java.lang.String aae007) {
			this.aae007 = aae007;
		}
		public void setAae008(java.lang.String aae008) {
			this.aae008 = aae008;
		}
		public void setAae009(java.lang.String aae009) {
			this.aae009 = aae009;
		}
		public void setAab003(java.lang.String aab003) {
			this.aab003 = aab003;
		}
		public void setAac009(java.lang.String aac009) {
			this.aac009 = aac009;
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
		public void setAae010(java.lang.String aae010) {
			this.aae010 = aae010;
		}
		public void setAae011(java.lang.String aae011) {
			this.aae011 = aae011;
		}
		public void setAae012(java.lang.String aae012) {
			this.aae012 = aae012;
		}
		public void setAae013(java.lang.String aae013) {
			this.aae013 = aae013;
		}
		public void setAae014(java.lang.String aae014) {
			this.aae014 = aae014;
		}

	
	
	
	
	
	
	

	
	
}

