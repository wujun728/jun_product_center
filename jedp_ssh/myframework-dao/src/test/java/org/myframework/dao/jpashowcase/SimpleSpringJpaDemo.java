package org.myframework.dao.jpashowcase;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.myframework.dao.jpashowcase.dao.UserDao;
import org.myframework.dao.jpashowcase.dao.UserDao2;
import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class SimpleSpringJpaDemo {

	public SimpleSpringJpaDemo() {
		 
	}

	/**
	 * 测试MyRepository扩展后的效果
	 */
	@Test
	public void testMyRepository() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		UserDao userDao = ctx.getBean(UserDao.class);
		userDao.sharedCustomMethod();
		Page<AccountInfo> page = userDao.findPageList(
				"select * from t_accountinfo a ", null, new PageRequest(0, 10));
		List<AccountInfo> list = page.getContent();

		System.out.println("content:" + list);
		System.out.println("current page :" + page.getNumber());
		System.out.println("size  per page  :" + page.getSize());
		System.out.println("total size  :" + page.getTotalElements());
		System.out
				.println("size of current page :" + page.getNumberOfElements());
		System.out.println("total page :" + page.getTotalPages());
	}

	/**
	 * 测试JPA扩展方法 printPlus
	 */
	@Test
	public void testUserDaoPlus() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		UserDao userDao = ctx.getBean(UserDao.class);
		userDao.printPlus();
	}
	
	/**
	 * 测试JPA扩展方法  
	 */
	@Test
	public void testUserDao2() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		UserDao2 userDao = ctx.getBean(UserDao2.class);
		userDao.findPageList("select * from t_accountinfo a ", null, new PageRequest(0, 10));
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		UserDao userDao = ctx.getBean(UserDao.class);
		System.out.println(ctx.getBean("userDao").getClass());
		// System.out.println(ctx.getBean("UserDaoImpl2") instanceof
		// UserDaoImpl2 );
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setBalance(111);
		userDao.save(accountInfo);

		//
		PageRequest pageRequest = new PageRequest(1, 1);
		Page<AccountInfo> page = userDao.findAll(pageRequest);
		List<AccountInfo> list = page.getContent();
		System.out.println(list);
		System.out.println(page.getTotalPages());

		// jpa common自带方法
		page = userDao.findAll(new PageRequest(2, 1));
		list = page.getContent();
		System.out.println(list);
		System.out.println(page.getTotalPages());

		// 自定义扩展方法
		page = userDao.findPageList("select * from t_accountinfo a ", null,
				new PageRequest(0, 10));
		list = page.getContent();

		System.out.println("content:" + list);
		System.out.println("current page :" + page.getNumber());
		System.out.println("size  per page  :" + page.getSize());
		System.out.println("total size  :" + page.getTotalElements());
		System.out
				.println("size of current page :" + page.getNumberOfElements());
		System.out.println("total page :" + page.getTotalPages());

		//
		System.out.println(" all  :"
				+ userDao.selectAllList("SELECT * FROM t_accountinfo", null));
	}

}
