package org.myframework.dao.query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.myframework.commons.util.ReflectUtils;

public class ShowCase <T>{

	EntityManagerFactory entityManagerFactory;

	public TypedQuery<T> getTypedQuery(String qlString) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery  typedQuery =  entityManager.createQuery(qlString,
				ReflectUtils.getClassGenricType(getClass()));
		return typedQuery;
	}
}
