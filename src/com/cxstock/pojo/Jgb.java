package com.cxstock.pojo;

/**
 * <p>
 * Title: JGB
 * </p>
 * <p>
 * Description:机构表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:机构信息表* </p>
 * @hibernate.class table="JGB"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class Jgb implements java.io.Serializable {
 
      private  java.lang.String jgid; //机构ID
    private  java.lang.String jgmc; //机构名称
    private  java.lang.String pid; //父ID
  	
	  
       /**
   * 机构ID
   * @hibernate.property column="jgid"
   * @return jgid
   */
  public  java.lang.String getJgid() {
    return this.jgid; 
  }
	
    /**
   * 机构ID
   * @hibernate.property column="jgid"
   * @return jgid
   */
  public void setJgid(java.lang.String jgid) {
    this.jgid=jgid; 
  }
       /**
   * 机构名称
   * @hibernate.property column="jgmc"
   * @return jgmc
   */
  public  java.lang.String getJgmc() {
    return this.jgmc; 
  }
	
    /**
   * 机构名称
   * @hibernate.property column="jgmc"
   * @return jgmc
   */
  public void setJgmc(java.lang.String jgmc) {
    this.jgmc=jgmc; 
  }
       /**
   * 父ID
   * @hibernate.property column="pid"
   * @return pid
   */
  public  java.lang.String getPid() {
    return this.pid; 
  }
	
    /**
   * 父ID
   * @hibernate.property column="pid"
   * @return pid
   */
  public void setPid(java.lang.String pid) {
    this.pid=pid; 
  }
  	
    }
