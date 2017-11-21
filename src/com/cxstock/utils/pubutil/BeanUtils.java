package com.cxstock.utils.pubutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class BeanUtils {
	public static void copyProptiesToMap(Object bean, Map map)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Field[] field = bean.getClass().getDeclaredFields();
		Class<?> classType = bean.getClass();
		for (int i = 0; i < field.length; i++) {
			String paraName = field[i].getName();
			Method method = classType.getMethod("get"
					+ paraName.substring(0, 1).toUpperCase()
					+ paraName.substring(1));
			Object value = method.invoke(bean);
			if (value != null) {
				map.put(paraName, value);
			}
		}
	}

	public static void mapToMap(Map bean, Map map) {
		Set set = bean.keySet();
		Object objTemp = null;
		for (Object obj : bean.entrySet()) {
			Map.Entry entry = (Entry) obj;
			objTemp = entry.getValue();
			if(objTemp instanceof Map){
				Map mapTemp = new HashMap();
				mapToMap((Map)objTemp,mapTemp);
				map.put(entry.getKey(), mapTemp);
				continue;
			}
			map.put(entry.getKey(), objTemp);
		}
	}
}
