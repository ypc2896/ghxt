package com.cxstock.action.dagl;
import com.cxstock.action.BaseAction;
import com.cxstock.biz.dagl.DwxxBiz;
import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.biz.ziliao.GysBiz;
import com.cxstock.biz.ziliao.dto.GysDTO;
import com.cxstock.utils.pubutil.Page;
/*
 * 单位信息管理
 */
@SuppressWarnings("serial")
public class DwxxAction extends BaseAction  {
	  private String dwbh00; //单位编号
//	  private String dwmc00; //单位名称
//	  private String dhua00; //单位电话
//	  private String dizhi0; //地址
//	  private String yzbm00; //邮政编码
//	  private String lxr000; //联系人
//	  private String bz0000;//备注

	  
	  private String aab001;	//	单位编号      
	  private String aab002;	//	上级单位编号  
	  private String aab003;	//	单位名称      
	  private String aab004;	//是否参保        
	  private String aab005;	//	负责人        
	  private String aab006;	//	负责人电话    
	  private String aab007;	//	单位地址      
	  private String aab008;	//单位类型        
	  private String aab009;	//是否登记        
	  private String aab010;	//	备注          
	  private String aab011;	//	主席          
	  private String aab012;	//	主席电话      

	  
	  
	  
	  
	  
	  
	  
	  private DwxxBiz dwxxBiz;//-------------接口类
 
	  
	  
		/** 
		 * 类别树
		 */
		public String findDwlbTree() {
			try {
				this.outTreeJsonList(dwxxBiz.findDwlbTree());
			} catch (Exception e) {
				e.printStackTrace();
				this.outError();
			}
			return null;
		}		  
	  
	  /** 
 * 分页查询单位信息列表 
 */
	  
	  
	/**indPageDwxx
	 * com.cxstock.action.dagl.DwxxAction -------3 
	 */
	  
	  
public String findPageDwxx() {
	try {
		Page page = new Page();
		page.setStart(this.getStart());
		page.setLimit(this.getLimit());
		
		
		/**3
		 * 4 findpagedwxx(Dwxxbizlmpl.java)-----
		 */
		
		dwxxBiz.findPageDwxx(aab001, aab003, page);
		this.outPageString(page);
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}	  
/**
 * 保存/修改单位信息
 */
public String saveOrUpdateDwxx() {
	try {
		DwxxDTO dto = new DwxxDTO(dwbh00,aab001,aab002,aab003,aab004,aab005,aab006,aab007,aab008,aab009,aab010,aab011,aab012);
		dwxxBiz.saveOrUpdateDwxx(dto);
		if(dwbh00.equals("")||dwbh00.equals(null)){
			this.outString("{success:true,message:'新增成功!'}");
		}else{
			this.outString("{success:true,message:'修改成功!'}");
		}
	} catch (Exception e) {
		 e.printStackTrace();
		 this.outError();
	}
	return null;
} 
/**
 * 删除单位
 */
public String deleteDwxx() {
	try {
		dwxxBiz.deleteDwxx(dwbh00);
		this.outString("{success:true}");
	} catch (Exception e) {
		e.printStackTrace();
		this.outError();
	}
	return null;
}
	public void setDwbh00(String dwbh00) {
		this.dwbh00 = dwbh00;
	}
//	public void setDwmc00(String dwmc00) {
//		this.dwmc00 = dwmc00;
//	}
//	public void setDhua00(String dhua00) {
//		this.dhua00 = dhua00;
//	}
//	public void setDizhi0(String dizhi0) {
//		this.dizhi0 = dizhi0;
//	}
//	public void setYzbm00(String yzbm00) {
//		this.yzbm00 = yzbm00;
//	}
//	public void setLxr000(String lxr000) {
//		this.lxr000 = lxr000;
//	}
//	public void setBz0000(String bz0000) {
//		this.bz0000 = bz0000;
//	}
	
	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}	
	
	public void setAab002(String aab002) {
		this.aab002 = aab002;
	}	
	
	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}	
	
	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}	
	
	public void setAab005(String aab005) {
		this.aab005 = aab005;
	}	
	
	public void setAab006(String aab006) {
		this.aab006 = aab006;
	}
	public void setAab007(String aab007) {
		this.aab007 = aab007;
	}	
	
	public void setAab008(String aab008) {
		this.aab008 = aab008;
	}	
	
	public void setAab009(String aab009) {
		this.aab009 = aab009;
	}
	public void setAab010(String aab010) {
		this.aab010 = aab010;
	}	
	
	public void setAab011(String aab011) {
		this.aab011 = aab011;
	}	
	
	public void setAab012(String aab012) {
		this.aab012 = aab012;
	}	
	
	
	
	
	
	
	public void setDwxxBiz(DwxxBiz dwxxBiz) {
		this.dwxxBiz = dwxxBiz;
	}

	 
	

}

