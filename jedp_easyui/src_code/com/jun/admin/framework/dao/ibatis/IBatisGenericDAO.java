package com.jun.admin.framework.dao.ibatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

//import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jun.admin.framework.dao.support.Page;

/**
 * IBatis Dao的泛型基类
 * <p/>
 * 继承于Spring的SqlMapClientDaoSupport,提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换.
 * 
 * @author Wujun
 * 
 * @see SqlMapClientDaoSupport
 */
@SuppressWarnings("unchecked")
public class IBatisGenericDAO extends SqlMapClientDaoSupport {
	public static final String POSTFIX_INSERT = ".insert";

	public static final String POSTFIX_UPDATE = ".update";

	public static final String POSTFIX_DELETE = ".delete";

	public static final String POSTFIX_SELECT = ".select";

	public static final String POSTFIX_COUNT = ".count";

	public static final String POSTFIX_QUERY = ".query";

	/**
	 * 根据ID获取对象
	 */
	public <T> T get(Class<T> entityClass, Serializable id) throws SQLException {
		T o = (T) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, POSTFIX_SELECT), id);
		return o;
	}

	/**
	 * 新增对象
	 */
	public long _insert(Object o) throws SQLException {
		Object oo = this.getSqlMapClientTemplate().insert(this.getStatementId(o.getClass(), POSTFIX_INSERT), o);
		if(oo == null){
			return -1;//有些表无需返回值，无序列，所以用-1代替
		}else{
			return (Long)oo;//有些表有返回值，如：序列，所以返回真实值
		}
	}
	
	/**
	 * 批量 新增对象
	 * @param list
	 * @throws SQLException
	 */
//	public <T> void _batchInsert(final List<T> list) throws SQLException {
//		// 执行回调
//		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//			// 实现回调接口
//			@Override
//			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
//				// 开始批处理
//				executor.startBatch();
//				for (T o : list) {
//					executor.insert(getStatementId(o.getClass(), POSTFIX_INSERT), o);
//				}
//				// 执行批处理
//				executor.executeBatch();
//
//				return null;
//			}
//		});
//	}

	/**
	 * 更新对象
	 */
	public int _update(Object o) throws SQLException {
		return (Integer)this.getSqlMapClientTemplate().update(this.getStatementId(o.getClass(), POSTFIX_UPDATE), o);

	}
	
	/**
	 * 批量 更新对象
	 * @param list
	 * @throws SQLException
	 */
//	public <T> void _batchUpdate(final List<T> list) throws SQLException {
//		// 执行回调
//		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//			// 实现回调接口
//			@Override
//			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
//				// 开始批处理
//				executor.startBatch();
//				for (T o : list) {
//					executor.update(getStatementId(o.getClass(), POSTFIX_UPDATE), o);
//				}
//				// 执行批处理
//				executor.executeBatch();
//
//				return null;
//			}
//		});
//	}
	
	/**
	 * 根据ID删除对象
	 */
	public <T> int deleteByPrimarykey(Class<T> entityClass, Serializable id) throws SQLException {
		return (Integer)this.getSqlMapClientTemplate().delete(
				this.getStatementId(entityClass, POSTFIX_DELETE), id);
	}
	
	/**
	 * 批量 删除对象
	 * @param list
	 * @throws SQLException
	 */
