package com.jun.plugin.common.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.util.Assert;

public class EntityManagerUtil {
	public static  <T> boolean isLoaded(EntityManager em,T entity, String attributeName) {
		Assert.notNull(entity,"The entityManager must not be null");
		Assert.hasText(attributeName,"The attributeName must not be empty");
		return em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(entity, attributeName);
	}
	public static <T> boolean isLoaded(EntityManager em,T entity) {
		Assert.notNull(entity,"The entityManager must not be null");
		return em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(entity);
	}
	public static <T> Object getIdentifier(EntityManager em,T entity) {
		Assert.notNull(entity,"The entityManager must not be null");
		return em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	public static Object getSingleResult(Query query){
		List<Object> list = query.getResultList();
		return list.isEmpty()?null:list.get(0);
	}
	
}
