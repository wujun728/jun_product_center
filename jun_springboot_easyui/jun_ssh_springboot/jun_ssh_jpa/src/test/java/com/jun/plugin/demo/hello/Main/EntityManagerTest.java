package com.jun.plugin.demo.hello.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;
import com.jun.plugin.common.utils.MyPrinter2;
import com.jun.plugin.demo.jpa.pojo.CopyOfUser;
import com.jun.plugin.demo.jpa.pojo.CopyOfUser2;
import com.jun.plugin.demo.jpa.pojo.User;
import com.jun.plugin.demo.jpa.pojo.User_;

public class EntityManagerTest extends BaseTest {

	@PersistenceContext
	EntityManager em;

	@Test
	public void testJPACriteriaQuery() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userToot = criteriaQuery.from(User.class);
		Predicate condition = criteriaBuilder.equal(userToot.get(User_.account), "klguang");
		criteriaQuery.where(condition);
		Query query = em.createQuery(criteriaQuery);
		MyPrinter2.print(query.getResultList());
	}

	@Test
	public void testTransient() {

		String sql = "select u.*  from user u inner join logrole l on u.logRoleId=l.logRoleId";
		Query query = em.createNativeQuery(sql, User.class);
		MyPrinter.print(query.getResultList());
	}

	/**
	 * aliasToBean：bean种的属性必须包含所有alias，可以有其他属性
	 */
	@Test
	public void result2bean() {
		 String sql="select userId,userName from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.aliasToBean(CopyOfUser.class));
		MyPrinter2.print(query.getResultList());
	}

	@Test
	public void result2bean1(){
		 String sql="select userId,userName from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.aliasToBean(CopyOfUser2.class));
		
		thrown.expect(javax.persistence.PersistenceException.class);
		MyPrinter2.print(query.getResultList());
		
	}
	
	@Test(expected=java.lang.ClassCastException.class)
	public void result2bean2() {
		String sql = "select * from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.aliasToBean(CopyOfUser.class));
		MyPrinter2.print(query.getResultList());
	}


	@Test
	public void result2bean_addScalar() {
		String sql = "select userId,account,userName from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).addScalar("userId", StandardBasicTypes.INTEGER).setResultTransformer(Transformers.aliasToBean(CopyOfUser.class));
		MyPrinter2.print(query.getResultList());
	}
	
	@Test
	public void customTransformers() {
		String sql = "select userId as user_id,userName as user_name from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(new ResultTransformer() {
			NamingStrategy namingStrategy = ImprovedNamingStrategy.INSTANCE;

			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				// TODO Auto-generated method stub
				Map result = new HashMap(tuple.length);
				for (int i = 0; i < tuple.length; i++) {
					String alias = aliases[i];
					if (alias != null) {
						System.out.println(namingStrategy.propertyToColumnName(alias));
						result.put(alias.toUpperCase(), tuple[i]);
					}
				}
				return result;
			}

			@Override
			public List transformList(List collection) {
				// TODO Auto-generated method stub
				return collection;
			}
		});
		MyPrinter2.print(query.getResultList());
	}

	@Test
	public void result2map() {
		String sql = "select account,userName from user";
		Query query = em.createNativeQuery(sql);
		query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		MyPrinter2.print(query.getResultList());
	}

	@Test
	public void result2map2() {
		//无法通过
		//String sql = "select account,userName from user";
		//Query query = em.createNativeQuery(sql, java.util.HashMap.class);
		//MyPrinter2.print(query.getResultList());
	}
	// ----------------------------jpa 一级缓存测试

	@Test
	public void testL1Cache() {
		// 没有缓存？ejb环境下才有
		MyPrinter.print(em.find(User.class, 1));
		Assert.assertFalse(em.find(User.class, 1) == em.find(User.class, 1));
	}

	@Test
	public void testL1Cache2() {
		// 有缓存
		ApplicationContext context = new ClassPathXmlApplicationContext("app-jpa.xml");
		EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
		EntityManager em = entityManagerFactory.createEntityManager();
		Assert.assertTrue(em.find(User.class, 1) == em.find(User.class, 1));
	}
}
