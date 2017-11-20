package com.ghxt.pojo;

/**
 * <p>
 * Title: GRJFB
 * </p>
 * <p>
 * Description:个人缴费登记明细表
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:鏄撹仈浼椾俊鎭妧鏈偂浠芥湁闄愬叕鍙� * </p>
 * @hibernate.class table="GRJFB"
 * @author wph 
 * @email  wangpeihua001@163.com
 * @version 1.0
 */
public class Grjfb implements java.io.Serializable {
 
    private  java.lang.String aae001; //缴费ID
    private  java.lang.String aac001; //个人编号
    private  java.lang.String aab001; //单位编号
    private  java.lang.String aae005; //缴费期别
    private  java.lang.String aae061; //应收单号单位汇总表ID
    private  java.lang.String aae007; //是否实收是否实收0未实收，1财务已实收
    private  java.lang.String aae008; //经办人
    private  java.lang.String aae009; //经办日期
    private  java.lang.String aab003; //单位名称

    private java.lang.String aac009; //医保卡号                                                                                 
    private java.lang.String aac002; //身份号码                                                                                 
    private java.lang.String aac003; //姓名                                                                                     
    private java.lang.String aac004; //性别                                                                                     
    private java.lang.String aae010; //报名表序号                                                                               
    private java.lang.String aae011; //报名备注                                                                                 
    private java.lang.String aae012; //填表人                                                                                   
    private java.lang.String aae013; //填表日期                                                                                 
    private java.lang.String aae014; //报名表盖章办事处名称
	  
       /**
   * 缴费ID
   * @hibernate.property column="aae001"
   * @return aae001
   */
  public  java.lang.String getAae001() {
    return this.aae001; 
  }
	
    /**
   * 缴费ID
   * @hibernate.property column="aae001"
   * @return aae001
   */
  public void setAae001(java.lang.String aae001) {
    this.aae001=aae001; 
  }
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
   * 应收单号
   * @hibernate.property column="aae061"
   * @return aae061
   */
  public  java.lang.String getAae061() {
    return this.aae061; 
  }
	
    /**
   * 应收单号
   * @hibernate.property column="aae061"
   * @return aae061
   */
  public void setAae061(java.lang.String aae061) {
    this.aae061=aae061; 
  }
       /**
   * 是否实收
   * @hibernate.property column="aae007"
   * @return aae007
   */
  public  java.lang.String getAae007() {
    return this.aae007; 
  }
	
    /**
   * 是否实收
   * @hibernate.property column="aae007"
   * @return aae007
   */
  public void setAae007(java.lang.String aae007) {
    this.aae007=aae007; 
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
   * 单位名称
   * @hibernate.property column="aab003"
   * @return aab003
   */
  public  java.lang.String getAab003() {
    return this.aab003; 
  }
	
    /**
   * 单位名称
   * @hibernate.property column="aab003"
   * @return aab003
   */
  public void setAab003(java.lang.String aab003) {
    this.aab003=aab003; 
  }
  	
  public java.lang.String getAac009() {
		return aac009;
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

	public java.lang.String getAae010() {
		return aae010;
	}

	public java.lang.String getAae011() {
		return aae011;
	}

	public java.lang.String getAae012() {
		return aae012;
	}

	public java.lang.String getAae013() {
		return aae013;
	}

	public java.lang.String getAae014() {
		return aae014;
	}  
  
  
	public void setAac009(java.lang.String aac009) {
		this.aac009 = aac009;
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

	public void setAae010(java.lang.String aae010) {
		this.aae010 = aae010;
	}

	public void setAae011(java.lang.String aae011) {
		this.aae011 = aae011;
	}

	public void setAae012(java.lang.String aae012) {
		this.aae012 = aae012;
	}

	public void setAae013(java.lang.String aae013) {
		this.aae013 = aae013;
	}

	public void setAae014(java.lang.String aae014) {
		this.aae014 = aae014;
	}
  
  
  
  
  
    }
