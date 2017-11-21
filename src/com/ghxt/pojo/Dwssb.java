package com.ghxt.pojo;

/**
 * <p>
 * Title: DWSSB
 * </p>
 * <p>
 * Description:单位实收表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:鏄撹仈浼椾俊鎭妧鏈偂浠芥湁闄愬叕鍙� * </p>
 * @hibernate.class table="DWSSB"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class Dwssb implements java.io.Serializable {
 
      private  java.lang.String aae062; //实收缴单号
    private  java.lang.String aab001; //单位编号
    private  java.lang.String aae005; //缴费期别
    private  java.math.BigDecimal aae006; //实收金额
    private  java.lang.String aae008; //经办人
    private  java.lang.String aae009; //经办日期
    private  java.lang.String aae071; //票据号
    private  java.lang.String aae072; //实收人数
  	
	  
       /**
   * 实收缴单号
   * @hibernate.property column="aae062"
   * @return aae062
   */
  public  java.lang.String getAae062() {
    return this.aae062; 
  }
	
    /**
   * 实收缴单号
   * @hibernate.property column="aae062"
   * @return aae062
   */
  public void setAae062(java.lang.String aae062) {
    this.aae062=aae062; 
  }
       /**
   * 单位编号
   * @hibernate.property column="aab001"
   * @return aab001
   */
  public  java.lang.String getAab001() {
    return this.aab001; 
  }
	
    /**
   * 单位编号
   * @hibernate.property column="aab001"
   * @return aab001
   */
  public void setAab001(java.lang.String aab001) {
    this.aab001=aab001; 
  }
       /**
   * 缴费期别
   * @hibernate.property column="aae005"
   * @return aae005
   */
  public  java.lang.String getAae005() {
    return this.aae005; 
  }
	
    /**
   * 缴费期别
   * @hibernate.property column="aae005"
   * @return aae005
   */
  public void setAae005(java.lang.String aae005) {
    this.aae005=aae005; 
  }
       /**
   * 实收金额
   * @hibernate.property column="aae006"
   * @return aae006
   */
  public  java.math.BigDecimal getAae006() {
    return this.aae006; 
  }
	
    /**
   * 实收金额
   * @hibernate.property column="aae006"
   * @return aae006
   */
  public void setAae006(java.math.BigDecimal aae006) {
    this.aae006=aae006; 
  }
       /**
   * 经办人
   * @hibernate.property column="aae008"
   * @return aae008
   */
  public  java.lang.String getAae008() {
    return this.aae008; 
  }
	
    /**
   * 经办人
   * @hibernate.property column="aae008"
   * @return aae008
   */
  public void setAae008(java.lang.String aae008) {
    this.aae008=aae008; 
  }
       /**
   * 经办日期
   * @hibernate.property column="aae009"
   * @return aae009
   */
  public  java.lang.String getAae009() {
    return this.aae009; 
  }
	
    /**
   * 经办日期
   * @hibernate.property column="aae009"
   * @return aae009
   */
  public void setAae009(java.lang.String aae009) {
    this.aae009=aae009; 
  }
       /**
   * 票据号
   * @hibernate.property column="aae071"
   * @return aae071
   */
  public  java.lang.String getAae071() {
    return this.aae071; 
  }
	
    /**
   * 票据号
   * @hibernate.property column="aae071"
   * @return aae071
   */
  public void setAae071(java.lang.String aae071) {
    this.aae071=aae071; 
  }
       /**
   * 实收人数
   * @hibernate.property column="aae072"
   * @return aae072
   */
  public  java.lang.String getAae072() {
    return this.aae072; 
  }
	
    /**
   * 实收人数
   * @hibernate.property column="aae072"
   * @return aae072
   */
  public void setAae072(java.lang.String aae072) {
    this.aae072=aae072; 
  }
  	
    }
