package com.cxstock.pojo;

import java.util.Date;

/**
 * VusermenuId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Vgwyc implements java.io.Serializable {

	// Fields
	  private String dwbh00; //单位编号
	  private String bz0000;//公文备注
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
	  
	  
	  private String yc001;//接收人所在部门
	  private String yc003;//接收人所在部门
	  private String yc004;//公文接收者的用户名
	  private String yc005;//是否看过公文 0:未阅，1已阅，2正办，3办结
	  private Date yc006;//确认时间，阅读时间
	  private String yc007;//本人确认意见
	  private String yc008;//转发人
	  private String yc009;//转发人部门
	  private Date yc010;//转发日期
	  private Integer yc011;//是否提醒标志0为不提醒，1为提醒
	  private String yc012;//转发人编号
	  private String yc013;//收藏标志	
	  private String username;//收藏标志	
	  private String baiwei;//收藏标志	
	
	
	


	// Constructors

	/** default constructor */
	public Vgwyc() {
	}

	/** minimal constructor */
	public Vgwyc(String dwbh00, 
			 	 String bz0000, 
			 	 String gw001,
			     String gw002,
				 String gw003,//公文标题
				 String gw004,//公文文号
				 String gw005,//发文单位
				 Date gw006,//发文日期
				 String gw007,//文件等级
				 String gw008,//文件密级
				 String gw009,//文件种类通知请示
				 String gw010,//文件内容简述

				 String gw013,//文件附件
				 String gw014,//收发标志0收文1发文	


				 String yc005//是否看过公文 0:未阅，1已阅，2正办，3办结
		     
				) {

		  this.dwbh00=dwbh00; //单位编号
		  this.bz0000=bz0000;//公文备注
		  this.gw001=gw001;//公文编号
		  this.gw002=gw002;//传阅标志
		  this.gw003=gw003;//公文标题
		  this.gw004=gw004;//公文文号
		  this.gw005=gw005;//发文单位
		  this.gw006=gw006;//发文日期
		  this.gw007=gw007;//文件等级
		  this.gw008=gw008;//文件密级
		  this.gw009=gw009;//文件种类通知请示
		  this.gw010=gw010;//文件内容简述

		  this.gw013=gw013;//文件附件
		  this.gw014=gw014;//收发标志0收文1发文	

		  this.yc005=yc005;//是否看过公文 0:未阅，1已阅，2正办，3办结


	}

	/** full constructor */
	public Vgwyc(String dwbh00, 
		 	 String bz0000, 
		 	 String gw001,
		     String gw002,
			 String gw003,//公文标题
			 String gw004,//公文文号
			 String gw005,//发文单位
			 Date gw006,//发文日期
			 String gw007,//文件等级
			 String gw008,//文件密级
			 String gw009,//文件种类通知请示
			 String gw010,//文件内容简述

			 String gw013,//文件附件
			 String gw014,//收发标志0收文1发文	
			 String yc001,//接收人所在部门
			 String yc003,//接收人所在部门
			 String yc004,//公文接收者的用户名
			 String yc005,//是否看过公文 0:未阅，1已阅，2正办，3办结
			 Date yc006,//确认时间，阅读时间
			 String yc007,//本人确认意见
			 String yc008,//转发人
			 String yc009,//转发人部门
			 Date yc010,//转发日期
			 Integer yc011,//是否提醒标志0为不提醒，1为提醒
			 String yc012,//转发人编号
			 String yc013,//收藏标志
			 String username,
			 String baiwei//收藏标志			     
			) {

	  this.dwbh00=dwbh00; //单位编号
	  this.bz0000=bz0000;//公文备注
	  this.gw001=gw001;//公文编号
	  this.gw002=gw002;//传阅标志
	  this.gw003=gw003;//公文标题
	  this.gw004=gw004;//公文文号
	  this.gw005=gw005;//发文单位
	  this.gw006=gw006;//发文日期
	  this.gw007=gw007;//文件等级
	  this.gw008=gw008;//文件密级
	  this.gw009=gw009;//文件种类通知请示
	  this.gw010=gw010;//文件内容简述

	  this.gw013=gw013;//文件附件
	  this.gw014=gw014;//收发标志0收文1发文	
             
             
	  this.yc001=yc001;//接收人所在部门             
	  this.yc003=yc003;//接收人所在部门
	  this.yc004=yc004;//公文接收者的用户名
	  this.yc005=yc005;//是否看过公文 0:未阅，1已阅，2正办，3办结
	  this.yc006=yc006;//确认时间，阅读时间
	  this.yc007=yc007;//本人确认意见
	  this.yc008=yc008;//转发人
	  this.yc009=yc009;//转发人部门
	  this.yc010=yc010;//转发日期
	  this.yc011=yc011;//是否提醒标志0为不提醒，1为提醒
	  this.yc012=yc012;//转发人编号
	  this.yc013=yc013;//收藏标志	
	  this.username=username;
	  this.baiwei=baiwei;//收藏标志	
}
	
	
	
	
	
	
	// Property accessors
	public String getDwbh00() {
		return dwbh00;
	}

	public String getBz0000() {
		return bz0000;
	}

	public String getGw001() {
		return gw001;
	}

	public String getGw002() {
		return gw002;
	}

	public String getGw003() {
		return gw003;
	}

	public String getGw004() {
		return gw004;
	}

	public String getGw005() {
		return gw005;
	}

	public Date getGw006() {
		return gw006;
	}

	public String getGw007() {
		return gw007;
	}

	public String getGw008() {
		return gw008;
	}

	public String getGw009() {
		return gw009;
	}

	public String getGw010() {
		return gw010;
	}



	public String getGw013() {
		return gw013;
	}

	public String getGw014() {
		return gw014;
	}

	public String getYc001() {
		return yc001;
	}
	public String getYc003() {
		return yc003;
	}
	public String getYc004() {
		return yc004;
	}

	public String getYc005() {
		return yc005;
	}

	public Date getYc006() {
		return yc006;
	}

	public String getYc007() {
		return yc007;
	}

	public String getYc008() {
		return yc008;
	}

	public String getYc009() {
		return yc009;
	}

	public Date getYc010() {
		return yc010;
	}

	public Integer getYc011() {
		return yc011;
	}

	public String getYc012() {
		return yc012;
	}

	public String getYc013() {
		return yc013;
	}

	public String getUsername() {
		return username;
	}

	public String getBaiwei() {
		return baiwei;
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

	public void setBaiwei(String baiwei) {
		this.baiwei = baiwei;
	}



}