//	public <T> void _batchDelete(final List<T> list) throws SQLException {
//		// 执行回调
//		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//			// 实现回调接口
//			@Override
//			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
//				// 开始批处理
//				executor.startBatch();
//				for (T o : list) {
//					executor.delete(getStatementId(o.getClass(), POSTFIX_DELETE), o);
//				}
//				// 执行批处理
//				executor.executeBatch();
//
//				return null;
//			}
//		});
//	}

	// **************************************************************************
	// **************根据自定义的statementId执行SQL*********************************
	// **************************************************************************
	public <T> List<T> queryForList(Class<T> entityClass, String statementId, Object parameters)
			throws SQLException {
		return this.getSqlMapClientTemplate().queryForList(
				this.getStatementId(entityClass, statementId), parameters);
	}

	public <T> T queryForObject(Class<T> entityClass, String statementId, Object parameters)
			throws SQLException {
		return (T) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, statementId), parameters);
	}

	public int update(Class entityClass, String statementId, Object parameters)
			throws SQLException {
		return (Integer)this.getSqlMapClientTemplate().update(this.getStatementId(entityClass, statementId),
				parameters);
	}
	
	public long queryForCount(Class entityClass, String statementId, Object parameters) throws SQLException {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, statementId), parameters);
	}


	// **************************************************************************
	// **************以下是分页函数************************************************
	// **************************************************************************
	/**
	 * 分页查询函数一
	 * 
	 * @param entityClass
	 *            POJO/Entity对象
	 * @param parameterObject
	 *            object类型参数
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	public Page pagedQuery(Class entityClass, Object parameterObject, int start, int limit)
			throws SQLException {
		Assert.isTrue(start >= 0, "pageNo should start from 0");

		// 计算总数
		Integer totalCount = (Integer) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, POSTFIX_COUNT), parameterObject);

		// 如果没有数据则返回Empty Page
		Assert.notNull(totalCount, "totalCount Error");

		if (totalCount.intValue() == 0) {
			return new Page();
		}

		List<Object> list;
		int totalPageCount = 0; // 总共有多少页
		int startIndex = 0; // 起始(读取数据的开始序号 ；开始序号)

		// 如果pageSize小于0,则返回所有数据,等同于getAll
		if (limit > 0) {
			// 计算页数
			totalPageCount = (totalCount / limit);
			totalPageCount += (((totalCount % limit) > 0) ? 1 : 0);

			// 计算skip数量
			if (totalCount > start) {
				startIndex = start;
			} else {
				startIndex = (totalPageCount - 1) * limit;
			}

			/**
			 * iBatis在queryForList()中提供了为分页提供支持的方法。
			 * 记着skipResults是从0开始计算的，而maxResults就是取出的条数
			 * ，那么取前10条就是(0,10)，取11~20条就是(10,10)，以此类推。
			 */
			list = this.getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, POSTFIX_SELECT), parameterObject, startIndex,
					limit);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, POSTFIX_SELECT), parameterObject);
		}

		return new Page(startIndex, totalCount, limit, list);
	}

	/**
	 * 分页查询函数二
	 * 
	 * @param entityClass
	 *            POJO/Entity对象
	 * @param parameterObject
	 *            map类型参数
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	public Page pagedQuery(Class entityClass, Map parameterObject, int start, int limit)
			throws SQLException {

		Assert.isTrue(start >= 0, "pageNo should start from 0");

		// 计算总数
		Integer totalCount = (Integer) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, POSTFIX_COUNT), parameterObject);

		// 如果没有数据则返回Empty Page
		Assert.notNull(totalCount, "totalCount Error");

		if (totalCount.intValue() == 0) {
			return new Page();
		}

		List list;
		int totalPageCount = 0;
		int startIndex = 0;

		// 如果pageSize小于0,则返回所有数据,等同于getAll
		if (limit > 0) {

			// 计算页数
			totalPageCount = (totalCount / limit);
			totalPageCount += ((totalCount % limit) > 0) ? 1 : 0;

			// 计算skip数量
			if (totalCount > start) {
				startIndex = start;
			} else {
				startIndex = (totalPageCount - 1) * limit;
			}

			if (parameterObject == null)
				parameterObject = new HashMap();

			parameterObject.put("startIndex", startIndex); // 起始行
			parameterObject.put("endIndex", startIndex + limit); // 结束行
			parameterObject.put("limitIndex", limit); // 限制行

			list = this.getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, POSTFIX_QUERY), parameterObject);

		} else {
			list = this.getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, POSTFIX_QUERY), parameterObject);
		}

		return new Page(startIndex, totalCount, limit, list);
	}

	/**
	 * 分页查询函数三
	 * 
	 * @param entityClass
	 *            POJO/Entity对象
	 * @param parameterObject
	 *            map类型参数
	 * @param start
	 *            开始序号(注意：此处不是pageNo页号,从1开始.)
	 * @param limit
	 *            每页显示多少条数据
	 * @param countSqlId
	 *            求记录总数的sqlmap StatementName
	 * @param pageQuerySqlId
	 *            查询记录使用的sqlmap StatementName
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	public Page pagedQuery(Class entityClass, Map parameterObject, int start, int limit,
			String countSqlId, String pageQuerySqlId) throws SQLException {

		Assert.isTrue(start >= 0, "pageNo should start from 0");

		// 计算总数
		Integer totalCount = (Integer) this.getSqlMapClientTemplate().queryForObject(
				this.getStatementId(entityClass, countSqlId), parameterObject);

		// 如果没有数据则返回Empty Page
		Assert.notNull(totalCount, "totalCount Error");

		if (totalCount.intValue() == 0) {
			return new Page();
		}

		List list;
		int totalPageCount = 0;
		int startIndex = 0;

		// 如果pageSize小于0,则返回所有数据,等同于getAll
		if (limit > 0) {

			// 计算页数
			totalPageCount = (totalCount / limit);
			totalPageCount += ((totalCount % limit) > 0) ? 1 : 0;

			// 计算skip数量
			if (totalCount >= start) {
				startIndex = start;
			} else {
				startIndex = (totalPageCount - 1) * limit;
			}

			if (parameterObject == null)
				parameterObject = new HashMap();

			parameterObject.put("startIndex", startIndex); // 起始行
			parameterObject.put("endIndex", startIndex + limit); // 结束行
			parameterObject.put("limitIndex", limit); // 限制行

			list = this.getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, pageQuerySqlId), parameterObject);

		} else {
			list = this.getSqlMapClientTemplate().queryForList(
					this.getStatementId(entityClass, pageQuerySqlId), parameterObject);
		}

		return new Page(startIndex, totalCount, limit, list);
	}

	// **************************************************************************
	/**
	 * 获得sqlmap文件的getStatementId
	 * 
	 * @param entityClass
	 *            entity class
	 * @param suffix
	 *            suffix
	 * @return statement id
	 */
	private String getStatementId(Class entityClass, String suffix) {
		String className = entityClass.getName();
		String shortName = className.replace(entityClass.getPackage().getName() + ".", "");

		return shortName + suffix;
	}

}