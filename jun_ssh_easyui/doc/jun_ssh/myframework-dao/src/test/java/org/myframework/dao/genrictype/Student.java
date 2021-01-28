package org.myframework.dao.genrictype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.myframework.commons.util.ClassUtils;
import org.myframework.commons.util.ReflectUtils;

public class Student extends Person<Student> {
	public static void main(String[] args) {
		Student student=new Student();
		Class class1 = student.getClass();
		Class class2 = class1.getSuperclass();
		Type type = class1.getGenericSuperclass();
		
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p=(ParameterizedType)type;  
		
		//getActualTypeArguments获取参数化类型的数组，泛型可能有多个  
		Class c=(Class) p.getActualTypeArguments()[0];  
		
		
		System.out.println(ReflectUtils.getClassGenricType(class1));  
	}
}
