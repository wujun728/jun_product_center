package com.jun.plugin.common.dataaccess;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jun.plugin.common.dataaccess.entity.BaseEntity;
import com.jun.plugin.common.dataaccess.entity.EntityUtil;
import com.jun.plugin.query.jpa.Criteria;
import com.jun.plugin.query.jpa.expr.AttrExpression;
import com.jun.plugin.query.sql.Condition;
import com.jun.plugin.query.sql.SQLQuery;
import com.jun.plugin.query.sql.expr.SQLExpression;

public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	/** 更新忽略属性 */
	private static final String[] UPDATE_IGNORE_PROPERTIES = 
		new String[] {
			BaseEntity.CREATE_TIME_PROPERTY_NAME, 
			BaseEntity.MODIFY_TIME_PROPERTY_NAME,
			BaseEntity.VERSION_PROPERTY_NAME 
		};

	protected EntityManager em;
	protected Class<T> entityClass;
	protected String entityName;
	protected String tableName;
	protected final JpaEntityInformation<T, ?> entityInformation;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.entityClass = entityInformation.getJavaType();
		this.entityName = entityInformation.getEntityName();
		this.tableName = EntityUtil.getTableName(em, entityClass);
		this.entityInformation = entityInformation;
	}

	// public BaseDAOImpl(Class<T> domainClass, EntityManager em) {
	// super(domainClass, em);
	// this.em = em;
	// }



	public EntityManager getEntityManager(){
		return em;
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getTableName() {
		return tableName;
	}

	public JpaEntityInformation<T, ?> getEntityInformation() {
		return entityInformation;
	}

	@Transactional
	@Override
	public boolean delete(ID id, boolean checkRelation) {
		// TODO Auto-generated method stub
		if(checkRelation){
			boolean hasRelationData=EntityUtil.hasRelatedData(entityClass, id, em);
			if(hasRelationData){
				return false;
			}
		}
		try {
			delete(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return false;
	}
	
	@Override
	public void deleteIds(Iterable<ID> ids) {
		// TODO Auto-generated method stub
		for(ID id : ids){
			delete(id);
		}
	}
	
	@Override
	public T getById(ID id) {
		// TODO Auto-generated method stub
		T entity=findOne(id);
		em.refresh(entity);
		return entity;
	}
	
	
	
	/**
	 * 更新对象，忽略实体的创建时间、修改时间、version
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T update(T entity) {
		Assert.notNull(entity);
		ID id=(ID) EntityManagerUtil.getIdentifier(em, entity);
		Assert.notNull(id);

		T persistant = findOne(id);
		if (persistant != null) {
			copyProperties(entity, persistant, UPDATE_IGNORE_PROPERTIES);
		}
		
		return saveAndFlush(persistant);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T update(T entity, String... ignoreProperties) {
		Assert.notNull(entity);
		ID id=(ID) EntityManagerUtil.getIdentifier(em, entity);
		Assert.notNull(id);

		T persistant = findOne((ID) entityInformation.getId(entity));
		if (persistant != null) {
			copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
		}		
		return saveAndFlush(persistant);
	}

	@Override
	public Page<T> findPage(T example, Pageable pageable) {
		return findAll(Example.of(example), pageable);
	}

	@Override
	public List<T> findList(T example, Sort sort) {
		return findAll(Example.of(example), sort);
	}

	@Override
	public List<T> findList(T example) {
		return findAll(Example.of(example));
	}

	@Override
	public long count(T example) {
		return count(Example.of(example));
	}

	@Override
	public T getOne(T example) {
		List<T> list = findList(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Page<T> findPage(Pageable pageable, AttrExpression... exprs) {
		return findAll(new Criteria<T>().add(exprs), pageable);
	}

	@Override
	public List<T> findList(Sort sort, AttrExpression... exprs) {
		return findAll(new Criteria<T>().add(exprs), sort);
	}

	@Override
	public List<T> findList(AttrExpression... exprs) {
		return findAll(new Criteria<T>().add(exprs));
	}

	@Override
	public T getOne(AttrExpression... exprs) {
		List<T> list = findList(exprs);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public long count(AttrExpression... exprs) {
		return count(new Criteria<T>().add(exprs));
	}
	
	@Override
	public long executeUpdate(String updateName, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> random(int n) {
		String idFieldName=entityInformation.getIdAttribute().getName();
		String sql="SELECT * FROM "+tableName+" WHERE " + getRandomSQLPart(n);
		return em.createNativeQuery(sql, entityClass).getResultList();
	}

	@Override
	public List<T> random(int n, SQLExpression... exprs) {
		SQLQuery<T> sqlQuery=new SQLQuery<T>(em, entityClass);
		sqlQuery.select("*").from(tableName)
		.where(new Condition().add(exprs)).addPart("AND " + getRandomSQLPart(n));		
		return sqlQuery.list(null);
	}

	private String getRandomSQLPart(int n){
		String idFieldName=entityInformation.getIdAttribute().getName();
		return idFieldName +" >= "
				+"((SELECT MAX("+idFieldName+") FROM "+tableName+")-(SELECT MIN("+idFieldName+") FROM "+tableName+")) * RAND() + (SELECT MIN("+idFieldName+") FROM "+tableName+") "
				+"LIMIT " +n;
	}
	

	/**
	 * 拷贝对象属性，忽略ignoreProperties
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param ignoreProperties
	 *            忽略属性
	 */
	protected void copyProperties(T source, T target, String... ignoreProperties) {
		Assert.notNull(source);
		Assert.notNull(target);

		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(target);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String propertyName = propertyDescriptor.getName();
			Method readMethod = propertyDescriptor.getReadMethod();
			Method writeMethod = propertyDescriptor.getWriteMethod();
			if (ArrayUtils.contains(ignoreProperties, propertyName) || readMethod == null
					|| writeMethod == null
					|| !EntityManagerUtil.isLoaded(em, source, propertyName)) {
				continue;
			}
			try {
				Object sourceValue = readMethod.invoke(source);
				writeMethod.invoke(target, sourceValue);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
