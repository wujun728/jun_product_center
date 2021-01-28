package org.myframework.dao.orm;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;
import org.myframework.commons.beanutil.DateConverter;

import com.google.common.collect.Maps;

 
 /**
  * SQL映射转换器
  * @author Wujun
  *
  * @param <T>
  */
public class BeanTransformer <T> implements ResultTransformer{
    private static final long serialVersionUID = 1L;  
    private final Class<T> clazz;  
    private Map<String,String> fieldMap;
    
    static {
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
		ConvertUtils.register(new DateConverter(), java.sql.Date.class);
	}
      
    public BeanTransformer(Class<T> clazz) {  
        if(clazz==null){
        	throw new IllegalArgumentException("clazz cannot be null"); 
        }
       this.clazz = clazz;
       this.fieldMap=getMethodNames(clazz);
    }
    
	public static BeanTransformer aliasToBean(Class target) {
		return new BeanTransformer(target);
	}
	
	public <T> Map<String,String> getMethodNames(Class<T> c){
		Map<String,String> resultMap=Maps.newHashMap();
		for(Class<?> clazz = c ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
	        for (Field field : clazz.getDeclaredFields()) { 
        		if(field.isAnnotationPresent(Column.class)){
        			Column colume=field.getAnnotation(Column.class);		//注解名则得先得到对应的注释name
	            	if(colume!=null && colume.name()!=null){
	            		String fieldName=colume.name().toUpperCase();
	            		resultMap.put(fieldName, field.getName());
	            	}
        		}else if(field.isAnnotationPresent(Transient.class)){
        			resultMap.put(field.getName().toUpperCase(), field.getName());
        		}
	        }
		}
        return resultMap;
	}
    
    /**
     * 结果转换
     * @param: tuple 值数组
     * @param: aliases 名数组
     */
    public Object transformTuple(Object[] tuple, String[] aliases) {  
    	Object result = null;  
        boolean isSet=false;
        try {  
        	result = clazz.newInstance();  //这里使用SETTER方法填充POJO对象  
                for (int i = 0; i < aliases.length; i++) {  
                    String alias = aliases[i];  
                    if(alias != null) {   
                    	String funName=fieldMap.get(alias.toUpperCase());  //得到set 方法
                    	if(funName!=null && tuple[i]!=null){
                    		isSet=true;
                    		BeanUtils.setProperty(result, funName, tuple[i]);
                    	}
                    }  
                }    
        } catch (InstantiationException e) {  
            throw new HibernateException("Could not instantiate resultclass: " + clazz.getName());  
        } catch (IllegalAccessException e) {  
            throw new HibernateException("Could not instantiate resultclass: " + clazz.getName());  
        } catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        return isSet?result:null;  
    }  

	@Override
	public List transformList(List collection) {
		return collection;
	}  
    }