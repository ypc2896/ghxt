package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.RylszfxxBiz;
import com.cxstock.biz.ghxt.dto.RylszfxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class RylszfxxAction extends BaseAction  {



	private java.lang.String zf001;     //ID支付业务ID与支付明细对应回写到理算支付明细表病案信息表                    
	private java.lang.String zf002;     //所属期别                                                                    
	private java.lang.String zf003;     //支付批次                                                                    
	private java.lang.String zf004;     //病案起始编号                                                                
	private java.lang.String zf005;     //病案截止编号                                                                
	private java.math.BigDecimal zf006;     //应支付人数                                                              
	private java.math.BigDecimal zf007;     //应支付金额                                                              
	private java.math.BigDecimal zf008;     //先行垫付人数                                                            
	private java.math.BigDecimal zf009;     //先行垫付金额                                                            
	private java.math.BigDecimal zf010;     //实际支付人数                                                            
	private java.math.BigDecimal zf011;     //实际支付金额                                                            
	private java.lang.String zf012;     //支付类型0为互助支付1先行垫付2其他支付                                       
	private java.math.BigDecimal zf013;     //已支付人数                                                              
	private java.math.BigDecimal zf014;     //已支付金额                                                              
	private java.lang.String zf015;     //经办时间                                                                    
	private java.lang.String zf016;     //经办人                                                                      
	private java.lang.String zf017;     //经办人意见                                                                  
	private java.lang.String zf018;     //复核时间                                                                    
	private java.lang.String zf019;     //复核人                                                                      
	private java.lang.String zf020;     //复核人意见                                                                  
	private java.lang.String zf021;     //审批人时间                                                                  
	private java.lang.String zf022;     //审批人                                                                      
	private java.lang.String zf023;     //审批人意见                                                                  
	private java.lang.String zf024;     //支出时间                                                                    
	private java.lang.String zf025;     //支出经办人                                                                  
	private java.lang.String zf026;     //支出情况                                                                    
	private java.lang.String zf027;     //支出单据编号                                                                
	private java.lang.String zf028;     //支出银行                                                                    
	private java.lang.String zf029;     //支出银行账户                                                                
	private java.math.BigDecimal zf030;     //支出前支出累计                                                          
	private java.math.BigDecimal zf031;     //支出后支出累计                                                          
	private java.lang.String zf032;     //状态0未申报1已申报待复核2复核中3已复核4待审批5已审批6待支付7已付出8已记账   
            
 
	  
	  private RylszfxxBiz rylszfxxBiz;//-------------接口类
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
	  
	  
public String findPageRylszfxx() {
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
		if(zf032!=null&&!"100".equals(zf032)){
			 
			buf.append(" and t.zf032='"+zf032+"'");
				}
		//管理公文模块 左侧树用到
		//if(sae075!=null&&!"100".equals(sae075)){
			
			
			 
		//	buf.append(" and t.sae075='"+sae075+"'");
			 
		//}
		
		
		
		//
		if(zf012!=null&&!"".equals(zf012)){
			buf.append(" and t.zf012='"+zf012+"'");
		}
		
		
		//期别
		if(zf002!=null&&!"".equals(zf002)){
			buf.append(" and t.zf002='"+zf002+"'");
		}	
		
		
		
       if(search!=null&&!"".equals(search)){
			buf.append(" and ( t.zf027 like '%");//单据编号
			buf.append(search);
			buf.append("%'  or t.zf028 like '%");//银行名称
			buf.append(search);
			buf.append("%'  or t.zf003 like '%");//支付批次
			buf.append(search);
			buf.append("%'  or t.zf029 like '%");//账户
			buf.append(search);			
			//buf.append("%'  or t.sae043 like '%"); //医保卡号
			//buf.append(search);
			buf.append("%')");			
		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		rylszfxxBiz.findPageRylszfxx(zf001, page);
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
public String saveOrUpdateRylszfxx() {
	try {
		RylszfxxDTO dto = new RylszfxxDTO(zf001,zf002,zf003,zf004,zf005,zf006,zf007,zf008,zf009,zf010,zf011,
				zf012,zf013,zf014,zf015,zf016,zf017,zf018,zf019,zf020,zf021,zf022,zf023,zf024,zf025,zf026,
				zf027,zf028,zf029,zf030,zf031,zf032);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		rylszfxxBiz.saveOrUpdateRylszfxx(dto);
		if(zf001.equals("")||zf001.equals(null)){
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
public String deleteRylszfxx() {
	try {
		rylszfxxBiz.deleteRylszfxx(zf001);
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
public String updateRylszfxxgx() {
	try {
		rylszfxxBiz.updateRylszfxxgx(zf001,zf032);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setRylszfxxBiz(RylszfxxBiz rylszfxxBiz) {
		this.rylszfxxBiz = rylszfxxBiz;
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
	
	
	
	
	
	
	public void setZf001(java.lang.String zf001) {
		this.zf001 = zf001;
	}

	public void setZf002(java.lang.String zf002) {
		this.zf002 = zf002;
	}

	public void setZf003(java.lang.String zf003) {
		this.zf003 = zf003;
	}

	public void setZf004(java.lang.String zf004) {
		this.zf004 = zf004;
	}

	public void setZf005(java.lang.String zf005) {
		this.zf005 = zf005;
	}

	public void setZf006(java.math.BigDecimal zf006) {
		this.zf006 = zf006;
	}

	public void setZf007(java.math.BigDecimal zf007) {
		this.zf007 = zf007;
	}

	public void setZf008(java.math.BigDecimal zf008) {
		this.zf008 = zf008;
	}

	public void setZf009(java.math.BigDecimal zf009) {
		this.zf009 = zf009;
	}

	public void setZf010(java.math.BigDecimal zf010) {
		this.zf010 = zf010;
	}

	public void setZf011(java.math.BigDecimal zf011) {
		this.zf011 = zf011;
	}

	public void setZf012(java.lang.String zf012) {
		this.zf012 = zf012;
	}

	public void setZf013(java.math.BigDecimal zf013) {
		this.zf013 = zf013;
	}

	public void setZf014(java.math.BigDecimal zf014) {
		this.zf014 = zf014;
	}

	public void setZf015(java.lang.String zf015) {
		this.zf015 = zf015;
	}

	public void setZf016(java.lang.String zf016) {
		this.zf016 = zf016;
	}

	public void setZf017(java.lang.String zf017) {
		this.zf017 = zf017;
	}

	public void setZf018(java.lang.String zf018) {
		this.zf018 = zf018;
	}

	public void setZf019(java.lang.String zf019) {
		this.zf019 = zf019;
	}

	public void setZf020(java.lang.String zf020) {
		this.zf020 = zf020;
	}

	public void setZf021(java.lang.String zf021) {
		this.zf021 = zf021;
	}

	public void setZf022(java.lang.String zf022) {
		this.zf022 = zf022;
	}

	public void setZf023(java.lang.String zf023) {
		this.zf023 = zf023;
	}

	public void setZf024(java.lang.String zf024) {
		this.zf024 = zf024;
	}

	public void setZf025(java.lang.String zf025) {
		this.zf025 = zf025;
	}

	public void setZf026(java.lang.String zf026) {
		this.zf026 = zf026;
	}

	public void setZf027(java.lang.String zf027) {
		this.zf027 = zf027;
	}

	public void setZf028(java.lang.String zf028) {
		this.zf028 = zf028;
	}

	public void setZf029(java.lang.String zf029) {
		this.zf029 = zf029;
	}

	public void setZf030(java.math.BigDecimal zf030) {
		this.zf030 = zf030;
	}

	public void setZf031(java.math.BigDecimal zf031) {
		this.zf031 = zf031;
	}

	public void setZf032(java.lang.String zf032) {
		this.zf032 = zf032;
	}
	
	

	
	
}

