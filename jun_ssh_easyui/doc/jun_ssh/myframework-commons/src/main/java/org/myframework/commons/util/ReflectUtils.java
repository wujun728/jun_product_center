package org.myframework.commons.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.internal.Lists;

  
/** 
 * 
 */  
public class ReflectUtils {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
  
    private static final String SETTER_PREFIX = "set";
    
    private static final String GETTER_PREFIX = "get";
    
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    /** 
     * 通过构造函数实例化对象  
     * @param className       类的全路径名称    
     * @param parameterTypes  参数类型 
     * @param initargs        参数值 
     * @return 
     */  
    @SuppressWarnings("rawtypes")  
    public static Object constructorNewInstance(String className,Class [] parameterTypes,Object[] initargs) {   
        try {  
            Constructor<?> constructor = (Constructor<?>) Class.forName(className).getDeclaredConstructor(parameterTypes);//暴力反射  
            constructor.setAccessible(true);  
            return constructor.newInstance(initargs);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
  
    }  
  
      
    /** 
     * 暴力反射获取字段值 
     * @param fieldName 属性名 
     * @param obj       实例对象 
     * @return          属性值 
     */  
    public static Object getFieldValue(String propertyName, Object obj) {  
        try {  
            Field field = obj.getClass().getDeclaredField(propertyName);              
            field.setAccessible(true);  
            return field.get(obj);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 暴力反射获取字段值 
     * @param propertyName 属性名 
     * @param object       实例对象 
     * @return          字段值 
     */  
    public static Object getProperty(String propertyName, Object object) {  
        try {  
              
            PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());  
            Method method = pd.getReadMethod();  
            return method.invoke(object);  
              
            //其它方式  
            /*BeanInfo beanInfo =  Introspector.getBeanInfo(object.getClass()); 
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); 
            Object retVal = null; 
            for(PropertyDescriptor pd : pds){ 
                if(pd.getName().equals(propertyName)) 
                { 
                    Method methodGetX = pd.getReadMethod(); 
                    retVal = methodGetX.invoke(object); 
                    break; 
                } 
            } 
            return retVal;*/  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的,它支持属性连缀操作:如,.对象.属性 
     * @param propertyName 属性名 
     * @param object       实例对象 
     * @return          字段值 
     */  
    public static Object getBeanInfoProperty(String propertyName, Object object) {  
        try {             
            return BeanUtils.getProperty(object, propertyName);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的 
     * @param object       实例对象 
     * @param propertyName 属性名 
     * @param value        字段值 
     * @return           
     */  
    public static void setBeanInfoProperty(Object object,String propertyName,String value) {  
        try {             
            BeanUtils.setProperty(object, propertyName,value);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型 
     * @param propertyName 属性名 
     * @param object       实例对象 
     * @return          字段值 
     */  
    public static Object getPropertyUtilByName(String propertyName, Object object) {  
        try {             
            return PropertyUtils.getProperty(object, propertyName);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型,这是PropertyUtils与BeanUtils的根本区别 
     * @param object       实例对象 
     * @param propertyName 属性名 
     * @param value        字段值 
     * @return           
     */  
    public static void setPropertyUtilByName(Object object,String propertyName,Object value) {  
        try {             
            PropertyUtils.setProperty(object, propertyName,value);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 设置字段值     
     * @param obj          实例对象 
     * @param propertyName 属性名 
     * @param value        新的字段值 
     * @return           
     */  
    public static void setProperties(Object object, String propertyName,Object value) throws IntrospectionException,  
            IllegalAccessException, InvocationTargetException {  
        PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());  
        Method methodSet = pd.getWriteMethod();  
        methodSet.invoke(object,value);  
    }  
      
      
    /** 
     * 设置字段值 
     * @param propertyName 字段名 
     * @param obj          实例对象 
     * @param value        新的字段值 
     * @return           
     */  
    public static void setFieldValue(Object obj,String propertyName,Object value) {  
        try {  
            Field field = obj.getClass().getDeclaredField(propertyName);          
            field.setAccessible(true);  
            field.set(obj, value);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 设置字段值 
     * @param className        类的全路径名称 
     * @param methodName       调用方法名 
     * @param parameterTypes   参数类型 
     * @param values           参数值 
     * @param object           实例对象 
     * @return           
     */  
    @SuppressWarnings("rawtypes")  
    public static Object methodInvoke(String className,String methodName,Class [] parameterTypes,Object [] values,Object object) {  
        try {  
            Method method = Class.forName(className).getDeclaredMethod(methodName,parameterTypes);  
            method.setAccessible(true);  
            return method.invoke(object,values);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
  
  
/** 
     * @param <T> 具体对象 
     * @param fileds  要进行比较Bean对象的属性值集合(以属性值为key,属性注释为value,集合从数据库中取出) 
     * @param oldBean  源对象 
     * @param newBean  新对象 
     * @return 返回二个Bean对象属性值的异同 
     */  
    public static <T> String compareBeanValue(Map<String,String> fileds,T oldBean,T newBean){  
          
        StringBuilder compares = new StringBuilder();  
        String propertyName = null;       
        Object oldPropertyValue = null;  
        Object newPropertyValue = null;  
          
        StringBuilder descrips = new StringBuilder();                 
        for(Map.Entry<String, String> entity : fileds.entrySet()){  
            //获取新旧二个对象对应的值  
            propertyName = entity.getKey().toLowerCase();  
            oldPropertyValue = getProperty(propertyName, oldBean);  
            newPropertyValue = getProperty(propertyName, newBean);            
                              
            if(null == oldPropertyValue && null == newPropertyValue){  
                continue;  
            }             
            if("".equals(oldPropertyValue) && "".equals(newPropertyValue)){  
                continue;  
            }             
            if(null == oldPropertyValue){  
                oldPropertyValue = "";  
            }             
            if(null == newPropertyValue){  
                newPropertyValue = "";  
            }             
              
            if(oldPropertyValue.equals(newPropertyValue)){            
                continue;  
            }  
            compares.append("字段注释: ").append(entity.getValue()).append("】").append("原属性值\"");  
            if(StringUtils.isEmpty(oldPropertyValue+"")){  
                oldPropertyValue = " ";  
            }  
            compares.append(oldPropertyValue).append("\"现属性值\"");  
            if(StringUtils.isEmpty(newPropertyValue+"")){  
                newPropertyValue = " ";  
            }  
            compares.append(newPropertyValue).append("\";");              
        }         
        return compares.toString();  
    }  

	
	
	/**
	 * 根据可能的名称及过滤字符模糊匹配类中的属性名
	 * @param cls Java Class
	 * @param String propertyName
	 * @param filterChar 允许属性字段中忽略的字符 如"-","_",可匹配多个字符
	 * @return
	 */
	public static String getClassFieldName(String[] fields,String propertyName,String... filterChar){
		if(propertyName==null || fields==null || fields.length==0){
			return null;
		}
		String fazzyName=null;
		if(filterChar!=null && filterChar.length>0){
			fazzyName=new String();
			for(String filter:filterChar){
				fazzyName=propertyName.replaceAll(filter, "");
			}
		}else{
			int index=java.util.Arrays.binarySearch(fields,propertyName);
			if(index>-1){
				return fields[index];
			}
		}
		if(fields!=null && fields.length>0){
			for(String field:fields){
				if(field.equalsIgnoreCase(propertyName)|| (fazzyName!=null && field.equalsIgnoreCase(fazzyName))){
					return field;
				}
			}
		}
		return null;
	}
	

	 

	/**
	 * 调用Getter方法.
	 */
	public static Object invokeGetter(Object obj, String propertyName) {
		String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);
		return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
	}
	
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			return null;
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw ExceptionUtils.convertExceptionToUnchecked(e);
		}
	}

	/**
	 * 调用Setter方法, 仅匹配方法名。
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value) {
		String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);
		invokeMethodByName(obj, setterMethodName, new Object[] { value });
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			return null;
			//throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	 
	/**
	 * 直接调用对象方法, 无视private/protected修饰符，
	 * 用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用.
	 * 只匹配函数名，如果有多个同名函数调用第一个。
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			return null;
//			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw ExceptionUtils.convertExceptionToUnchecked(e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object obj, final String fieldName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(fieldName, "fieldName can't be blank");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				makeAccessible(field);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 匹配函数名+参数类型。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 只匹配函数名。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * 改变private/protected的方法为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
				.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处
	 * 如无法找到, 返回Object.class.
	 * eg.
	 * public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return (Class<T>) Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if ((index >= params.length) || (index < 0)) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return (Class<T>) Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return (Class<T>) Object.class;
		}

		return (Class) params[index];
	}

	public static Class<?> getUserClass(Object instance) {
		Validate.notNull(instance, "Instance must not be null");
		Class clazz = instance.getClass();
		if ((clazz != null) && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superClass = clazz.getSuperclass();
			if ((superClass != null) && !Object.class.equals(superClass)) {
				return superClass;
			}
		}
		return clazz;

	}


	
	/**
	 * 得到类中存在的属性字段
	 * @param cls Java Class
	 * @return
	 */
	public static String[] getClassFields(Class cls){
		Field[] fields = cls.getDeclaredFields();
		String[] fieldArray=fields==null?null:new String[fields.length];
		for(int i=0;i<fields.length;i++){
			fieldArray[i]=fields[i].getName();
		}
		return fieldArray;
	}
	
	/**
	 * 
	 * 功能说明:获取字段的加强型方法，可以支持获取当前对象中自定义对象中的属性(支持两级)   
	 * @param: @param obj
	 * @param: @param fieldName
	 * @param: @return      
	 * @return: Field     
	 * @author:童贝 
	 * @date: 2015-6-4 下午3:29:01
	 */
	public static Field getAccessibleFieldEnhance(Object obj,String fieldName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(fieldName, "fieldName can't be blank");
		try{
			if(fieldName.indexOf(".")>-1){
				String[] fields=fieldName.split("\\.");
				Field field=getAccessibleField(obj, fields[0]);
				if(field!=null){
					Type[] params = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
					obj=((Class)params[0]).newInstance();
					fieldName=fields[1];
				}
			}
			for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
				try {
					Field field = superClass.getDeclaredField(fieldName);
					makeAccessible(field);
					return field;
				} catch (NoSuchFieldException e) {// NOSONAR
					// Field不在当前类定义,继续向上转型
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getAnnotation(Class clss,Class obejctClass){
		return  clss.getAnnotation(obejctClass);
	}
}