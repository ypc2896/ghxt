package com.cxstock.utils.pubutil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;

import util.CharFilter;



public class JsonUtil {

	/**
	 * json转换成实体
	 * @param jsonStr 传入的json字符串
	 * @param _class 需要转换的类
	 * @return object 生成的对象
	 */
	public static Object jsonToObj(String jsonStr, Class _class) {
		JSONObject json = JSONObject.fromObject(jsonStr);
		Field[] fields = _class.getDeclaredFields();
		Object obj = null;
		try {
			obj = _class.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			return null;
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			return null;
		}
		for (Field field : fields) {
			try {
				String strValue = "";
				try {
					strValue = json.getString(field.getName());
				} catch (net.sf.json.JSONException e) {
					continue;
				}
				if ("class java.lang.String".equals(field.getType().toString())) {

					if (!NullFlag.isObjNull(strValue)) {
						String methodName = "set"
								+ field.getName().replaceFirst(
										field.getName().substring(0, 1),
										field.getName().substring(0, 1)
												.toUpperCase());
						Method method = obj.getClass().getDeclaredMethod(
								methodName, String.class);
						method.invoke(obj, strValue);
					}
				} else if ("class java.sql.Date".equals(field.getType()
						.toString())) {
					if (!NullFlag.isObjNull(strValue)
							&& !"null".equals(strValue)) {
						java.sql.Date value = java.sql.Date.valueOf(strValue);
						String methodName = "set"
								+ field.getName().replaceFirst(
										field.getName().substring(0, 1),
										field.getName().substring(0, 1)
												.toUpperCase());
						Method method = obj.getClass().getDeclaredMethod(
								methodName, java.sql.Date.class);
						method.invoke(obj, value);
					}
				} else if ("class java.math.BigDecimal".equals(field.getType()
						.toString())) {
					if (!NullFlag.isObjNull(strValue)
							&& !"null".equals(strValue)) {
						java.math.BigDecimal value = new java.math.BigDecimal(
								strValue);
						String methodName = "set"
								+ field.getName().replaceFirst(
										field.getName().substring(0, 1),
										field.getName().substring(0, 1)
												.toUpperCase());
						Method method = obj.getClass().getDeclaredMethod(
								methodName, java.math.BigDecimal.class);
						method.invoke(obj, value);
					}
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 把实体对象转换成JSON格式数据
	 * @param obj
	 * @return
	 */
	public static String convertObject2Json(Object obj, String flag) {
		StringBuffer json = new StringBuffer();
		json.append("{");
		Class cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		Object objValue = null;
		String name = "";
		for (int i = 0; i < fields.length; i++) {
			name = fields[i].getName();
			try {
				objValue = PropertyUtils.getProperty(obj, name);
			} catch (IllegalAccessException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

			objValue = (objValue == null ? "" : objValue);
			String objValue2String = "";

			//校验数据
			Class csl = objValue.getClass();
			if (objValue.getClass().getName().equals("java.lang.String")) {
				objValue2String = objValue.toString();
				//转义特殊字符
				if (objValue2String != null) {
					if (objValue2String.contains("\r\n")) {
						objValue2String = objValue2String.replaceAll("\r\n",
								"@"); //把回车转义成@字符
					}
					if (objValue2String.contains("\n")) {
						objValue2String = objValue2String.replaceAll("\n", "@"); //把回车转义成@字符
					}
				}
				objValue = objValue2String;
			}

			if (flag.equals("sysh")) {
				//构造JSON格式数据
				json.append("\"").append(name).append("\":\"").append(objValue)
						.append("\",");
			} else if (flag.equals("ygbb")) {
				//构造JSON格式数据
				json.append("\"").append(name).append("\":\"").append(
						CharFilter.codeFilter(objValue)).append("\",");
			}

		}

		String data = json.toString();
		data = data.substring(0, data.length() - 1) + "}";
		return data;
	}
	   /**  
     * 将普通的pojo转换成JSON字符串  
     * @return  
     */  
    public JSONObject bean2json() {   
    	JSONObject json1=new JSONObject();
        json1.accumulate("id", "123");
        json1.accumulate("name", "JSONServlet");
        json1.accumulate("password", "JSON");
        json1.accumulate("say", "Hello , i am a servlet !");
        json1.put("id1", "123");
        json1.put("name1", "JSONServlet");
        json1.put("password1", "JSON");
        json1.put("say1", "Hello , i am a servlet !");
        JSONObject json=new JSONObject();
       // json.accumulate("success", true);
        json.accumulate("user", json1);      
        System.out.println("User转换后的字符串:"+json.toString());   
        return json;   
    }   
    /**  
     * 从request中取出参数 放到map中
     * @param jsonObject  
     */  
    public Map getDatas() {   
    	Map map = new HashMap();
    	HttpServletRequest request  = ServletActionContext.getRequest();
		Map maplist  =(HashMap)request.getParameterMap();
		for(Iterator it_map=maplist.keySet().iterator();it_map.hasNext();){ 
			Object key=it_map.next(); 
			String[] val=(String[])maplist.get(key);
			String vals = val[0];	
			map.put(key.toString().toLowerCase(),vals);  
		}
		return map;
    }
    /**  
     * 从JSONObject对象中反向解析出User对象  
     * @param jsonObject  
     */  
    public void json2bean(JSONObject jsonObject) {   
       // User user=(User)JSONObject.toBean((JSONObject)jsonObject.get("user"),User.class);   
       // System.out.println("转换得到的User对象的Name为:"+user.getName());   
    }  
    /*
     * 
     * 取客户端传递的参数，转换成jsobj
     * 
     */
    public JSONObject getJson(){
	    HttpServletRequest request  = ServletActionContext.getRequest();
		Map map  =(HashMap)request.getParameterMap();
		Set set = map.keySet();
		Iterator it = set.iterator();
		String key=null;
		if(it.hasNext()){
            key = (String) it.next();
		}
	 	JSONObject jsObj = JSONObject.fromObject(key);
	 	return jsObj;
    }
    /*
      while(rs.next()){
	        	  Map resMap=new HashMap();
	        	  resMap.put("aac001",rs.getString("aac001"));
	        	  resMap.put("aac002",rs.getString("aac002"));
	        	  resMap.put("aac003",rs.getString("aac003"));
	        	  resMap.put("aac004",rs.getString("aac004"));
		          String ls = rs.getString(1);
		          System.out.print(rs.getString(3));
		          System.out.print(rs.getString(4));
		          System.out.print(ls);
		          resData.add(resMap);
		          /			//通过ActionContext对象访问Web应用的Session
//		 HttpServletResponse response = ServletActionContext.getResponse();
//
//		 PrintWriter out=null;
//		 try {
//		 response.setCharacterEncoding("utf-8");
//		 out=response.getWriter();
//
//		 out.print("suc");
//		 } catch (Exception e) {
//
//		 e.printStackTrace();
//		 }
//		 out.flush();
//		 out.close(); 
		     }
		     //把json中的对象取出来放到MAP中
		     /*JSONObject jsObj = new JsonUtil().getJson();
	map.put("page", jsObj.getString("page"));
	map.put("pagesize", jsObj.getString("pagesize"));
	if(jsObj.containsKey("sac101")){
	   map.put("sac101", jsObj.getString("sac101"));
	}
	if(jsObj.containsKey("aac002")){
	   map.put("aac002", jsObj.getString("aac002"));
	}
	if(jsObj.containsKey("aac002")){
	   map.put("aac003", jsObj.getString("aac003"));
	}*/

    /** 
    * 通用取结果方案,返回list 
    *  
    * @param rs 
    * @return 
    * @throws SQLException 
    */  
    public List extractData(ResultSet rs) throws SQLException {  
		    ResultSetMetaData md = rs.getMetaData();  
		    int num = md.getColumnCount();  
		    List listOfRows = new ArrayList();  
		    while (rs.next()) {  
			    Map mapOfColValues = new HashMap(num);  
			    for (int i = 1; i <= num; i++) {  
			       mapOfColValues.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));  
			    }  
			    listOfRows.add(mapOfColValues);  
		    }  
		    return listOfRows;  
    }  
    /** 
	    * 一般取结果方案,返回list 
	    *  
	    * @param rs 
	    * @return 
	    * @throws SQLException 
	    */  
	    public List resultsetToList(ResultSet rs) throws SQLException {  
	    	List<HashMap> listh = new ArrayList<HashMap>();
	    	while(rs.next()){
	        	  HashMap resMap=new HashMap();
	        	  resMap.put("menuid",rs.getString("menuid"));
	        	  resMap.put("menunm",rs.getString("menunm"));
	        	  resMap.put("pmid00",rs.getString("pmid00"));
	        	  resMap.put("sfqy00",rs.getString("sfqy00"));
	        	  resMap.put("pxz000",rs.getString("pxz000"));
	        	  resMap.put("ljdz00",rs.getString("ljdz00"));
	        	  resMap.put("mntype",rs.getString("mntype"));
	        	  resMap.put("xz0000",rs.getString("xz0000"));
	        	  resMap.put("mnkind",rs.getString("mnkind"));
	        	  listh.add(resMap);
	    	}
	    	return listh;
	    }    
    /** 
    * 通用取结果方案,返回JSONArray 
    *  
    * @param rs 
    * @return 
    * @throws SQLException 
    */  
    public JSONArray extractJSONArray(ResultSet rs) throws SQLException {  
    ResultSetMetaData md = rs.getMetaData();  
    int num = md.getColumnCount();  
    JSONArray array = new JSONArray();  
    while (rs.next()) {  
    JSONObject mapOfColValues = new JSONObject();  
    for (int i = 1; i <= num; i++) {  
    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));  
    }  
    array.add(mapOfColValues);  
    }  
    return array;  
    }  
}

