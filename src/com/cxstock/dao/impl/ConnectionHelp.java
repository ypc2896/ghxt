package com.cxstock.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cxstock.dao.BaseDAO;

/** 统一数据访问接口实现 */
@SuppressWarnings("unchecked")
public final class ConnectionHelp extends HibernateDaoSupport{
	
	 
	/** 从连接池中取得一个JDBC连接 */
	@SuppressWarnings("deprecation")
	public Connection getConnection() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
	}
	 
		 	 
	
	/** 调用存储过程 */
	public void callProcedure(String call) {
		SQLQuery query = this.getSession().createSQLQuery(call);    
		query.executeUpdate();
	}

}