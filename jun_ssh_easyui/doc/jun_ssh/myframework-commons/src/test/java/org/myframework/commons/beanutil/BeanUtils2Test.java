package org.myframework.commons.beanutil;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;
import org.myframework.commons.util.config.PropertiesParser;

public class BeanUtils2Test {
	@Test
	public void test04() throws Exception {

		// 创建Student对象

		Student st = new Student();

		// 使用CovertUtils注册创建一个日期格式转换器
 		ConvertUtils.register( new DateConverter(),   Date.class);
 		ConvertUtils.register( new StringConverter(),   String.class);
		// 给birth赋值
		BeanUtils2.setProperty(st, "birth", "1991-09-25 12:12:09");
		// 输出
		System.out.println( new java.sql.Date(st.getBirth().getTime()) );
		System.out.println(BeanUtils.getProperty(st, "birth"));
	}
	
	@Test
	public void testCopyProperties() throws Exception {
		Properties dbcpprops =	new PropertiesParser("application.properties")
				.getPropertyGroup("ST.", true);
		Student st = new Student();
//		BeanUtils2.register(new DateConverter(), Date.class);
//		BeanUtils2.register(new StringConverter(), String.class);
		BeanUtils2.copyProperties(st, dbcpprops);
		
		// 输出
//		System.out.println( new java.sql.Date(st.getBirth().getTime()) );
		
//		BeanUtils.setProperty(st, "birth", "1991-09-25 12:12:09");
		System.out.println( st );
	 
		
		//
	}

}
