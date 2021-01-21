package org.myframework.dao.orm;

import static org.springframework.data.jpa.repository.query.QueryUtils.COUNT_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.myframework.commons.util.ReflectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractEntityQuery<T>
		implements EntityQuery<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<T> domainClass = ReflectUtils.getClassGenricType(getClass());
	
	@Override
	public List<T> selectAllList(String sql, Map<String, Object> param ){
		Query query = getSQLEntityQuery(sql, param);
		return query.getResultList();
	}
	
	@Override
	public List<T> selectPageList(String sql, Map<String, Object> param,
			int offset, int limit) {
		Query query = getSQLEntityQuery(sql, param);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public T selectOne(String sql, Map<String, Object> param) {
		Query query = getSQLEntityQuery(sql, param);
		return (T) query.getSingleResult();
	}
	
	@Override
	public Long selectCount(String sql, Map<String, Object> param) {
		sql = String.format("select count(1) from ( %s ) a",sql);
		Query query = getSqlQuery(sql, param) ;
		return Number.class.cast(query.getSingleResult()).longValue();
	}
	
	@Override
	public Page<T> findPageList(String sql, Map<String, Object> param ,Pageable pageable) {
		List<T> content =  selectPageList(sql,param,pageable.getOffset(),pageable.getPageSize());
		long total =  selectCount(sql,param).intValue();
		return new PageImpl<T>(content, pageable, total);
	}
	
	
	protected TypedQuery<T> getTypedQuery(String qlString, Map<String, ?> param) {
		//
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(qlString);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		//
		TypedQuery<T>  typedQuery = (TypedQuery<T>) entityManager.createQuery(sqlToUse,domainClass);
		for (int i = 0; i < params.length; i++) {
			typedQuery.setParameter(i+1,params[i] );
		}
		return typedQuery;
	}
	
	protected Query getSQLEntityQuery(String sql, Map<String, ?> param) {
		//
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		//
		Query query = entityManager.createNativeQuery(sqlToUse,domainClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1,params[i] );
		}
		return query;
	}
	
	protected Query getSqlQuery(String sql, Map<String, ?> param) {
		//
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		//
		Query query = entityManager.createNativeQuery(sqlToUse);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1,params[i] );
		}
		return query;
	}

	
	@Override
	@Transactional
	public int update(String sql, Map<String, Object> param) {
		return getSqlQuery(sql,param).executeUpdate();
	}
	
	@Transactional
	public <S extends T> S save(S entity) {
		JpaEntityInformation<T,?> entityInformation =JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
		if (entityInformation.isNew(entity)) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}
	
 

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(java.lang.Object)
	 */
	@Transactional
	public <S extends T> S saveAndFlush(S entity) {

		S result = save(entity);
		flush();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#save(java.lang.Iterable)
	 */
	@Transactional
	public <S extends T> List<S> save(Iterable<S> entities) {

		List<S> result = new ArrayList<S>();

		if (entities == null) {
			return result;
		}

		for (S entity : entities) {
			result.add(save(entity));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#flush()
	 */
	@Transactional
	public void flush() {
		entityManager.flush();
	}

	public Page<T> findAll(Pageable pageable) {
		if (null == pageable) {
			return new PageImpl<T>(findAll());
		}
		TypedQuery<T> query = getTypedQuery(" from " + domainClass.getSimpleName(), null);
		return pageable == null ? new PageImpl<T>(query.getResultList()) : readPage(query, pageable );
	}
	
	public List<T> findAll() {
		return getTypedQuery(" from " + domainClass.getSimpleName() ,  null).getResultList();
	}
	
	public long count() {
		return entityManager.createQuery(getCountQueryString(), Long.class).getSingleResult();
	}
	

	private String getCountQueryString() {
		String countQuery = String.format(COUNT_QUERY_STRING, "x", "%s");
		return getQueryString(countQuery,   domainClass.getSimpleName());
	}
	
	protected Page<T> readPage(TypedQuery<T> query, Pageable pageable ) {
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		Long total = count();
		List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
		return new PageImpl<T>(content, pageable, total);
	}
}