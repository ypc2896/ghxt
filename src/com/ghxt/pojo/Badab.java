package com.ghxt.pojo;

/**
 * <p>
 * Title: BADAB
 * </p>
 * <p>
 * Description:病案档案表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:鏄撹仈浼椾俊鎭妧鏈偂浠芥湁闄愬叕鍙� * </p>
 * @hibernate.class table="BADAB"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class Badab implements java.io.Serializable {
 
    private  java.lang.String sae201; //单档ID
    private  java.lang.String sae041; //理算业务ID
    private  java.lang.String sae202; //档案类型
    private  java.lang.String sae203; //档案文件类型
    private  java.lang.String sae204; //文件名
    private  java.lang.String sae205; //文件地址
    private  java.lang.String sae036; //上传人
    private  java.lang.String sae011; //上传日期
    private  java.lang.String sae012; //标志0未绑定，已绑定
       /**
   * 单档ID
   * @hibernate.property column="sae201"
   * @return sae201
   */
  public  java.lang.String getSae201() {
    return this.sae201; 
  }
	
    /**
   * 单档ID
   * @hibernate.property column="sae201"
   * @return sae201
   */
  public void setSae201(java.lang.String sae201) {
    this.sae201=sae201; 
  }
       /**
   * 理算业务ID
   * @hibernate.property column="sae041"
   * @return sae041
   */
  public  java.lang.String getSae041() {
    return this.sae041; 
  }
	
    /**
   * 理算业务ID
   * @hibernate.property column="sae041"
   * @return sae041
   */
  public void setSae041(java.lang.String sae041) {
    this.sae041=sae041; 
  }
       /**
   * 档案类型
   * @hibernate.property column="sae202"
   * @return sae202
   */
  public  java.lang.String getSae202() {
    return this.sae202; 
  }
	
    /**
   * 档案类型
   * @hibernate.property column="sae202"
   * @return sae202
   */
  public void setSae202(java.lang.String sae202) {
    this.sae202=sae202; 
  }
       /**
   * 档案文件类型
   * @hibernate.property column="sae203"
   * @return sae203
   */
  public  java.lang.String getSae203() {
    return this.sae203; 
  }
	
    /**
   * 档案文件类型
   * @hibernate.property column="sae203"
   * @return sae203
   */
  public void setSae203(java.lang.String sae203) {
    this.sae203=sae203; 
  }
       /**
   * 文件名
   * @hibernate.property column="sae204"
   * @return sae204
   */
  public  java.lang.String getSae204() {
    return this.sae204; 
  }
	
    /**
   * 文件名
   * @hibernate.property column="sae204"
   * @return sae204
   */
  public void setSae204(java.lang.String sae204) {
    this.sae204=sae204; 
  }
       /**
   * 文件地址
   * @hibernate.property column="sae205"
   * @return sae205
   */
  public  java.lang.String getSae205() {
    return this.sae205; 
  }
	
    /**
   * 文件地址
   * @hibernate.property column="sae205"
   * @return sae205
   */
  public void setSae205(java.lang.String sae205) {
    this.sae205=sae205; 
  }
       /**
   * 上传人
   * @hibernate.property column="sae036"
   * @return sae036
   */
  public  java.lang.String getSae036() {
    return this.sae036; 
  }
	
    /**
   * 上传人
   * @hibernate.property column="sae036"
   * @return sae036
   */
  public void setSae036(java.lang.String sae036) {
    this.sae036=sae036; 
  }
       /**
   * 上传日期
   * @hibernate.property column="sae011"
   * @return sae011
   */
  public  java.lang.String getSae011() {
    return this.sae011; 
  }
  public  java.lang.String getSae012() {
	    return this.sae012; 
	  }	
    /**
   * 上传日期
   * @hibernate.property column="sae011"
   * @return sae011
   */
  public void setSae011(java.lang.String sae011) {
    this.sae011=sae011; 
  }
  public void setSae012(java.lang.String sae012) {
	    this.sae012=sae012; 
	  }
	  	 	
    }
