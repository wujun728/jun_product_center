package com.jun.plugin.common.dataaccess;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseDAOWrap<T,ID extends Serializable> {
	@Autowired
	protected BaseRepository<T, ID> baseRepository;
	@PersistenceContext
	protected EntityManager em;
	
	protected Class<T> entityClass;
	protected String entityName;
	protected String tableName;
	
	public BaseDAOWrap() {		
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.entityClass= (Class<T>) params[0];
		this.entityName=entityClass.getSimpleName();
	}
	public String tableName(){
		return getTableName(em, entityClass);
	}
	
	/**
	 * 获取表名
	 * 
	 * @param em
	 * @param entityClass
	 * @return
	 */
	public static <T> String getTableName(EntityManager em, Class<T> entityClass) {
		/*
		 * Check if the specified class is present in the metamodel. Throws
		 * IllegalArgumentException if not.
		 */
		Metamodel meta = em.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);

		// Check whether @Table annotation is present on the class.
		Table t = entityClass.getAnnotation(Table.class);

		String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
		return tableName;
	}
	
	public List queryList(String queryName,Sort sort, Object conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page queryPage(String queryName,Pageable pageable, Object conditions) {
		// TODO Auto-generated method stub
		return null;
	}
}
