package com.cxstock.action.ghxt;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.ghxt.RylsfjxxBiz;
import com.cxstock.biz.ghxt.dto.RylsfjxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class RylsfjxxAction extends BaseAction  {



 

	private  java.lang.String sae201; //单档ID
    private  java.lang.String sae041; //理算业务ID
    private  java.lang.String sae202; //档案类型
    private  java.lang.String sae203; //档案文件类型
    private  java.lang.String sae204; //文件名
    private  java.lang.String sae205; //文件地址
    private  java.lang.String sae036; //上传人
    private  java.lang.String sae011; //上传日期
    private  java.lang.String sae012; //标志0未绑定，已绑定 
	  
	  private RylsfjxxBiz rylsfjxxBiz;//-------------接口类
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
	  
	  
public String findPageRylsfjxx() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		//page.setGwgl(this.getGwgl());
		
		
		
		StringBuffer buf = new StringBuffer(" where 1=1 ");
		
		
		//发送公文模块带来的参数
//		if(gwgl!=null&&"fsgw".equals(gwgl)){
//			//buf.append(" and t.userid="+this.getUserDTO().getUserid());
//		}
//		//共享公文模块带来的参数,共享公文只能在本机构间公文
//		if(gwgl!=null&&"gwgx".equals(gwgl)){
//			//buf.append(" and t.gw010='1' and t.aab001="+this.getUserDTO().getLbid());
// 		}
//		
//		if(startdate!=null&&enddate!=null){
//			//wheres.append(" and t.riqi between '")
//			//buf.append(" and to_char(t.aac006,'yyyy-mm-dd') between '");
//			//buf.append(startdate);
//			//buf.append("' and '");
//			//buf.append(enddate);
//			//buf.append("'");
//		}
		
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
//		if(sae075!=null&&!"100".equals(sae075)){
//			
//			
//			 
//			buf.append(" and t.sae075='"+sae075+"'");
//			 
//		}
		
		
		
		//是否首参
//		if(sae052!=null&&!"".equals(sae052)){
//			buf.append(" and t.sae052='"+sae052+"'");
//		}
//		//理算业务id
		if(sae041!=null&&!"".equals(sae041)){
			buf.append(" and t.sae041='"+sae041+"'");
		}	
		
		
		
//       if(search!=null&&!"".equals(search)){
//			buf.append(" and ( t.aab001 like '%");//单位编号
//			buf.append(search);
//			buf.append("%'  or t.sae033 like '%");//单位名称
//			buf.append(search);
//			buf.append("%'  or t.aac002 like '%");//身份证号
//			buf.append(search);
//			buf.append("%'  or t.aac003 like '%");//姓名
//			buf.append(search);			
//			buf.append("%'  or t.sae043 like '%"); //医保卡号
//			buf.append(search);
//			buf.append("%')");			
//		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		rylsfjxxBiz.findPageRylsfjxx(sae201, page);
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
public String saveOrUpdateRylsfjxx() {
	try {
		RylsfjxxDTO dto = new RylsfjxxDTO(sae201,sae041,sae202,sae203,sae204,sae205,sae036,sae011,sae012);
		
//		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
//		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		rylsfjxxBiz.saveOrUpdateRylsfjxx(dto);
		if(sae201.equals("")||sae201.equals(null)){
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
public String deleteRylsfjxx() {
	try {
		rylsfjxxBiz.deleteRylsfjxx(sae201);
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
		rylsfjxxBiz.updateRylsfjxxgx(sae201,sae012);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	


	public void setRylsfjxxBiz(RylsfjxxBiz rylsfjxxBiz) {
		this.rylsfjxxBiz = rylsfjxxBiz;
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
	
	
	


	
	   public void setSae201(java.lang.String sae201) {
			this.sae201 = sae201;
		}
		public void setSae041(java.lang.String sae041) {
			this.sae041 = sae041;
		}
		public void setSae202(java.lang.String sae202) {
			this.sae202 = sae202;
		}
		public void setSae203(java.lang.String sae203) {
			this.sae203 = sae203;
		}
		public void setSae204(java.lang.String sae204) {
			this.sae204 = sae204;
		}
		public void setSae205(java.lang.String sae205) {
			this.sae205 = sae205;
		}
		public void setSae036(java.lang.String sae036) {
			this.sae036 = sae036;
		}
		public void setSae011(java.lang.String sae011) {
			this.sae011 = sae011;
		}
		public void setSae012(java.lang.String sae012) {
			this.sae012 = sae012;
		}
	
	
	
	

	
	
}

