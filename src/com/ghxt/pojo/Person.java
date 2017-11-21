package com.ghxt.pojo;

/**
 * <p>
 * Title: PERSON
 * </p>
 * <p>
 * Description:个人信息表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:鏄撹仈浼椾俊鎭妧鏈偂浠芥湁闄愬叕鍙� * </p>
 * @hibernate.class table="PERSON"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class Person implements java.io.Serializable {
 
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
  	
	  
       /**
   * 个人编号
   * @hibernate.property column="aac001"
   * @return aac001
   */
  public  java.lang.String getAac001() {
    return this.aac001; 
  }
	
    /**
   * 个人编号
   * @hibernate.property column="aac001"
   * @return aac001
   */
  public void setAac001(java.lang.String aac001) {
    this.aac001=aac001; 
  }
       /**
   * 身份号码
   * @hibernate.property column="aac002"
   * @return aac002
   */
  public  java.lang.String getAac002() {
    return this.aac002; 
  }
	
    /**
   * 身份号码
   * @hibernate.property column="aac002"
   * @return aac002
   */
  public void setAac002(java.lang.String aac002) {
    this.aac002=aac002; 
  }
       /**
   * 姓名
   * @hibernate.property column="aac003"
   * @return aac003
   */
  public  java.lang.String getAac003() {
    return this.aac003; 
  }
	
    /**
   * 姓名
   * @hibernate.property column="aac003"
   * @return aac003
   */
  public void setAac003(java.lang.String aac003) {
    this.aac003=aac003; 
  }
       /**
   * 性别
   * @hibernate.property column="aac004"
   * @return aac004
   */
  public  java.lang.String getAac004() {
    return this.aac004; 
  }
	
    /**
   * 性别
   * @hibernate.property column="aac004"
   * @return aac004
   */
  public void setAac004(java.lang.String aac004) {
    this.aac004=aac004; 
  }
       /**
   * 出生日期
   * @hibernate.property column="aac006"
   * @return aac006
   */
  public  java.lang.String getAac006() {
    return this.aac006; 
  }
	
    /**
   * 出生日期
   * @hibernate.property column="aac006"
   * @return aac006
   */
  public void setAac006(java.lang.String aac006) {
    this.aac006=aac006; 
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
   * 电话
   * @hibernate.property column="aac005"
   * @return aac005
   */
  public  java.lang.String getAac005() {
    return this.aac005; 
  }
	
    /**
   * 电话
   * @hibernate.property column="aac005"
   * @return aac005
   */
  public void setAac005(java.lang.String aac005) {
    this.aac005=aac005; 
  }
       /**
   * 住址
   * @hibernate.property column="aac007"
   * @return aac007
   */
  public  java.lang.String getAac007() {
    return this.aac007; 
  }
	
    /**
   * 住址
   * @hibernate.property column="aac007"
   * @return aac007
   */
  public void setAac007(java.lang.String aac007) {
    this.aac007=aac007; 
  }
       /**
   * 邮政编码
   * @hibernate.property column="aac008"
   * @return aac008
   */
  public  java.lang.String getAac008() {
    return this.aac008; 
  }
	
    /**
   * 邮政编码
   * @hibernate.property column="aac008"
   * @return aac008
   */
  public void setAac008(java.lang.String aac008) {
    this.aac008=aac008; 
  }
       /**
   * 医保卡号
   * @hibernate.property column="aac009"
   * @return aac009
   */
  public  java.lang.String getAac009() {
    return this.aac009; 
  }
	
    /**
   * 医保卡号
   * @hibernate.property column="aac009"
   * @return aac009
   */
  public void setAac009(java.lang.String aac009) {
    this.aac009=aac009; 
  }
       /**
   * 是否农民工
   * @hibernate.property column="aac010"
   * @return aac010
   */
  public  java.lang.String getAac010() {
    return this.aac010; 
  }
	
    /**
   * 是否农民工
   * @hibernate.property column="aac010"
   * @return aac010
   */
  public void setAac010(java.lang.String aac010) {
    this.aac010=aac010; 
  }
       /**
   * 是否参医保
   * @hibernate.property column="aac011"
   * @return aac011
   */
  public  java.lang.String getAac011() {
    return this.aac011; 
  }
	
    /**
   * 是否参医保
   * @hibernate.property column="aac011"
   * @return aac011
   */
  public void setAac011(java.lang.String aac011) {
    this.aac011=aac011; 
  }
  	
    }
