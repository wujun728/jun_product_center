package com.shuogesha.platform.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBRef; 

public class BeanUtil {
	
	public static <T> DBObject beanIDObject(T bean) throws IllegalArgumentException, IllegalAccessException {
		if (bean == null) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		// 获取对象对应类中的所有属性域
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) { 
			// 获取属性名
			String varName = field.getName();
			if("id".equals(varName)) {
				// 修改访问控制权限
				boolean accessFlag = field.isAccessible();
				if (!accessFlag) {
					field.setAccessible(true);
				}
				Object param = field.get(bean);
				if (param == null) { 
					continue;
				} else if (param instanceof Integer) {// 判断变量的类型
					int value = ((Integer) param).intValue();
					dbObject.put(varName, value);
				} else if (param instanceof String) {
					String value = (String) param;
					dbObject.put(varName, value);
				} else if (param instanceof Double) {
					double value = ((Double) param).doubleValue();
					dbObject.put(varName, value);
				} else if (param instanceof Float) {
					float value = ((Float) param).floatValue();
					dbObject.put(varName, value);
				} else if (param instanceof Long) {
					long value = ((Long) param).longValue();
					dbObject.put(varName, value);
				} else if (param instanceof Boolean) {
					boolean value = ((Boolean) param).booleanValue();
					dbObject.put(varName, value);
				} else if (param instanceof Date) {
					Date value = (Date) param;
					dbObject.put(varName, value);
				} 
				// 恢复访问控制权限
				field.setAccessible(accessFlag);
			} 
		}
		return dbObject;
	}

	
	public static <T> DBObject bean2DBObject(T bean,Boolean isNull) throws IllegalArgumentException, IllegalAccessException {
		if (bean == null) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		// 获取对象对应类中的所有属性域
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 获取属性名
			String varName = field.getName();
			// 修改访问控制权限
			boolean accessFlag = field.isAccessible();
			if (!accessFlag) {
				field.setAccessible(true);
			}
			Object param = field.get(bean);
			if (param == null) {
				if(!isNull) {
					dbObject.put(varName, param);
				}
				continue;
			} else if (param instanceof Integer) {// 判断变量的类型
				int value = ((Integer) param).intValue();
				dbObject.put(varName, value);
			} else if (param instanceof String) {
				String value = (String) param;
				dbObject.put(varName, value);
			} else if (param instanceof Double) {
				double value = ((Double) param).doubleValue();
				dbObject.put(varName, value);
			} else if (param instanceof Float) {
				float value = ((Float) param).floatValue();
				dbObject.put(varName, value);
			} else if (param instanceof Long) {
				long value = ((Long) param).longValue();
				dbObject.put(varName, value);
			} else if (param instanceof Boolean) {
				boolean value = ((Boolean) param).booleanValue();
				dbObject.put(varName, value);
			} else if (param instanceof Date) {
				Date value = (Date) param;
				dbObject.put(varName, value);
			}
			//
			if(field.getAnnotationsByType(org.springframework.data.mongodb.core.mapping.DBRef.class).length>0) { 
				if(param instanceof ArrayList) {
					List list = (List) param;
					JSONArray json = new JSONArray();
					for (Object obj : list) {
						DBObject js = new BasicDBObject();
						DBObject oo = beanIDObject(obj);
						js.put("$ref", obj.getClass().getSimpleName().toLowerCase());
						js.put("$id", oo.get("id"));
						js.put("$db", "");
						json.add(js);
					}
					dbObject.put(varName, json);
				}
			}
			// 恢复访问控制权限
			field.setAccessible(accessFlag);
		}
		return dbObject;
	}

	/**
	 * 把DBObject转换成bean对象
	 * 
	 * @param dbObject
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static <T> T dbObject2Bean(DBObject dbObject, T bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (bean == null) {
			return null;
		}
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			String varName = field.getName();
			Object object = dbObject.get(varName);
			if (object != null) {
				BeanUtils.setProperty(bean, varName, object);
			}
		}
		return bean;
	}
	
	/**
	 * 转换update
	 * @param object
	 * @return
	 */
	public static Update fromDBObjectExcludeNullFields(DBObject object) {
	    Update update = new Update();       
	    for (String key : object.keySet()) {
	        Object value = object.get(key);
	        if(value!=null){
	            update.set(key, value);
	        }
	    }
	    return update;
	}
	/**
	 * all
	 * @param object
	 * @return
	 */
	public static Update fromDBObject(DBObject object) {
	    Update update = new Update();       
	    for (String key : object.keySet()) {
	        Object value = object.get(key);
 	        update.set(key, value); 
	    }
	    return update;
	}
	
	public static <T> Update fromBean(T bean,Boolean isNull) {
	    Update update = new Update();   
	    try {
	    	DBObject object= bean2DBObject(bean,isNull);
		    for (String key : object.keySet()) {
		        Object value = object.get(key);
		        if("id".equals(key)) {
		        	continue;
		        }
		        if(isNull) {
		        	if(value!=null){
			            update.set(key, value);
			        } 
		        }else {
		        	update.set(key, value);
		        } 
		    }
		} catch (Exception e) {
			 //暂时不处理
		} 
	    return update;
	} 
}
