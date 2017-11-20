package com.cxstock.biz.power.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.dagl.DwxxBiz;
import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.biz.power.JgbBiz;
import com.cxstock.biz.power.dto.JgbDTO;
import com.cxstock.biz.ziliao.dto.SplbDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Dwxx;
import com.cxstock.pojo.Jgb;
import com.cxstock.pojo.Splb;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.pubutil.TreeNode;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class JgbBizImpl implements JgbBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	/*
	 * 分页查询机构列表
	 */
	public void findPageJgb(String pid,Page page) {
		if(pid==null){
			pid = "";
		}
		String hql="from Jgb where pid = '"+pid+"'";
		List list = baseDao.findByHql(hql,page.getStart(), page.getLimit());
		hql="select count(*)from Jgb where pid = '"+pid+"'";
		int total = baseDao.countQuery(hql);
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改机构
	 */
	 public void saveOrUpdateJgb(JgbDTO dto) {
		Jgb jgb = new Jgb();
		if(dto.getJgid()!=null&&!dto.getJgid().equals("")){
			jgb = (Jgb)baseDao.loadById(Jgb.class, dto.getJgid());
		}else{
			jgb.setPid(dto.getPid());
			jgb.setJgid(baseDao.getSequence("seq_jgid"));
			dto.setJgid(jgb.getJgid());
		}
		jgb.setJgmc(dto.getJgmc());
		baseDao.saveOrUpdate(jgb);
	}
	
	/*
	 * 删除机构
	 */
	public void deleteJgb(String jgid) {
		int count = baseDao.countQuery("select count(*) from Jgb where jgid = "+jgid);
		baseDao.deleteById(Jgb.class, jgid);
	}
	
	/*
	 * 机构树
	 */
	public List findJgbTree() {
		List list = baseDao.listAll("Jgb");
		return this.getTree("0", list);
	}
	
	/**  
	 * 通过递归生成tree结构  
	 * @param List childrenlist 商品类别集合
	 * @param Integer id 节点（父节点id）
	 */
	private List getTree(String id,List childrenlist) {
		List resultlist = new ArrayList();

		//当前级菜单集合
		List list = this.getChildrens(childrenlist, id);
		for (Object obj : list) {
			Jgb jgb = (Jgb) obj;

			TreeNode treeNode = new TreeNode();
			treeNode.setId(jgb.getJgid());
			treeNode.setText(jgb.getJgmc());
			treeNode.setIconCls("menu-folder");
			//子菜单
			List children = this.getChildrens(childrenlist, jgb.getJgid());
			if (children.size() > 0) {//有子类别集合
				treeNode.setLeaf(false);
				treeNode.setChildren(getTree(jgb.getJgid(),childrenlist)); //递归调   
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
	private List getChildrens(List jgbs, String pid) {
		List resultList = new ArrayList();
		Jgb jgb = null;
		for (Object obj : jgbs) {
			jgb = (Jgb) obj;
			if (jgb.getPid()!=null&&jgb.getPid().equals(pid)) {//父节点id
				resultList.add(jgb);
			}
		}
		return resultList;
	}


}
