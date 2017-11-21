package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.DwjfxxBiz;
import com.cxstock.biz.ghxt.dto.DwjfxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class DwjfxxAction extends BaseAction  {

    private  java.lang.String aae061; //缴费ID
    private  java.lang.String aab001; //单位编号
    private  java.lang.String aae062; //缴费期别
    private  java.math.BigDecimal aae063; //应缴金额
    private  java.lang.String aae064; //经办人
    private  java.lang.String aae065; //经办日期
    private  java.math.BigDecimal aae072; //缴费人数
    private  java.lang.String aab003; //单位名称
    private  java.math.BigDecimal aae073; //缴费标准
    private  java.math.BigDecimal aae074; //人员比例
    private  java.math.BigDecimal aae075; //在职人数
    private  java.math.BigDecimal aae076; //内退人数
    private  java.math.BigDecimal aae077; //参加医保人数
    private  java.math.BigDecimal aae078; //未参加医保人数
    private  java.math.BigDecimal aae079; //变动人数
    private  java.lang.String aae080; //变动原因
    private  java.math.BigDecimal aae081; //农民工人数
    private  java.math.BigDecimal aae082; //非农民工人数
    private  java.lang.String aae083; //是否首次参加
    private  java.lang.String aab005; //负责人
    private  java.lang.String aab006; //负责人电话
    private  java.lang.String aab007; //单位地址
    private  java.lang.String aab011; //主席
    private  java.lang.String aab012; //主席电话
    private  java.lang.String aae084; //缴费标志
 
	private java.lang.String aae085;          //	备注复核审批财务意见        
    private java.lang.String aae086;          //	申报用户用                  
    private java.lang.String aae087;          //  申报日期                    
    private java.lang.String aae088;          //	复核人                      
    private java.lang.String aae089;          //  复核日期                    
    private java.lang.String aae090;          //	复核意见                    
    private java.lang.String aae091;          //	审批人                      
    private java.lang.String aae092;          //  审批日期                    
    private java.lang.String aae093;          //	审批意见                    
    private java.lang.String aae094;          //	财务收款人                  
    private java.lang.String aae095;          //  收款日期                    
    private java.lang.String aae096;          //	收款意见                    
    private java.lang.String aae097;          //	收款单据号                  
 
	  
	  private DwjfxxBiz dwjfxxBiz;//-------------接口类
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
	  
	  
public String findPageDwjfxx() {
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
		//if(aac009!=null&&!"99".equals(aac009)){
			 
			//buf.append(" and t.aac009='"+aac009+"'");
	//	} 
		//发送公文模块 左侧树用到
		//if(aac002!=null&&!"10".equals(aac002)){
			 
			//buf.append(" and t.aac002='"+aac002+"'");
		//}
		//管理公文模块 左侧树用到
		if(aae084!=null&&!"10".equals(aae084)){
			
			
			 
			buf.append(" and t.aae084='"+aae084+"'");
			 
		}
		
		
		
		//是否首参
		if(aae083!=null&&!"".equals(aae083)){
			buf.append(" and t.aae083='"+aae083+"'");
		}
		//期别
		if(aae062!=null&&!"".equals(aae062)){
			buf.append(" and t.aae062='"+aae062+"'");
		}	
		
		
		
        if(search!=null&&!"".equals(search)){
			buf.append(" and ( t.aab001 like '%");
			buf.append(search);
			buf.append("%'  or t.aab003 like '%");
			buf.append(search);
//			buf.append("%'  or t.aac009 like '%");
//			buf.append(search);
			buf.append("%')");

		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		dwjfxxBiz.findPageDwjfxx(aab001, page);
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
public String saveOrUpdateDwjfxx() {
	try {
		DwjfxxDTO dto = new DwjfxxDTO(aae061,aab001,aae062,aae063,aae064,aae065,aae072,aab003,aae073,
					aae074,aae075,aae076,aae077,aae078,aae079,aae080,aae081,aae082,aae083,aab005,aab006,aab007,
					aab011,aab012,aae084,aae085,aae086,aae087,aae088,aae089,aae090,aae091,aae092,aae093,aae094,
					aae095,aae096,aae097);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		dwjfxxBiz.saveOrUpdateDwjfxx(dto);
		if(aae061.equals("")||aae061.equals(null)){
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
public String deleteDwjfxx() {
	try {
		dwjfxxBiz.deleteDwjfxx(aae061);
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
public String updateDwjfxxgx() {
	try {
		dwjfxxBiz.updateDwjfxxgx(aae061,aae061);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setDwjfxxBiz(DwjfxxBiz dwjfxxBiz) {
		this.dwjfxxBiz = dwjfxxBiz;
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
	
	
	
	public void setAae061(java.lang.String aae061) {
		this.aae061 = aae061;
	}

	public void setAab001(java.lang.String aab001) {
		this.aab001 = aab001;
	}

	public void setAae062(java.lang.String aae062) {
		this.aae062 = aae062;
	}

	public void setAae063(java.math.BigDecimal aae063) {
		this.aae063 = aae063;
	}

	public void setAae064(java.lang.String aae064) {
		this.aae064 = aae064;
	}

	public void setAae065(java.lang.String aae065) {
		this.aae065 = aae065;
	}

	public void setAae072(java.math.BigDecimal aae072) {
		this.aae072 = aae072;
	}

	public void setAab003(java.lang.String aab003) {
		this.aab003 = aab003;
	}

	public void setAae073(java.math.BigDecimal aae073) {
		this.aae073 = aae073;
	}

	public void setAae074(java.math.BigDecimal aae074) {
		this.aae074 = aae074;
	}

	public void setAae075(java.math.BigDecimal aae075) {
		this.aae075 = aae075;
	}

	public void setAae076(java.math.BigDecimal aae076) {
		this.aae076 = aae076;
	}

	public void setAae077(java.math.BigDecimal aae077) {
		this.aae077 = aae077;
	}

	public void setAae078(java.math.BigDecimal aae078) {
		this.aae078 = aae078;
	}

	public void setAae079(java.math.BigDecimal aae079) {
		this.aae079 = aae079;
	}

	public void setAae080(java.lang.String aae080) {
		this.aae080 = aae080;
	}

	public void setAae081(java.math.BigDecimal aae081) {
		this.aae081 = aae081;
	}

	public void setAae082(java.math.BigDecimal aae082) {
		this.aae082 = aae082;
	}

	public void setAae083(java.lang.String aae083) {
		this.aae083 = aae083;
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

	public void setAab011(java.lang.String aab011) {
		this.aab011 = aab011;
	}

	public void setAab012(java.lang.String aab012) {
		this.aab012 = aab012;
	}

	public void setAae084(java.lang.String aae084) {
		this.aae084 = aae084;
	}

	public void setAae085(java.lang.String aae085) {
		this.aae085 = aae085;
	}

	public void setAae086(java.lang.String aae086) {
		this.aae086 = aae086;
	}

	public void setAae087(java.lang.String aae087) {
		this.aae087 = aae087;
	}

	public void setAae088(java.lang.String aae088) {
		this.aae088 = aae088;
	}

	public void setAae089(java.lang.String aae089) {
		this.aae089 = aae089;
	}

	public void setAae090(java.lang.String aae090) {
		this.aae090 = aae090;
	}

	public void setAae091(java.lang.String aae091) {
		this.aae091 = aae091;
	}

	public void setAae092(java.lang.String aae092) {
		this.aae092 = aae092;
	}

	public void setAae093(java.lang.String aae093) {
		this.aae093 = aae093;
	}

	public void setAae094(java.lang.String aae094) {
		this.aae094 = aae094;
	}

	public void setAae095(java.lang.String aae095) {
		this.aae095 = aae095;
	}

	public void setAae096(java.lang.String aae096) {
		this.aae096 = aae096;
	}

	public void setAae097(java.lang.String aae097) {
		this.aae097 = aae097;
	}	
	
	  


	
	
	
	
	
	

	
	
}

