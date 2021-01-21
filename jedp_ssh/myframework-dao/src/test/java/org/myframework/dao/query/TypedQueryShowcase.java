package org.myframework.dao.query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypedQueryShowcase extends ShowCase<AccountInfo> {
	@Before
	public void init() {
		// TODO Auto-generated method stub
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
	}

	@Test
	public void getPageInfo() {
		TypedQuery<AccountInfo> query = getTypedQuery(" from  AccountInfo a where accountId = :accountId " )  ;
//		query.setFirstResult(40);
//		query.setMaxResults(10);
		query.setParameter("accountId", "2c90801f531659940153165999470000");
		System.out.println(query.getResultList());
	}
	
	@Test
	public void getPageInfo1() {
		TypedQuery<AccountInfo> query = getTypedQuery(" from  AccountInfo a where accountId = ? " )  ;
//		query.setFirstResult(40);
//		query.setMaxResults(10);
		query.setParameter(1, "2c90801f531659940153165999470000");
		System.out.println(query.getResultList());
	}
	
	@Test
	public void getCount() {
		Query TypedQuery = getTypedQuery("select count(*)   from  AccountInfo");
		Object singleResult = TypedQuery.getSingleResult();
		System.out.println(Number.class.cast(singleResult).longValue() );
		if (singleResult instanceof Number)
			System.out.println(((Number) singleResult).longValue());
	}

	 
}
