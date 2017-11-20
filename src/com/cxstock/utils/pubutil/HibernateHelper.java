package com.cxstock.utils.pubutil;


import java.io.BufferedInputStream;
import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
public final class HibernateHelper
{
  private static final Logger logger = Logger.getLogger(HibernateHelper.class);
  private SessionFactory sessionFactory;
  private final ThreadLocal<org.hibernate.Session> threadLocal = new ThreadLocal();
  private Configuration configuration;
  private final Map<String, SessionFactory> sessionFactoryCache = new HashMap();
  private final Map<String, Configuration> configurationCache = new HashMap();
  private final Map<String, ThreadLocal<org.hibernate.Session>> threadLocalCache = new HashMap();
  private final List<String> dsNames = new ArrayList();
  private static HibernateHelper hibernateHelper;
  
  private void initFactory(SessionFactory sessionfactory)
  {
    this.sessionFactory = sessionfactory;
  }

  private void initConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }
  /**
	 * 根据HQL语句信息，生成HQL中存在的参数信息。并与书写顺序一致
	 * 
	 * @param hql
	 * @return
	 */
	public static String[] getParaNames(String hql) {
		
	 
			String sql=new String(hql);
			Pattern p=Pattern.compile(":\\w+");
			Matcher m=p.matcher(sql);
			 
			 
			List nameList=new  ArrayList();
			int i=0;
			while(m.find()){  
				nameList.add(m.group());
			} 
			
			
		    String[] names=new String[nameList.size()];
		    
		    for(Iterator it=nameList.iterator();it.hasNext();i++){
		    	String key=(String)it.next();
		    	names[i]=key.replace(":", "");
		    	
		    } 
			
			return names; 
			 
	}
	/**
	 * 书写参数的参数类型
	 * 
	 * @return Type[]
	 */
	private static Type getType(Class obj) {
		Type type = Hibernate.STRING;

		if (Date.class.equals(obj)) {
			type = Hibernate.DATE;
		} else if (java.sql.Date.class.equals(obj)) {
			type = Hibernate.DATE;
		}  else if (String.class.equals(obj)) {
			type = Hibernate.STRING;
		} else if (Double.class.equals(obj)) {
			type = Hibernate.DOUBLE;
		} else if (Long.class.equals(obj)) {
			type = Hibernate.LONG;
		}  else if (Integer.class.equals(obj)) {
			type = Hibernate.INTEGER;
		} else if (BigDecimal.class.equals(obj)) {
			type = Hibernate.BIG_DECIMAL;
		} else {
			type = Hibernate.STRING;
		}

		return type;
	}
	/**
	 * 书写参数的参数类型
	 * 
	 * @param Object
	 *            entity
	 * @param String[]
	 *            props
	 * @return Type[]
	 * @throws ApplicationException
	 */
	public static Type[] getTypes(Object entity, String paras[])
			throws RuntimeException {
		if (paras == null || paras.length < 1)
			return null;

		ArrayList al = new ArrayList();

		for (int i = 0; i < paras.length; i++) {
			Class temp = null;
			try {
				temp = PropertyUtils.getPropertyType(entity, paras[i]);
			} catch (IllegalAccessException iae) {
				throw new RuntimeException("非法访问出错!", iae);
			} catch (InvocationTargetException ite) {
				throw new RuntimeException("引用目标出错!", ite);
			} catch (NoSuchMethodException nsme) {
				throw new RuntimeException("没有指定的方法出错!", nsme);
			}
			al.add(getType(temp));
		}

		Type[] types = new Type[al.size()];
		al.toArray(types);

		return types;
	}
