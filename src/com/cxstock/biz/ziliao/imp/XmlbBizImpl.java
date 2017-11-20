package com.cxstock.biz.ziliao.imp;

import java.util.ArrayList;
import java.util.List;

import com.cxstock.biz.ziliao.XmlbBiz;
import com.cxstock.biz.ziliao.dto.XmlbDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Xmlb;
import com.cxstock.utils.pubutil.TreeNode;

@SuppressWarnings("unchecked")
public class XmlbBizImpl implements XmlbBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 保存/修改商品类别
	 */
	public Integer saveOrUpdateXmlb(XmlbDTO dto) {
		Xmlb xmlb = new Xmlb();
		if(dto.getLbid()!=null){
			xmlb = (Xmlb)baseDao.loadById(Xmlb.class, dto.getLbid());
		}else{
			xmlb.setPid(dto.getPid());
			xmlb.setLbid(Integer.parseInt(baseDao.getSequence("seq_gysid")));
		}
		xmlb.setLbname(dto.getLbname());
		baseDao.saveOrUpdate(xmlb);
		return xmlb.getLbid();
	}
	
	/*
	 * 删除商品类别
	 */
	public boolean deleteXmlb(Integer lbid) {
		int count = baseDao.countQuery("select count(*) from Xmxx where lbid = "+lbid);
		if(count>0){
			return false;
		}
		baseDao.deleteById(Xmlb.class, lbid);
		return true;
	}
	
	/*
	 * 商品类别树
	 */
	public List findXmlbTree() {
		List list = baseDao.listAll("Xmlb");
		return this.getTree(0, list);
	}
	
	/**  
	 * 通过递归生成tree结构  
	 * @param List childrenlist 商品类别集合
	 * @param Integer id 节点（父节点id）
	 */
	private List getTree(Integer id,List childrenlist) {
		List resultlist = new ArrayList();

		//当前级菜单集合
		List list = this.getChildrens(childrenlist, id);
		for (Object obj : list) {
			Xmlb xmlb = (Xmlb) obj;

			TreeNode treeNode = new TreeNode();
			treeNode.setId(xmlb.getLbid().toString());
			
			//-------------------------
			treeNode.setText(xmlb.getLbid().toString()+"_"+xmlb.getLbname());
			treeNode.setIconCls("menu-folder");
			//子菜单
			List children = this.getChildrens(childrenlist, xmlb.getLbid());
			if (children.size() > 0) {//有子类别集合
				treeNode.setLeaf(false);
				treeNode.setChildren(getTree(xmlb.getLbid(),childrenlist)); //递归调   
			} else {//该节点为叶子    
				treeNode.setLeaf(true);
			}
			resultlist.add(treeNode);
		}
		return resultlist;
	}

	/**
	 * 从funcs集合中找出父节点id为pid的类别集合
	 * @param List menus 类别集合
	 * @param Integer pid 父节点id
	 * return List
	 */
	private List getChildrens(List xmlbs, Integer pid) {
		List resultList = new ArrayList();
		Xmlb xmlb = null;
		for (Object obj : xmlbs) {
			xmlb = (Xmlb) obj;
			if (xmlb.getPid()!=null&&xmlb.getPid().equals(pid)) {//父节点id
				resultList.add(xmlb);
			}
		}
		return resultList;
	}

}
