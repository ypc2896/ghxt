package com.cxstock.biz.dagl.dto;

import java.util.Date;   //有日期 类型时，需要引入这句代码。
public class GwycDTO {

 
	  
	  private String yc001;//公文传阅ID
	  private String yc002;//公文的id
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
 
   
	  
	  
	  
	  
	  public GwycDTO() {
			super();
		}

		public GwycDTO(String yc001,String yc002,String yc003,
				String yc004,String yc005,Date yc006,String yc007,String yc008,String yc009,
				Date yc010,Integer yc011,String yc012,String yc013) {
			super();
			
			this.yc001 = yc001;//公文编号          
			this.yc002 = yc002;//传阅标志          
			this.yc003 = yc003;//公文标题          
			this.yc004 = yc004;//公文文号          
			this.yc005 = yc005;//发文单位          
			this.yc006 = yc006;//发文日期          
			this.yc007 = yc007;//文件等级          
			this.yc008 = yc008;//文件密级          
			this.yc009 = yc009;//文件种类通知请示  
			this.yc010 = yc010;//文件内容简述      
			this.yc011 = yc011;//录入日期          
			this.yc012 = yc012;//归档日期          
			this.yc013 = yc013;//文件附件          
			 
		}
		
		
		
 
		  
		public String getYc001() {
			return yc001;
		}
		public void setYc001(String yc001) {
			this.yc001 = yc001;
		}
		public String getYc002() {
			return yc002;
		}
		public void setYc002(String yc002) {
			this.yc002 = yc002;
		}
		public String getYc003() {
			return yc003;
		}
		public void setYc003(String yc003) {
			this.yc003 = yc003;
		}
		public String getYc004() {
			return yc004;
		}
		public void setYc004(String yc004) {
			this.yc004 = yc004;
		}
		public String getYc005() {
			return yc005;
		}
		public void setYc005(String yc005) {
			this.yc005 = yc005;
		}
		public Date getYc006() {
			return yc006;
		}
		public void setYc006(Date yc006) {
			this.yc006 = yc006;
		}
		public String getYc007() {
			return yc007;
		}
		public void setYc007(String yc007) {
			this.yc007 = yc007;
		}
		public String getYc008() {
			return yc008;
		}
		public void setYc008(String yc008) {
			this.yc008 = yc008;
		}
		public String getYc009() {
			return yc009;
		}
		public void setYc009(String yc009) {
			this.yc009 = yc009;
		}
		public Date getYc010() {
			return yc010;
		}
		public void setYc010(Date yc010) {
			this.yc010 = yc010;
		}
		public Integer getYc011() {
			return yc011;
		}
		public void setYc011(Integer yc011) {
			this.yc011 = yc011;
		}
		public String getYc012() {
			return yc012;
		}
		public void setYc012(String yc012) {
			this.yc012 = yc012;
		}
		public String getYc013() {
			return yc013;
		}
		public void setYc013(String yc013) {
			this.yc013 = yc013;
		}
	 
	
	
	
}
