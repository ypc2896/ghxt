package com.cxstock.biz.ghxt.dto;

import java.util.Date;   //有日期 类型时，需要引入这句代码。
public class RyxxDTO {



	private  java.lang.String aac001; //个人编号
    private  java.lang.String aac002; //身份号码
    private  java.lang.String aac003; //姓名
    private  java.lang.String aac004; //性别
    private  java.lang.String aac006; //出生日期
    private  java.lang.String aab001; //单位编号
    private  java.lang.String aac005; //电话
    private  java.lang.String aac007; //住址
    private  java.lang.String aac008; //邮政编码
    private  java.lang.String aac009; //医保卡号
    private  java.lang.String aac010; //是否农民工
    private  java.lang.String aac011; //是否参医保
	  
	  
	  
	  
	  
	  
	  
	  public RyxxDTO() {
			super();
		}

		public RyxxDTO(String aac001,String aac002,String aac003,
				String aac004,String aac005,String aab001,String aac006,String aac007,String aac008,String aac009,
				String aac010,String aac011) {
			super();

   
			this.aac001 = aac001;//公文编号          
			this.aac002 = aac002;//传阅标志          
			this.aac003 = aac003;//公文标题          
			this.aac004 = aac004;//公文文号          
			this.aac005 = aac005;//发文单位   
			this.aab001 = aab001;//发文单位
			this.aac006 = aac006;//发文日期          
			this.aac007 = aac007;//文件等级          
			this.aac008 = aac008;//文件密级          
			this.aac009 = aac009;//文件种类通知请示  
			this.aac010 = aac010;//文件内容简述      
			this.aac011 = aac011;//录入日期          

		}
		
		
	    public java.lang.String getAac001() {
			return aac001;
		}

		public java.lang.String getAac002() {
			return aac002;
		}

		public java.lang.String getAac003() {
			return aac003;
		}

		public java.lang.String getAac004() {
			return aac004;
		}

		public java.lang.String getAac006() {
			return aac006;
		}

		public java.lang.String getAab001() {
			return aab001;
		}

		public java.lang.String getAac005() {
			return aac005;
		}

		public java.lang.String getAac007() {
			return aac007;
		}

		public java.lang.String getAac008() {
			return aac008;
		}

		public java.lang.String getAac009() {
			return aac009;
		}

		public java.lang.String getAac010() {
			return aac010;
		}

		public java.lang.String getAac011() {
			return aac011;
		}

		public void setAac001(java.lang.String aac001) {
			this.aac001 = aac001;
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

		public void setAac006(java.lang.String aac006) {
			this.aac006 = aac006;
		}

		public void setAab001(java.lang.String aab001) {
			this.aab001 = aab001;
		}

		public void setAac005(java.lang.String aac005) {
			this.aac005 = aac005;
		}

		public void setAac007(java.lang.String aac007) {
			this.aac007 = aac007;
		}

		public void setAac008(java.lang.String aac008) {
			this.aac008 = aac008;
		}

		public void setAac009(java.lang.String aac009) {
			this.aac009 = aac009;
		}

		public void setAac010(java.lang.String aac010) {
			this.aac010 = aac010;
		}

		public void setAac011(java.lang.String aac011) {
			this.aac011 = aac011;
		} 
	
	
	
	
}
