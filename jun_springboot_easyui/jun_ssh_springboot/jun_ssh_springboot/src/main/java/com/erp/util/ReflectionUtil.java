package com.erp.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * 
 * @author 孙宇
 * 
 */
public class ReflectionUtil {

	public static void main(String[] args) {

	}

	/**
	 * 通过属性名称获得属性值
	 * 
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static Object getValue(Object object, String propertyName) {
		try {
			Class<? extends Object> clazz = object.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			Object o = getMethod.invoke(object);// 执行get方法返回一个Object
			return o;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据对象获得所有字段的值
	 * 
	 * @param object
	 */
	public static void method(Object object) {
		try {
			Class<? extends Object> clazz = object.getClass();
			Field[] fields = object.getClass().getDeclaredFields();// 获得属性
			for (Field field : fields) {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				Object o = getMethod.invoke(object);// 执行get方法返回一个Object
				System.out.println(o);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
