package com.erp.configuration;


import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class HibernateConfig {

	Logger log = LoggerFactory.getLogger(getClass());

	public String current_session_context_class = "org.springframework.orm.hibernate5.SpringSessionContext";

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() throws SQLException {
		log.info("执行sessionFactoryBean()");
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("com.erp.model","com.erp.dao","com.erp.dao.impl");// dao和entity的公共包
		Properties properties = new Properties();


//	properties.setProperty("hibernate.username", "root");
//	properties.setProperty("hibernate.password", "root");
//	properties.setProperty("hibernate.url", "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC");
//	properties.setProperty("hibernate.driver_class", "com.mysql.cj.jdbc.Driver"); 
//		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.current_session_context_class", current_session_context_class);
		sessionFactoryBean.setHibernateProperties(properties);

		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws SQLException {
		log.info("执行transactionManager()"); 
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setDataSource(dataSource);
		transactionManager.setSessionFactory(sessionFactoryBean().getObject()); // 注入sessionFactory
		transactionManager.setHibernateManagedSession(true); // 获取当前session
		transactionManager.setValidateExistingTransaction(true); // 开启事务校验
		transactionManager.setRollbackOnCommitFailure(true);
		transactionManager.setAutodetectDataSource(true);
		transactionManager.setGlobalRollbackOnParticipationFailure(true);

		return transactionManager;
	}

	@Bean 
	public TransactionInterceptor transactionInterceptors() throws Exception {
		TransactionInterceptor transInterceptor = new TransactionInterceptor();
		transInterceptor.setTransactionManager(transactionManager());
		Properties props = new Properties();
		props.setProperty("save*", "PROPAGATION_REQUIRED");
		props.setProperty("update*", "PROPAGATION_REQUIRED");
		props.setProperty("delete*", "PROPAGATION_REQUIRED");
		props.setProperty("find*", "PROPAGATION_REQUIRED");
		props.setProperty("get*", "PROPAGATION_REQUIRED");
		props.setProperty("load*", "PROPAGATION_REQUIRED");
		props.setProperty("add*", "PROPAGATION_REQUIRED");
		props.setProperty("execute*", "PROPAGATION_REQUIRED");
		props.setProperty("merge*", "PROPAGATION_REQUIRED");
		transInterceptor.setTransactionAttributes(props);

		return transInterceptor;

	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() throws Exception {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("*ServiceImpl","*DaoImpl");
		beanNameAutoProxyCreator.setInterceptorNames("transactionInterceptor");
		return beanNameAutoProxyCreator;
	}
}

 