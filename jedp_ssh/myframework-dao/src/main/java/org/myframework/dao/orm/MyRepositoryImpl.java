package org.myframework.dao.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public class MyRepositoryImpl<T, ID extends Serializable>
		extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

	private final EntityManager entityManager;

	public MyRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		entityManager = em;
	}

	public MyRepositoryImpl(
			final JpaEntityInformation<T, ?> entityInformation,
			final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	public String sharedCustomMethod() {
		System.out.println("hello sharedCustomMethod");
		return "hello sharedCustomMethod";
		// implementation goes here
	}
	

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
		TypedQuery<T>  typedQuery = (TypedQuery<T>) entityManager.createQuery(sqlToUse,getDomainClass());
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
		Query query = entityManager.createNativeQuery(sqlToUse,getDomainClass());
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
	 
 
}
