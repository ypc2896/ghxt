package com.cxstock.biz.power.dto;

/**
 * <p>
 * Title: JGB000
 * </p>
 * <p>
 * Description:机构表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:机构信息表* </p>
 * @hibernate.class table="JGB000"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class JgbDTO implements java.io.Serializable {
 
    private  java.lang.String jgid; //机构ID
    private  java.lang.String jgmc; //机构名称
    private  java.lang.String pid; //父ID
  	
    public JgbDTO() {
		super();
	}

	public JgbDTO(String jgid, String jgmc, String pid) {
		super();
		this.jgid = jgid;
		this.jgmc = jgmc;
		this.pid = pid; 
	}  
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
