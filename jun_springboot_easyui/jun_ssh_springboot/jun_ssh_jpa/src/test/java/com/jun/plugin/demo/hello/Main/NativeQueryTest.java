package com.jun.plugin.demo.hello.Main;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;
import com.jun.plugin.common.utils.MyPrinter2;
import com.jun.plugin.demo.jpa.pojo.User;
import com.jun.plugin.query.SmartAliasToBean;

public class NativeQueryTest extends BaseTest{
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testInQuery(){
		String jql="from User u where u.account in(:accounts)";
		Query query=em.createQuery(jql, User.class);		
		query.setParameter("accounts", Arrays.asList(new String[]{"klguang","klguang1"}));
		MyPrinter2.print(query.getResultList());
	}
	@Test
	public void testInNativeQuery(){
		String jql="select * from user u where u.account in(:accounts)";
		Query query=em.createNativeQuery(jql, User.class);		
		query.setParameter("accounts", Arrays.asList(new String[]{"klguang","klguang1"}));
		MyPrinter2.print(query.getResultList());
	}
		
	@Test
	public void testQueryParamLike(){	
		String sql="select * from user where account like:account";
		Query query=em.createNativeQuery(sql, User.class);
		query.setParameter("account", "%a%");
		MyPrinter.print(query.getResultList());
	}
	@Test
	public void testQueryParam(){
		String sql="select * from user where userName=:userName";
		Query query=em.createNativeQuery(sql, User.class);
		query.setParameter("userName", "admin");
		MyPrinter.print(query.getResultList());
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate(){
		String sql="update user set userName=:userName where account=:account";
		Query query=em.createNativeQuery(sql);
		query.setParameter("userName", "KKKK");
		query.setParameter("account", "GGGG");
		query.executeUpdate();
	}
	
	
	
	
	@Test
	@Transactional
	@Rollback
	public void testBatchInsert(){
		String sql="insert into user(account,userName) values(:account,:userName)";
		long count=0;
		for(int i=0;i<10;i++){
			
			Query query=em.createNativeQuery(sql);
			query.setParameter("userName", "batch-"+i);
			query.setParameter("account", "batch-"+i);
			count+=query.executeUpdate();
		}
		System.out.println("count: "+count);
	}

	//@Transient注解的字段，存、取都会忽略，不做orm映射	
	@Test 
	public void testTransient(){
		String sql = "select u.*,u.account as tmep from user u";		
		Query query=em.createNativeQuery(sql,User.class);
		MyPrinter.print(query.getResultList());
	}

	@Test 
	public void testTransient2(){
		String sql = "select u.*,u.account as tmep from user u";		
		Query query=em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(new SmartAliasToBean(User.class));
		MyPrinter.print(query.getResultList());
	}
	
}
