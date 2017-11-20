/**
 * 
 */
package com.cxstock.utils.pubutil;


import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.cxstock.dao.impl.ConnectionHelp;

/**
 * @author: wujf
 * @description:
 */
public class SequenceUtil {
	
	private static final  Logger LOGGER = Logger.getLogger(SequenceUtil.class);
	
	/**
	 * 获取sequence
	 * @param conn
	 * @param seqName
	 * @return
	 */
	public static String getSequence(String seqName){
		Session session = null;
		String s = "";
		if(seqName == null){
			throw new RuntimeException("sequence name can not be null!");
		}
		try{
			 session = new ConnectionHelp().getSessionFactory().getCurrentSession();
			 s = session.createSQLQuery("select " + seqName + ".nextval from dual").uniqueResult().toString();
		 }catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 throw new RuntimeException(e);
		}finally{
          session.close();
		}
		 return s;
	}

}
