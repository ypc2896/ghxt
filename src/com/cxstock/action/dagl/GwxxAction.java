package com.cxstock.action.dagl;
import java.util.Date;

import com.cxstock.action.BaseAction;
import com.cxstock.biz.dagl.GwxxBiz;
import com.cxstock.biz.dagl.dto.GwxxDTO;

import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文信息管理
 */
@SuppressWarnings("serial")
public class GwxxAction extends BaseAction  {
	  private String dwbh00; //单位编号
//	  private String dwmc00; //单位名称
//	  private String dhua00; //单位电话
//	  private String dizhi0; //地址
//	  private String yzbm00; //邮政编码
//	  private String lxr000; //联系人
	  private String bz0000;//备注
	  private Integer lbid;
	  private String lbname;
	  
	  private Integer userid;  //用户ID
	  private String username; //用户名
	  private String gw001;//公文编号
	  private String gw002;//传阅标志
	  private String gw003;//公文标题
	  private String gw004;//公文文号
	  private String gw005;//发文单位
	  private Date gw006;//发文日期
	  private String gw007;//文件等级
	  private String gw008;//文件密级
	  private String gw009;//文件种类通知请示
	  private String gw010;//文件内容简述
	  private Date gw011;//录入日期
	  private Date gw012;//归档日期
	  private String gw013;//文件附件
	  private String gw014;//收发标志0收文1发文
	  private String gw015;//共享标志0未共享1共享
	  
	  private GwxxBiz gwxxBiz;//-------------接口类
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
	  
	  
public String findPageGwxx() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		//page.setGwgl(this.getGwgl());
		
		
		
		StringBuffer buf = new StringBuffer(" where 1=1 ");
		
		
		//发送公文模块带来的参数
		if(gwgl!=null&&"fsgw".equals(gwgl)){
			buf.append(" and t.userid="+this.getUserDTO().getUserid());
		}
		//共享公文模块带来的参数,共享公文只能在本机构间公文
		if(gwgl!=null&&"gwgx".equals(gwgl)){
			buf.append(" and t.gw015='1' and t.lbid="+this.getUserDTO().getLbid());
 		}
		
		if(startdate!=null&&enddate!=null){
			//wheres.append(" and t.riqi between '")
			buf.append(" and to_char(t.gw006,'yyyy-mm-dd') between '");
			buf.append(startdate);
			buf.append("' and '");
			buf.append(enddate);
			buf.append("'");
		}
		
		//a.equals(b)比较a和b是否相等。
		//共享公文模块，左侧文种分类树，点击传的参数
		if(gw009!=null&&!"99".equals(gw009)){
			 
			buf.append(" and t.gw009='"+gw009+"'");
		} 
		//发送公文模块 左侧树用到
		if(gw002!=null&&!"10".equals(gw002)){
			 
			buf.append(" and t.gw002='"+gw002+"'");
		}
		//管理公文模块 左侧树用到
		if(lbid!=null&&lbid!=0){
			buf.append(" and t.lbid="+lbid);
		}
		
		
		
		
		if(gw014!=null&&!"".equals(gw014)){
			buf.append(" and t.gw014="+gw014);
		}
		
		
        if(search!=null&&!"".equals(search)){
			buf.append(" and ( t.gw001 like '%");
			buf.append(search);
			buf.append("%'  or t.gw003 like '%");
			buf.append(search);
			buf.append("%'  or t.gw004 like '%");
			buf.append(search);
			buf.append("%')");

		}
		
		page.setWheres(buf.toString());
		
		
		
		
		
		gwxxBiz.findPageGwxx(dwbh00, page);
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
public String saveOrUpdateGwxx() {
	try {
		GwxxDTO dto = new GwxxDTO(dwbh00,bz0000,lbid,lbname,
				userid,username,gw001,gw002,gw003,gw004,gw005,gw006,gw007,gw008,gw009,gw010,
				gw011,gw012,gw013,gw014);
		
		dto.setUserid(this.getUserDTO().getUserid());//取当前登录用户ID
		dto.setUsername(this.getUserDTO().getUsername());//最当前用登录的用户名
         
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		gwxxBiz.saveOrUpdateGwxx(dto);
		if(dwbh00.equals("")||dwbh00.equals(null)){
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
public String deleteGwxx() {
	try {
		gwxxBiz.deleteGwxx(dwbh00);
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
public String updateGwxxgx() {
	try {
		gwxxBiz.updateGwxxgx(dwbh00,gw015);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


	public void setDwbh00(String dwbh00) {
		this.dwbh00 = dwbh00;
	}
	
	/**
	public void setDwmc00(String dwmc00) {
		this.dwmc00 = dwmc00;
	}
	public void setDhua00(String dhua00) {
		this.dhua00 = dhua00;
	}
	public void setDizhi0(String dizhi0) {
		this.dizhi0 = dizhi0;
	}
	public void setYzbm00(String yzbm00) {
		this.yzbm00 = yzbm00;
	}
	public void setLxr000(String lxr000) {
		this.lxr000 = lxr000;
	}
	
	
	*/
	public void setBz0000(String bz0000) {
		this.bz0000 = bz0000;
	}
	public void setGwxxBiz(GwxxBiz gwxxBiz) {
		this.gwxxBiz = gwxxBiz;
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
	
	
	public void setGwgl(String gwgl) {
		this.gwgl = gwgl;
	}
	
	
	
	
	
	
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}	  
	public void setUsername(String username) {
		this.username = username;
	}		  

	
	public void setGw001(String gw001) {
		this.gw001 = gw001;
	}
	public void setGw002(String gw002) {
		this.gw002 = gw002;
	}
	public void setGw003(String gw003) {
		this.gw003 = gw003;
	}
	public void setGw004(String gw004) {
		this.gw004 = gw004;
	}
	public void setGw005(String gw005) {
		this.gw005 = gw005;
	}
	public void setGw006(Date gw006) {
		this.gw006 = gw006;
	}
	
	
	public void setGw007(String gw007) {
		this.gw007 = gw007;
	}
	public void setGw008(String gw008) {
		this.gw008 = gw008;
	}
	public void setGw009(String gw009) {
		this.gw009 = gw009;
	}
	public void setGw010(String gw010) {
		this.gw010 = gw010;
	}
	public void setGw011(Date gw011) {
		this.gw011 = gw011;
	}
	public void setGw012(Date gw012) {
		this.gw012 = gw012;
	}
	public void setGw013(String gw013) {
		this.gw013 = gw013;
	}
	public void setGw014(String gw014) {
		this.gw014 = gw014;
	}
	public void setGw015(String gw015) {
		this.gw015 = gw015;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}	
	
	
}

