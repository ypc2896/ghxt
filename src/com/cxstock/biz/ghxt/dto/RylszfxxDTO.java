package com.cxstock.biz.ghxt.dto;

import java.math.BigDecimal;
import java.util.Date;   //有日期 类型时，需要引入这句代码。
public class RylszfxxDTO {



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
  
  		  
	  
	  
	  
	  
	  
	  
	  public RylszfxxDTO() {
			super();
		}

		public RylszfxxDTO(String zf001,String zf002,String zf003,String zf004,String zf005,BigDecimal zf006,
				BigDecimal zf007,BigDecimal zf008,BigDecimal zf009,BigDecimal zf010,BigDecimal zf011,String zf012,
				BigDecimal zf013,BigDecimal zf014,String zf015,String zf016,String zf017,String zf018,String zf019,
				String zf020,String zf021,String zf022,String zf023,String zf024,String zf025,String zf026,String zf027,
				String zf028,String zf029,BigDecimal zf030,BigDecimal zf031,String zf032) {
 		super();

   
 		this.zf001=zf001;
 		this.zf002=zf002;
 		this.zf003=zf003;
 		this.zf004=zf004;
 		this.zf005=zf005;
 		this.zf006=zf006;
 		this.zf007=zf007;
 		this.zf008=zf008;
 		this.zf009=zf009;
 		this.zf010=zf010;
 		this.zf011=zf011;
 		this.zf012=zf012;
 		this.zf013=zf013;
 		this.zf014=zf014;
 		this.zf015=zf015;
 		this.zf016=zf016;
 		this.zf017=zf017;
 		this.zf018=zf018;
 		this.zf019=zf019;
 		this.zf020=zf020;
 		this.zf021=zf021;
 		this.zf022=zf022;
 		this.zf023=zf023;
 		this.zf024=zf024;
 		this.zf025=zf025;
 		this.zf026=zf026;
 		this.zf027=zf027;
 		this.zf028=zf028;
 		this.zf029=zf029;
 		this.zf030=zf030;
 		this.zf031=zf031;
 		this.zf032=zf032;


         

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
	  	
		
		
		public java.lang.String getZf001() {
			return zf001;
		}

		public java.lang.String getZf002() {
			return zf002;
		}

		public java.lang.String getZf003() {
			return zf003;
		}

		public java.lang.String getZf004() {
			return zf004;
		}

		public java.lang.String getZf005() {
			return zf005;
		}

		public java.math.BigDecimal getZf006() {
			return zf006;
		}

		public java.math.BigDecimal getZf007() {
			return zf007;
		}

		public java.math.BigDecimal getZf008() {
			return zf008;
		}

		public java.math.BigDecimal getZf009() {
			return zf009;
		}

		public java.math.BigDecimal getZf010() {
			return zf010;
		}

		public java.math.BigDecimal getZf011() {
			return zf011;
		}

		public java.lang.String getZf012() {
			return zf012;
		}

		public java.math.BigDecimal getZf013() {
			return zf013;
		}

		public java.math.BigDecimal getZf014() {
			return zf014;
		}

		public java.lang.String getZf015() {
			return zf015;
		}

		public java.lang.String getZf016() {
			return zf016;
		}

		public java.lang.String getZf017() {
			return zf017;
		}

		public java.lang.String getZf018() {
			return zf018;
		}

		public java.lang.String getZf019() {
			return zf019;
		}

		public java.lang.String getZf020() {
			return zf020;
		}

		public java.lang.String getZf021() {
			return zf021;
		}

		public java.lang.String getZf022() {
			return zf022;
		}

		public java.lang.String getZf023() {
			return zf023;
		}

		public java.lang.String getZf024() {
			return zf024;
		}

		public java.lang.String getZf025() {
			return zf025;
		}

		public java.lang.String getZf026() {
			return zf026;
		}

		public java.lang.String getZf027() {
			return zf027;
		}

		public java.lang.String getZf028() {
			return zf028;
		}

		public java.lang.String getZf029() {
			return zf029;
		}

		public java.math.BigDecimal getZf030() {
			return zf030;
		}

		public java.math.BigDecimal getZf031() {
			return zf031;
		}

		public java.lang.String getZf032() {
			return zf032;
		}
	
	
}
