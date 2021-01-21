package org.myframework.ioc;

import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class BeanRegistryConfig {

	public static void main(String[] args) throws Exception {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = (BeanFactory) bindViaCode(beanRegistry);
		FXNewsProvider newsProvider = (FXNewsProvider) container
				.getBean("djNewsProvider");
		newsProvider.getAndPersistNews();
		
		ResourceLoader resourceLoader = new ClassPathXmlApplicationContext( );
		Resource urlResource2 = resourceLoader.getResource("classpath:logback.xml");
		System.out.println(IOUtils.toString(urlResource2.getInputStream()));
	
		//============
		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage("menu.file", Locale.US, "File");
		messageSource.addMessage("menu.edit", Locale.US, "Edit");
		
		ResourceBundleMessageSource  messageSource2 = new ResourceBundleMessageSource();
		messageSource2.setBasenames(new String[]{"conf/messages"});// 从 classpath加载资源文件
		
		ReloadableResourceBundleMessageSource  messageSource3 = new   ReloadableResourceBundleMessageSource();
	}

	/**
	 * 通过编码的方式来进行bean的注入
	 * @param registry
	 * @return
	 */
	public static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
		AbstractBeanDefinition newsProvider = new RootBeanDefinition(
				FXNewsProvider.class);
		AbstractBeanDefinition newsListener = new RootBeanDefinition(
				DowJonesNewsListener.class);
		AbstractBeanDefinition newsPersister = new RootBeanDefinition(
				DowJonesNewsPersister.class);
		// 将bean定义注册到容器中
		registry.registerBeanDefinition("djNewsProvider", newsProvider);
		registry.registerBeanDefinition("djListener", newsListener);
		registry.registerBeanDefinition("djPersister", newsPersister);
		// 指定依赖关系
		// 1. 可以通过构造方法注入方式
		ConstructorArgumentValues argValues = new ConstructorArgumentValues();
		argValues.addIndexedArgumentValue(0, newsListener);
		argValues.addIndexedArgumentValue(1, newsPersister);
		newsProvider.setConstructorArgumentValues(argValues);
		// 2. 或者通过setter方法注入方式
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue(
				new PropertyValue("newsListener", newsListener));
		propertyValues.addPropertyValue(
				new PropertyValue("newPersistener", newsPersister));
		newsProvider.setPropertyValues(propertyValues);
		// 绑定完成
		return (BeanFactory) registry;
	}

}
