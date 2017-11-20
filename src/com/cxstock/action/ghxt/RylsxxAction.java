package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.RylsxxBiz;
import com.cxstock.biz.ghxt.dto.RylsxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class RylsxxAction extends BaseAction  {



	private  java.lang.String sae041; //业务序号
    private  java.lang.String aac001; //个人编号
    private  java.lang.String aab001; //单位编号
    private  java.lang.String sae031; //患者病情
    private  java.lang.String sae005; //受助期别
    private  java.lang.String sae033; //备注
    private  java.lang.String sae034; //录入日期
    private  java.lang.String sae035; //录入人
    private  java.lang.String sae036; //理算日期
    private  java.lang.String sae037; //理算人
    private  java.lang.String sae038; //复核日期
    private  java.lang.String sae039; //复核人
    private  java.lang.String sae040; //审批日期
    private  java.lang.String sae042; //审批人
    private  java.lang.String sae029; //登记日期
    private  java.lang.String sae030; //登记人
    private  java.lang.String sae043; //医保卡号
    private  java.lang.String sae044; //审批意见
    private  java.lang.String sae047; //医院名称
    private  java.lang.String sae048; //持卡人姓名
    private  java.lang.String sae049; //银行名称
    private  java.lang.String sae050; //银行账号
    private  java.lang.String sae051; //持卡人联系电话
    private  java.lang.String sae052; //登记类型
    private  java.math.BigDecimal sae045; //基本医保金额
    private  java.math.BigDecimal sae046; //大病医保赔付金额
    private  java.math.BigDecimal sae032; //医疗费支付总金额
    private  java.math.BigDecimal sae053; //大病医保核定基数
    private  java.math.BigDecimal sae054; //大病医保核定比例
    private  java.math.BigDecimal sae055; //医保个人账户支付
    private  java.math.BigDecimal sae056; //医保自付金额
    private  java.math.BigDecimal sae057; //医保超出金额
    private  java.math.BigDecimal sae058; //受助次数
    private  java.math.BigDecimal sae059; //补助基数
    private  java.math.BigDecimal sae060; //补助比例
    private  java.math.BigDecimal sae061; //补助金额
    private  java.math.BigDecimal sae062; //救助基数
    private  java.math.BigDecimal sae063; //救助比例
    private  java.math.BigDecimal sae064; //救助金额
    private  java.math.BigDecimal sae065; //帮扶基数
    private  java.math.BigDecimal sae066; //帮扶比例
    private  java.math.BigDecimal sae067; //帮扶金额
    private  java.math.BigDecimal sae068; //补助自付金额
    private  java.math.BigDecimal sae069; //救助自付金额
    private  java.math.BigDecimal sae070; //帮扶自付金额
    private  java.lang.String sae099; //理算档案编号
    private  java.lang.String sae073; //住院时间
    private  java.lang.String sae074; //出院时间
    private  java.lang.String sae075; //业务进度
    private  java.lang.String sae076; //是否农民工
    private  java.lang.String sae077; //是否参医保
    private  java.lang.String sae078; //复核意见
    private  java.lang.String aac002; //身份号码
    private  java.lang.String aac003; //姓名
    private  java.math.BigDecimal aac006; //出生日期                 
    private  java.lang.String zfb003; //支付批次
    private  java.lang.String zfb027; //支付单据号
  
	  
	  private RylsxxBiz rylsxxBiz;//-------------接口类
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
	  
	  
public String findPageRylsxx() {
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
		if(sae075!=null&&!"100".equals(sae075)){
			
			
			 
			buf.append(" and t.sae075='"+sae075+"'");
			 
		}
		
		
		
		//是否首参
		if(sae052!=null&&!"".equals(sae052)){
			buf.append(" and t.sae052='"+sae052+"'");
		}
		//期别
		if(sae005!=null&&!"".equals(sae005)){
			buf.append(" and t.sae005='"+sae005+"'");
		}	
		
		//批次
		if(zfb003!=null&&!"".equals(zfb003)){
			buf.append(" and t.zfb003='"+zfb003+"'");
		}			
		
       if(search!=null&&!"".equals(search)){
			buf.append(" and ( t.aab001 like '%");//单位编号
			buf.append(search);
			buf.append("%'  or t.sae033 like '%");//单位名称
			buf.append(search);
			buf.append("%'  or t.aac002 like '%");//身份证号
			buf.append(search);
			buf.append("%'  or t.aac003 like '%");//姓名
			buf.append(search);			
			buf.append("%'  or t.sae043 like '%"); //医保卡号
			buf.append(search);
			buf.append("%')");			
		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		rylsxxBiz.findPageRylsxx(sae041, page);
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
public String saveOrUpdateRylsxx() {
	try {
		RylsxxDTO dto = new RylsxxDTO(sae041,aac001,aab001,sae031,sae005,sae033,sae034,sae035,sae036,sae037,sae038,
				sae039,sae040,sae042,sae029,sae030,sae043,sae044,sae047,sae048,sae049,sae050,sae051,sae052,sae045,
				sae046,sae032,sae053,sae054,sae055,sae056,sae057,sae058,sae059,sae060,sae061,sae062,sae063,sae064,
				sae065,sae066,sae067,sae068,sae069,sae070,sae099,sae073,sae074,sae075,sae076,sae077,sae078,aac002,
				aac003,aac006,zfb003,zfb027);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		rylsxxBiz.saveOrUpdateRylsxx(dto);
		if(sae041.equals("")||sae041.equals(null)){
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
public String deleteRylsxx() {
	try {
		rylsxxBiz.deleteRylsxx(sae041);
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
public String updateRylsxxgx() {
	try {
		rylsxxBiz.updateRylsxxgx(sae041,sae075);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setRylsxxBiz(RylsxxBiz rylsxxBiz) {
		this.rylsxxBiz = rylsxxBiz;
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
	
	
	
	public void setSae041(java.lang.String sae041) {
		this.sae041 = sae041;
	}
	public void setAac001(java.lang.String aac001) {
		this.aac001 = aac001;
	}
	public void setSae031(java.lang.String sae031) {
		this.sae031 = sae031;
	}
	public void setSae005(java.lang.String sae005) {
		this.sae005 = sae005;
	}
	public void setSae033(java.lang.String sae033) {
		this.sae033 = sae033;
	}
	public void setSae034(java.lang.String sae034) {
		this.sae034 = sae034;
	}
	public void setSae035(java.lang.String sae035) {
		this.sae035 = sae035;
	}
	public void setSae036(java.lang.String sae036) {
		this.sae036 = sae036;
	}
	public void setSae037(java.lang.String sae037) {
		this.sae037 = sae037;
	}
	public void setSae038(java.lang.String sae038) {
		this.sae038 = sae038;
	}
	public void setSae039(java.lang.String sae039) {
		this.sae039 = sae039;
	}
	public void setSae040(java.lang.String sae040) {
		this.sae040 = sae040;
	}
	public void setSae042(java.lang.String sae042) {
		this.sae042 = sae042;
	}
	public void setSae029(java.lang.String sae029) {
		this.sae029 = sae029;
	}
	public void setSae030(java.lang.String sae030) {
		this.sae030 = sae030;
	}
	public void setSae043(java.lang.String sae043) {
		this.sae043 = sae043;
	}
	public void setSae044(java.lang.String sae044) {
		this.sae044 = sae044;
	}
	public void setSae047(java.lang.String sae047) {
		this.sae047 = sae047;
	}
	public void setSae048(java.lang.String sae048) {
		this.sae048 = sae048;
	}
	public void setSae049(java.lang.String sae049) {
		this.sae049 = sae049;
	}
	public void setSae050(java.lang.String sae050) {
		this.sae050 = sae050;
	}
	public void setSae051(java.lang.String sae051) {
		this.sae051 = sae051;
	}
	public void setSae052(java.lang.String sae052) {
		this.sae052 = sae052;
	}
	public void setSae045(java.math.BigDecimal sae045) {
		this.sae045 = sae045;
	}
	public void setSae046(java.math.BigDecimal sae046) {
		this.sae046 = sae046;
	}
	public void setSae032(java.math.BigDecimal sae032) {
		this.sae032 = sae032;
	}
	public void setSae053(java.math.BigDecimal sae053) {
		this.sae053 = sae053;
	}
	public void setSae054(java.math.BigDecimal sae054) {
		this.sae054 = sae054;
	}
	public void setSae055(java.math.BigDecimal sae055) {
		this.sae055 = sae055;
	}
	public void setSae056(java.math.BigDecimal sae056) {
		this.sae056 = sae056;
	}
	public void setSae057(java.math.BigDecimal sae057) {
		this.sae057 = sae057;
	}
	public void setSae058(java.math.BigDecimal sae058) {
		this.sae058 = sae058;
	}
	public void setSae059(java.math.BigDecimal sae059) {
		this.sae059 = sae059;
	}
	public void setSae060(java.math.BigDecimal sae060) {
		this.sae060 = sae060;
	}
	public void setSae061(java.math.BigDecimal sae061) {
		this.sae061 = sae061;
	}
	public void setSae062(java.math.BigDecimal sae062) {
		this.sae062 = sae062;
	}
	public void setSae063(java.math.BigDecimal sae063) {
		this.sae063 = sae063;
	}
	public void setSae064(java.math.BigDecimal sae064) {
		this.sae064 = sae064;
	}
	public void setSae065(java.math.BigDecimal sae065) {
		this.sae065 = sae065;
	}
	public void setSae066(java.math.BigDecimal sae066) {
		this.sae066 = sae066;
	}
	public void setSae067(java.math.BigDecimal sae067) {
		this.sae067 = sae067;
	}
	public void setSae068(java.math.BigDecimal sae068) {
		this.sae068 = sae068;
	}
	public void setSae069(java.math.BigDecimal sae069) {
		this.sae069 = sae069;
	}
	public void setSae070(java.math.BigDecimal sae070) {
		this.sae070 = sae070;
	}
	public void setSae099(java.lang.String sae099) {
		this.sae099 = sae099;
	}
	public void setSae073(java.lang.String sae073) {
		this.sae073 = sae073;
	}
	public void setSae074(java.lang.String sae074) {
		this.sae074 = sae074;
	}
	public void setSae075(java.lang.String sae075) {
		this.sae075 = sae075;
	}
	public void setSae076(java.lang.String sae076) {
		this.sae076 = sae076;
	}
	public void setSae077(java.lang.String sae077) {
		this.sae077 = sae077;
	}
	public void setSae078(java.lang.String sae078) {
		this.sae078 = sae078;
	}
	public void setAac002(java.lang.String aac002) {
		this.aac002 = aac002;
	}
	public void setAac003(java.lang.String aac003) {
		this.aac003 = aac003;
	}
	public void setAac006(java.math.BigDecimal aac006) {
		this.aac006 = aac006;
	}	
	  
	public void setZfb003(java.lang.String zfb003) {
		this.zfb003 = zfb003;
	}	
	
	public void setZfb027(java.lang.String zfb027) {
		this.zfb027 = zfb027;
	}	

	
	
	
	
	
	

	
	
}

