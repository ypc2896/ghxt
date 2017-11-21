package com.cxstock.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Gys entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Gwxx implements java.io.Serializable {

	// Fields
	  private String dwbh00; //单位编号
//	  private String dwmc00; //单位名称
//	  private String dhua00; //单位电话
//	  private String dizhi0; //地址
//	  private String yzbm00; //邮政编码
//	  private String lxr000; //联系人
	  private String bz0000;//公文备注
	  private Integer lbid; //机构id
	  private String lbname;  //机构名

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
	  private String gw015;//收发标志0收文1发文
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	public String getDwbh00() {
		return dwbh00;
	}
	
	/**
	public String getDwmc00() {
		return dwmc00;
	}
	public String getDhua00() {
		return dhua00;
	}
	public String getDizhi0() {
		return dizhi0;
	}
	public String getYzbm00() {
		return yzbm00;
	}
	public String getLxr000() {
		return lxr000;
	}
	
	*/
	public String getBz0000() {
		return bz0000;
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

	public Integer getLbid() {
		return this.lbid;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public String getLbname() {
		return this.lbname;
	}

	public void setLbname(String lbname) {
		this.lbname = lbname;
	}
	
	
	
	   public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getGw001() {
			return gw001;
		}

		public void setGw001(String gw001) {
			this.gw001 = gw001;
		}

		public String getGw002() {
			return gw002;
		}

		public void setGw002(String gw002) {
			this.gw002 = gw002;
		}

		public String getGw003() {
			return gw003;
		}

		public void setGw003(String gw003) {
			this.gw003 = gw003;
		}

		public String getGw004() {
			return gw004;
		}

		public void setGw004(String gw004) {
			this.gw004 = gw004;
		}

		public String getGw005() {
			return gw005;
		}

		public void setGw005(String gw005) {
			this.gw005 = gw005;
		}

		public Date getGw006() {
			return gw006;
		}

		public void setGw006(Date gw006) {
			this.gw006 = gw006;
		}

		public String getGw007() {
			return gw007;
		}

		public void setGw007(String gw007) {
			this.gw007 = gw007;
		}

		public String getGw008() {
			return gw008;
		}

		public void setGw008(String gw008) {
			this.gw008 = gw008;
		}

		public String getGw009() {
			return gw009;
		}

		public void setGw009(String gw009) {
			this.gw009 = gw009;
		}

		public String getGw010() {
			return gw010;
		}

		public void setGw010(String gw010) {
			this.gw010 = gw010;
		}

		public Date getGw011() {
			return gw011;
		}

		public void setGw011(Date gw011) {
			this.gw011 = gw011;
		}

		public Date getGw012() {
			return gw012;
		}

		public void setGw012(Date gw012) {
			this.gw012 = gw012;
		}

		public String getGw013() {
			return gw013;
		}

		public void setGw013(String gw013) {
			this.gw013 = gw013;
		}

		public String getGw014() {
			return gw014;
		}

		public void setGw014(String gw014) {
			this.gw014 = gw014;
		}
		public String getGw015() {
			return gw015;
		}

		public void setGw015(String gw015) {
			this.gw015 = gw015;
		}	
}