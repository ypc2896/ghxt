package com.cxstock.action.dagl;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.cxstock.action.BaseAction;
 
import com.cxstock.biz.dagl.GwxxBiz;
import com.cxstock.biz.dagl.GwycBiz;
import com.cxstock.biz.dagl.dto.GwycDTO;




import javax.sound.midi.Sequence;
import com.cxstock.dao.BaseDAO;
 



//import com.cxstock.biz.ziliao.GysBiz;
//import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 公文阅处信息管理
 */
@SuppressWarnings("serial")
public class GwycAction extends BaseAction  {
	  private String dwbh00; //单位编号

	  private String bz0000;//备注

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

	  private String gw013;//文件附件
	  private String gw014;//收发标志0收文1发文
 
	  private String yc001;//公文传阅ID
	  private String yc002;//公文的id
	  private String yc003;//接收人所在部门
	  private String yc004;//公文接收者的用户名ID
	  private String yc005;//是否看过公文 0:未阅，1已阅，2正办，3办结
	  private Date yc006;//确认时间，阅读时间
	  private String yc007;//本人确认意见
	  private String yc008;//转发人
	  private String yc009;//转发人部门
	  private Date yc010;//转发日期
	  private Integer yc011;//是否提醒标志0为不提醒，1为提醒
	  private String yc012;//转发人编号
	  private String yc013;//收藏标志
	  private String username;
	  
	  private GwycBiz gwycBiz;//-------------接口类
	  private String search;
	  private String startdate;
	  private String enddate;	  
	  private String gwgl;
	 
	  /** 
 * 分页查询单位信息列表 
 */
	  
	  
	/**indPageGwyc
	 * com.cxstock.action.dagl.GwycAction -------3 
	 */
	  
	  
public String findPageGwyc() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		StringBuffer buf = new StringBuffer(" where 1=1 ");
		
		
		if(gwgl!=null&&"jsgw".equals(gwgl)){
			buf.append(" and t.yc004=");
			buf.append(this.getUserDTO().getUserid());
		}	

		
		if(startdate!=null&&enddate!=null){
			//wheres.append(" and t.riqi between '")
			buf.append(" and to_char(t.gw006,'yyyy-mm-dd') between '");
			buf.append(startdate);
			buf.append("' and '");
			buf.append(enddate);
			buf.append("'");
		}
		if(yc005!=null&&!"10".equals(yc005)){
			buf.append(" and t.yc005="+yc005);
		} 
		if(yc002!=null){
			buf.append(" and t.dwbh00="+yc002);//视图中只有公文编号dwbh00
		} 		
		if(yc013!=null&&!"9".equals(yc013)){
			buf.append(" and t.yc013="+yc013);
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
		 
		
		
		
		
		gwycBiz.findPageGwyc(page);
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
public String saveOrUpdateGwyc() {
	try {
		GwycDTO dto = new GwycDTO(yc001,yc002,yc003,yc004,yc005,yc006,yc007,yc008,yc009,yc010,
				yc011,yc012,yc013);
		
		dto.setYc005("0");//0未阅状态。1已阅，2正办，3办结。
		dto.setYc006(new Date());//日期为空时会报错，保以先给个初值。随后阅处时再进行改正。
		dto.setYc008(this.getUserDTO().getUsername());//最当前用登录的用户名为转发人名称
		dto.setYc009(this.getUserDTO().getJgid());//最当前用户的部门ID号
		dto.setYc010(new Date());//转发日期为当前系统日期
		dto.setYc011(this.getUserDTO().getUserid());//取当前登录用户ID为转发人ID
		dto.setYc012("1");   //是否提醒为1，表示提醒，0不提醒。
		dto.setYc013("0");   //收藏标，0为未收藏。1为收藏。
		
         //新增时yc001不给值，则为空，这样会在过程中按序列新增一个编号。
		
		
		
		//将机构ID和机构名称，置为当前用户的机构ID和机构名称，这样会公文管理中的修改和新增，保存为当前用户的机构ID和机构名称。
//		dto.setLbid(this.getUserDTO().getLbid());//取当前登录用户的机构ID
//		dto.setLbname(this.getUserDTO().getLbname());//最当前用户的机构名
		
		
		
		
		
		gwycBiz.saveOrUpdateGwyc(dto);
		 
		
		if(yc001.equals("")||yc001.equals(null)){
			this.outString("{success:true,message:'发送操作成功!自动取消同一用户重复发送的情况。'}");
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
public String deleteGwyc() {
	try {
		gwycBiz.deleteGwyc(yc001);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}

/**
 * 删除
 */
public String deleteGwycgwid() {
	try {
		gwycBiz.deleteGwycgwid(yc002);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}


  
	 
	public void setSearch(String search) {
		this.search = search;
	}

	public void setGwycBiz(GwycBiz gwycBiz) {
		this.gwycBiz = gwycBiz;
	}	
	
	public void setGwgl(String gwgl) {
		this.gwgl = gwgl;
	}
	
	
	
	public void setDwbh00(String dwbh00) {
		this.dwbh00 = dwbh00;
	}

	public void setBz0000(String bz0000) {
		this.bz0000 = bz0000;
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

	public void setGw013(String gw013) {
		this.gw013 = gw013;
	}
	public void setGw014(String gw014) {
		this.gw014 = gw014;
	}	
	
	
	
	
	
	
	
	public void setYc001(String yc001) {
		this.yc001 = yc001;
	}
	public void setYc002(String yc002) {
		this.yc002 = yc002;
	}
	public void setYc003(String yc003) {
		this.yc003 = yc003;
	}
	public void setYc004(String yc004) {
		this.yc004 = yc004;
	}
	public void setYc005(String yc005) {
		this.yc005 = yc005;
	}
	public void setYc006(Date yc006) {
		this.yc006 = yc006;
	}
	
	
	public void setYc007(String yc007) {
		this.yc007 = yc007;
	}
	public void setYc008(String yc008) {
		this.yc008 = yc008;
	}
	public void setYc009(String yc009) {
		this.yc009 = yc009;
	}
	public void setYc010(Date yc010) {
		this.yc010 = yc010;
	}
	public void setYc011(Integer yc011) {
		this.yc011 = yc011;
	}
	public void setYc012(String yc012) {
		this.yc012 = yc012;
	}
	public void setYc013(String yc013) {
		this.yc013 = yc013;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}		
		
		
		
			
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}	
	
	
}

