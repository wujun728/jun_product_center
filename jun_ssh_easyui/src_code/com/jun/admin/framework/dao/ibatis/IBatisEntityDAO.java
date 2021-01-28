package com.jun.admin.framework.dao.ibatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jun.admin.framework.GenericsUtils;
import com.jun.admin.framework.dao.support.Page;

/**
 * 负责为单个Entity 提供CRUD操作的IBatis DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 */

public class IBatisEntityDAO<T> extends IBatisGenericDAO implements IEntityDAO<T> {

	/**
	 * DAO所管理的Entity类型.
	 */
	protected Class<T> entityClass;

	protected String primaryKeyName;

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public IBatisEntityDAO() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 根据ID获取对象.
	 */
	public T findByPrimarykey(Serializable id) throws SQLException {
		return this.get(getEntityClass(), id);
	}

	public String getIdName(Class clazz) {
		return "id";
	}

	@Override
	public void batchDelete(List<T> list) throws SQLException {
//		super._batchDelete(list);
	}
	
	/**
	 * 根据ID移除对象.
	 */
	public int deleteByPrimarykey(Serializable id) throws SQLException {
		return this.deleteByPrimarykey(getEntityClass(), id);
	}

	public long insert(T o) throws SQLException {
		return super._insert(o);
	}
	
	@Override
	public void batchInsert(List<T> list) throws SQLException {
//		super._batchInsert(list);
	}

	public List<T> queryForList(String statementId, Object parameters) throws SQLException {
		return super.queryForList(getEntityClass(), statementId, parameters);
	}

	public T queryForObject(String statementId, Object parameters) throws SQLException {
		return super.queryForObject(getEntityClass(), statementId, parameters);
	}

	public int update(String statementId, Object parameters) throws SQLException {
		return super.update(getEntityClass(), statementId, parameters);
	}

	public int update(T o) throws SQLException {
		return super._update(o);
	}
	
	@Override
	public void batchUpdate(List<T> list) throws SQLException {
//		super._batchUpdate(list);
	}

	public long queryForCount(String statementId, Object parameters) throws SQLException {
		return super.queryForCount(getEntityClass(), statementId, parameters);
	}
	
	// **************************************************************************
	// **************以下是分页函数************************************************
	// **************************************************************************

	/**
	 * 分页查询一
	 * 
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @return
	 */
	public Page pagedQuery(Object parameterObject, int start, int limit) throws SQLException {
		return this.pagedQuery(getEntityClass(), parameterObject, start, limit);
	}

	/**
	 * 分页查询二
	 * 
	 * @param parameterObject
	 *            map类型参数
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @return
	 */
	public Page pagedQuery(Map parameterObject, int start, int limit) throws SQLException {
		return this.pagedQuery(getEntityClass(), parameterObject, start, limit);
	}

	/**
	 * 分页查询三
	 * 
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @param parameterObject
	 *            map类型参数
	 * @param countSqlId
	 *            求记录总数的sqlmap StatementName
	 * @param pageQuerySqlId
	 *            查询记录使用的sqlmap StatementName
	 * @return
	 */
	public Page pagedQuery(Map parameterObject, int start, int limit, String countSqlId,
			String pageQuerySqlId) throws SQLException {
		if (StringUtils.isNotBlank(pageQuerySqlId))
			return this.pagedQuery(getEntityClass(), parameterObject, start, limit, countSqlId,
					pageQuerySqlId);
		else {
			return this.pagedQuery(getEntityClass(), parameterObject, start, limit);
		}
	}

	// **************************************************************************
	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}

	/**
	 * 取得entityClass.
	 * <p/>
	 * JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

}
