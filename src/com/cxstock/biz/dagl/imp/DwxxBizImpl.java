package com.cxstock.biz.dagl.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequence;

import org.apache.commons.beanutils.BeanUtils;

 
import com.cxstock.biz.dagl.DwxxBiz;
import com.cxstock.biz.dagl.dto.DwxxDTO;
import com.cxstock.dao.BaseDAO;
import com.cxstock.pojo.Dwxx;
import com.cxstock.pojo.Xmlb;
import com.cxstock.utils.pubutil.ComboData;
import com.cxstock.utils.pubutil.Page;
import com.cxstock.utils.pubutil.TreeNode;
import com.lbs.leaf.exception.BusinessException;

@SuppressWarnings("unchecked")
public class DwxxBizImpl implements DwxxBiz {
	
	private BaseDAO baseDao;
	public void setBaseDao(BaseDAO baseDao) {
		this.baseDao = baseDao;
	}
	
	/*
	 * 分页查询单位列表
	 */
	
	/**4
	 * 5  findPageDwxx
	 */
	public void findPageDwxx(String aab001,String aab003,Page page) {
		if(aab001==null){
			aab001 = "";
		}
		if(aab003==null){
			aab003 = "";
		}
		String hql="from Dwxx where aab001 like '%"+aab001+"%' and aab003 like '%"+aab003+"%'";
		

		List list = baseDao.findByHql(hql,page.getStart(), page.getLimit());
		hql="select count(*)from Dwxx where aab001 like '%"+aab001+"%' and aab003 like '%"+aab003+"%'";
		int total = baseDao.countQuery(hql);
		page.setRoot(list);
		page.setTotal(total);
	}
	
	/*
	 * 保存/修改单位
	 */
	public void saveOrUpdateDwxx(DwxxDTO dto) {
		Dwxx dwxx = new Dwxx();
		try {
			
			if(dto.getDwbh00().equals("")||dto.getDwbh00().equals(null)){  
			   String dwbh00 = baseDao.getSequence("SEQ_DWBH00");
			   BeanUtils.copyProperties(dwxx,dto);
			   dwxx.setDwbh00(dwbh00);
			}
			else
			{
			   //dwxx = (Dwxx)baseDao.loadById(Dwxx.class, dto.getDwbh00());
				BeanUtils.copyProperties(dwxx, dto);
			}
			
			baseDao.saveOrUpdate(dwxx);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	/*
	 * 删除单位
	 */
	public void deleteDwxx(String dwbh00) {
		baseDao.deleteById(Dwxx.class, dwbh00);	
	}

	/*
	 *  单位下拉拉列表
	 */
	public List<ComboData> findDwxxComb() {
		List<ComboData> list = new ArrayList<ComboData>();
		List<Dwxx> gysList = baseDao.listAll("Dwxx");
		for (Dwxx dwxx : gysList) {
			ComboData comb = new ComboData();
			comb.setValue(dwxx.getAab001());
			comb.setText(dwxx.getAab003());
			list.add(comb);
		}
		return list;
	}
 

	/*
	 * 类别树
	 */
	public List findDwlbTree() {
		List list = baseDao.listAll("Dwxx");
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
			Dwxx dwxx = (Dwxx) obj;

			TreeNode treeNode = new TreeNode();
			treeNode.setId(dwxx.getAab001().toString());
			
			//-------------------------
			treeNode.setText(dwxx.getAab001().toString()+"_"+dwxx.getAab003());
			treeNode.setIconCls("menu-folder");
			//子菜单
			List children = this.getChildrens(childrenlist, dwxx.getAab001());
			if (children.size() > 0) {//有子类别集合
				treeNode.setLeaf(false);
				treeNode.setChildren(getTree(dwxx.getAab001(),childrenlist)); //递归调   
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
	private List getChildrens(List dwxxs, String aab002) {
		List resultList = new ArrayList();
		Dwxx dwxx = null;
		for (Object obj : dwxxs) {
			dwxx = (Dwxx) obj;
			if (dwxx.getAab002()!=null&&dwxx.getAab002().equals(aab002)) {//父节点id
				resultList.add(dwxx);
			}
		}
		return resultList;
	}
	
	
	
	
}