public static String fillSqlParam(String sql, Map params)
    throws Exception
	  {
	    String[] para = getParaNames(sql);
	    Type[] types = getTypes(params, para);
	    if (types == null){
	   	 return sql;
	    }
	    return sql = QueryFactoryDeprecated.getHQL(sql, para, types, params);
}	
  public static String getHql(String sql,Map params){
		if (params == null){
			params = new HashMap();
		}
		String rtnhql=sql;
		try { 
		    rtnhql=fillSqlParam(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rtnhql;
	}
  public static Map queryBySql(Map map, String sql)
  {
     Session session = HibernateHelper.createSession();
     Map resMap = new HashMap();
    try {
      Object page = map.get("page");
      Object pagesize = map.get("pagesize");
      Object rowcount = map.get("rowcount");
      String sql_query = getHql(sql, map); 
      if ((rowcount == null) || (rowcount.toString().equals("NaN")) || (rowcount.equals("0")))
      {
        String sql_count = "select count(*) as count_columns from (" + sql_query + ") ylz_alias_table";
        try {
          SQLQuery count_query = session.createSQLQuery(sql_count);
          Object obj = count_query.list().get(0);
          resMap.put("rowcount", obj.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      else
      {
    	  resMap.put("rowcount",rowcount.toString());
      }
      int start = (Integer.parseInt(map.get("page").toString()) - 1) * Integer.parseInt(map.get("pagesize").toString());

      SQLQuery q = session.createSQLQuery(sql_query);
      q.setFirstResult(start);
      q.setMaxResults(Integer.parseInt(map.get("pagesize").toString()));
      q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
      List resList = q.list();
      List resData = new ArrayList();
      for (Iterator  it = resList.iterator(); it.hasNext(); ) {
        Map map1 = (Map)it.next();
        Map resMap1 = new HashMap();
        for (Iterator it_map = map1.keySet().iterator(); it_map.hasNext(); ) {
          Object key = it_map.next();
          Object val = map1.get(key);
          if (val instanceof Clob)
          {
            Reader r = ((Clob)val).getCharacterStream();
            char[] c = new char[(int)((Clob)val).length()];
            r.read(c);
            val = new String(c);
          }

          if (val instanceof Blob) {
            Blob blob = (Blob)val;
            BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int)blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while ((offset < len) && ((read = is.read(bytes, offset, len - offset)) >= 0)) {
              offset += read;
            }
            val = bytes;
          }
          resMap1.put(key.toString().toLowerCase(), val);
        }

        resData.add(resMap1);
      }

      resMap.put("datas",resData);
    }
    catch (Exception e) {
      e.printStackTrace();

      resMap.put("result","error");
      resMap.put("info",e.getMessage());
    }

    return resMap;
  }
  public static List executeQuery(String sql)
  {
	    Connection conn = HibernateHelper.getConnection();
		Statement stmt;
		List listh = new ArrayList();
		try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        listh = new JsonUtil().extractData(rs);
	        rs.close();
	        stmt.close();
	        return listh;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (conn != null) {
				
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
  }
  public static List executeSqlQuery(String sql,Class c)
  {
		List listh = currentSession().createSQLQuery(sql).addEntity(c).list();
		return listh;
  }
  public static Connection getConnection() {
      org.hibernate.Session session = createSession();
    //org.hibernate.Session session = currentSession();
    return getConnectionFromSession(session);
  }

  public static Connection getConnection(String dbname) {
    org.hibernate.Session session = createSession(dbname);
    return getConnectionFromSession(session);
  }

  public static Connection currentConnection() {
    org.hibernate.Session session = currentSession();
    return getConnectionFromSession(session);
  }

  public static Connection currentConnection(String dbname) {
    org.hibernate.Session session = currentSession(dbname);
    return getConnectionFromSession(session);
  }

  private static Connection getConnectionFromSession(org.hibernate.Session session) {
    Connection connection = session.connection();
    logger.debug("打开数据库连接" + connection);
    //return connection;
    //return (Connection)Proxy.newProxyInstance(connection.getClass().getClassLoader(), new Class[] { Connection.class }, new HibernateHelper());
    
    connection = (Connection)Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(), new Handler(connection, session));
   // connection = (Connection)Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(), new Handler(connection, session));
    return connection;
  }

  public static org.hibernate.classic.Session createClassicSession() throws HibernateException {
    return (org.hibernate.classic.Session)createSession();
  }

  public static org.hibernate.Session createSession() throws HibernateException {
    return getInstance().sessionFactory.openSession();
  }

  private HibernateHelper() {
    initConfiguration(new Configuration().configure());
    initFactory(this.configuration.buildSessionFactory());
  }

  private static synchronized HibernateHelper getInstance() {
    if (hibernateHelper == null)
      hibernateHelper = new HibernateHelper();
    return hibernateHelper;
  }

  public static void clearSession() {
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if (session != null)
      session.clear();
  }

  public static void closeSession() throws HibernateException {
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if (session != null) {
      session.close();
      getInstance().threadLocal.set(null);
      logger.info("Hibernate session closed :" + session);
    }
  }

  public static void closeAllSessions() throws HibernateException
  {
    if (!getInstance().threadLocalCache.isEmpty()) {
      Set keySet = getInstance().threadLocalCache.keySet();
      for (Object key : keySet) {
        ThreadLocal tl = (ThreadLocal)getInstance().threadLocalCache.get(key.toString());
        org.hibernate.Session session = (org.hibernate.Session)tl.get();
        if (session != null) {
          session.close();
          tl.set(null);
        }
      }
    }
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if (session != null) {
      session.close();
      getInstance().threadLocal.set(null);
      logger.info("Hibernate session closed :" + session);
    }
  }

  public static org.hibernate.Session createSession(String dbname) throws HibernateException {
    org.hibernate.Session session = ((SessionFactory)getInstance().sessionFactoryCache.get(dbname)).openSession();
    ((ThreadLocal)getInstance().threadLocalCache.get(dbname)).set(session);
    return session;
  }

  public static org.hibernate.classic.Session createClassicSession(String dbname) throws HibernateException {
    return (org.hibernate.classic.Session)createSession(dbname);
  }

  public static org.hibernate.classic.Session currentClassicSession() throws HibernateException {
    return (org.hibernate.classic.Session)currentSession();
  }

  public static org.hibernate.Session currentSession() throws HibernateException {
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if ((session == null) || (!session.isOpen()) || (!session.isConnected())) {
     // if (session != null) {
       // session.close();
     // }
      session = getInstance().sessionFactory.openSession();
      getInstance().threadLocal.set(session);
      logger.info("Hibernate session created：" + session);
    }else
    {
    	//1 session.flush()的作用就是将session的缓存中的数据与数据库同步。
        //2 session.clear()的作用就是清除session中的缓存数据（不管缓存与数据库的同步）。
    	flushAndClearSession();
    }
    return session;
  }

  public static void flushAndClearSession() throws HibernateException {
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if (session != null) {
      session.flush();
      session.clear();
    }
  }

  public static void flushAndClearAllSessions() throws HibernateException {
    if (!getInstance().threadLocalCache.isEmpty()) {
      Set keySet = getInstance().threadLocalCache.keySet();
      for (Object key : keySet) {
        ThreadLocal tl = (ThreadLocal)getInstance().threadLocalCache.get(key.toString());
        org.hibernate.Session session = (org.hibernate.Session)tl.get();
        if (session != null) {
          session.flush();
          session.clear();
        }
      }
    }
    org.hibernate.Session session = (org.hibernate.Session)getInstance().threadLocal.get();
    if (session != null) {
      session.flush();
      session.clear();
    }
  }

  public static Configuration getConfiguration() {
    return getInstance().configuration;
  }

  public static SessionFactory getSessionFactory() {
    return getInstance().sessionFactory;
  }

  public static SessionFactoryImplementor getSessionFactoryImplementor() {
    return (SessionFactoryImplementor)getSessionFactory();
  }

  public static SessionFactoryImplementor getSessionFactoryImplementor(String dbname) {
    return (SessionFactoryImplementor)getSessionFactory(dbname);
  }

  public static void buildSessionFactory(String confFile, String dbname) {
    if (StringUtils.isBlank(dbname)) {
      throw new RuntimeException("buildSessionFactory方法的数据源名称dsname参数不能为空！");
    }
    synchronized (getInstance().configurationCache) {
      Configuration configuration = (Configuration)getInstance().configurationCache.get(dbname);
      if (configuration != null) {
        throw new RuntimeException("名称为" + dbname + "的数据源已经存在，不能使用重复的数据源名称！");
      }
      configuration = new Configuration().configure(confFile);
      getInstance().dsNames.add(dbname);
      getInstance().configurationCache.put(dbname, configuration);
      getInstance().sessionFactoryCache.put(dbname, configuration.buildSessionFactory());
      getInstance().threadLocalCache.put(dbname, new ThreadLocal());
    }
  }

  public static void clearSession(String dbname) {
    if (dbname == null)
      clearSession();
    ThreadLocal threadlocal = getThreadLocal(dbname);
    org.hibernate.Session session = (org.hibernate.Session)threadlocal.get();
    if (session != null)
      session.clear();
  }

  private static ThreadLocal<org.hibernate.Session> getThreadLocal(String dbname) {
    ThreadLocal threadlocal = (ThreadLocal)getInstance().threadLocalCache.get(dbname);
    a(dbname, threadlocal);
    return threadlocal;
  }

  private static void a(String dbname, Object obj) {
    if (obj == null)
      throw new RuntimeException("指定的数据源" + dbname + "不存在！");
  }

  public static void closeSession(String dbname)
    throws HibernateException
  {
    if (dbname == null)
      closeSession();
    ThreadLocal threadlocal = getThreadLocal(dbname);
    org.hibernate.Session session = (org.hibernate.Session)threadlocal.get();
    if (session != null) {
      session.close();
      threadlocal.set(null);
      logger.info("Hibernate session " + dbname + " closed :" + session);
    }
  }

  public static org.hibernate.classic.Session currentClassicSession(String dbname) throws HibernateException {
    return (org.hibernate.classic.Session)currentSession(dbname);
  }

  public static org.hibernate.Session currentSession(String dbname) throws HibernateException {
    if (dbname == null)
      return currentSession();
    ThreadLocal threadlocal = getThreadLocal(dbname);
    org.hibernate.Session session = (org.hibernate.Session)threadlocal.get();
    if ((session == null) || (!session.isOpen()) || (!session.isConnected())) {
      if (session != null) {
        session.close();
      }
      logger.info("Create Hibernate session " + dbname + " ...");
      session = ((SessionFactory)getInstance().sessionFactoryCache.get(dbname)).openSession();
      logger.info("Hibernate session created " + dbname + " : " + session);
      threadlocal.set(session);
    }
    return session;
  }

  public static void flushAndClearSession(String dbname) throws HibernateException {
    if (dbname == null)
      flushAndClearSession();
    ThreadLocal threadlocal = getThreadLocal(dbname);
    org.hibernate.Session session = (org.hibernate.Session)threadlocal.get();
    if (session != null) {
      session.flush();
      session.clear();
    }
  }

  public static Configuration getConfiguration(String dbname) {
    if (dbname == null)
      getConfiguration();
    Configuration configuration = (Configuration)getInstance().configurationCache.get(dbname);
    a(dbname, configuration);
    return configuration;
  }

  public static SessionFactory getSessionFactory(String dbname) {
    if (dbname == null)
      getSessionFactory();
    SessionFactory sessionfactory = (SessionFactory)getInstance().sessionFactoryCache.get(dbname);
    a(dbname, sessionfactory);
    return sessionfactory;
  }

  public static List<String> getOtherDSNames() {
    return getInstance().dsNames;
  }
}
  class Handler implements InvocationHandler
{
  private final Session session;
  private final Connection connection;
  private static final Logger logger = Logger.getLogger(HibernateHelper.class);
  public Object invoke(Object obj, Method method, Object[] aobj)
    throws Throwable
  {
    if ("close".equals(method.getName())) {
    	 logger.debug("关闭数据库连接..." + this.connection);
      return this.session.close();
    }
    return method.invoke(this.connection, aobj);
  }

  public Handler(Connection connection, Session session)
  {
    this.connection = connection;
    this.session = session;
  }
  
 
}
