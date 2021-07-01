package com.du.lin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext application;
	
	@Override
	public void setApplicationContext(ApplicationContext application) throws BeansException {
		SpringContextHolder.application = application;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		AssertApplication();
		return (T)application.getBean(name);
	} 

	public static <T> T getBean(Class<T> clazz){
		AssertApplication();
		return (T)application.getBean(clazz);
	}

	public void setApplicaiont(ApplicationContext applicaiont) {
		SpringContextHolder.application = applicaiont;
	}



	private static void AssertApplication(){
		if (application == null) {
			throw new RuntimeException("SpringContextHolder中application为空，请检查是否注入");
		}
	}
	
}
