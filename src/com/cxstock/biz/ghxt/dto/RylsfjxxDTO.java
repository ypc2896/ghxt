package com.cxstock.biz.ghxt.dto;

import java.math.BigDecimal;
import java.util.Date;   //有日期 类型时，需要引入这句代码。
public class RylsfjxxDTO {



  

	private  java.lang.String sae201; //单档ID
    private  java.lang.String sae041; //理算业务ID
    private  java.lang.String sae202; //档案类型
    private  java.lang.String sae203; //档案文件类型
    private  java.lang.String sae204; //文件名
    private  java.lang.String sae205; //文件地址
    private  java.lang.String sae036; //上传人
    private  java.lang.String sae011; //上传日期
    private  java.lang.String sae012; //标志0未绑定，已绑定  		  
	  
	  
	  
	  
	  
	  
	  public RylsfjxxDTO() {
			super();
		}

		public RylsfjxxDTO(String sae201,String sae041,String sae202,String sae203,String sae204,
				String sae205,String sae036,String sae011,String sae012) {
 		super();

   
 	    this.sae201=sae201;  
 		this.sae041=sae041;  
 		this.sae202=sae202;  
 		this.sae203=sae203;  
 		this.sae204=sae204;  
 		this.sae205=sae205;  
 		this.sae036=sae036;  
 		this.sae011=sae011;  
 		this.sae012=sae012;  


         

		}
		
		
		  public void setSae201(java.lang.String sae201) {
				this.sae201 = sae201;
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

			public void setSae011(java.lang.String sae011) {
				this.sae011 = sae011;
			}

			public void setSae012(java.lang.String sae012) {
				this.sae012 = sae012;
			}

			public java.lang.String getSae201() {
				return sae201;
			}

			public java.lang.String getSae202() {
				return sae202;
			}

			public java.lang.String getSae203() {
				return sae203;
			}

			public java.lang.String getSae204() {
				return sae204;
			}

			public java.lang.String getSae205() {
				return sae205;
			}

			public java.lang.String getSae011() {
				return sae011;
			}

			public java.lang.String getSae012() {
				return sae012;
			}

			
			
			
			
			public void setSae041(java.lang.String sae041) {
				this.sae041 = sae041;
			}

			public java.lang.String getSae041() {
				return sae041;
			}
			
			
			
			public void setSae036(java.lang.String sae036) {
				this.sae036 = sae036;
			}

			public java.lang.String getSae036() {
				return sae036;
			}	
			
			
			
			
			
	
}
