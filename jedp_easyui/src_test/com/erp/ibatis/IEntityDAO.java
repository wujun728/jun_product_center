package com.erp.ibatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jun.admin.Page;

public interface IEntityDAO<T> {
	/**
	 * 根据主键查找对象
	 * 
	 * @param id
	 *            主键值
	 * 
	 * @return 对象实体
	 */
	T findByPrimarykey(Serializable id) throws SQLException;

	/**
	 * 新增对象到数据库
	 * 
	 * @param o
	 * 
	 */
	long insert(T o) throws SQLException;

	/**
	 * 批量新增对象到数据库
	 * 
	 * @param list
	 * @throws SQLException
	 */
	void batchInsert(List<T> list) throws SQLException;

	/**
	 * 更新对象实体到数据库
	 * 
	 * @param o
	 * @return 如果主键是自动生成的，则其返回值可以通过<selectKey>标签来设置
	 *         如果不通过<selectKey>标签来设置，则返回值为空！
	 * 
	 */
	int update(T o) throws SQLException;

	/**
	 * 批量更新对象实体到数据库
	 * 
	 * @param list
	 * @throws SQLException
	 */
	void batchUpdate(List<T> list) throws SQLException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param id
	 *            主键值
	 * @return 返回值则是DELETE语句所影响的行数
	 * 
	 */
	int deleteByPrimarykey(Serializable id) throws SQLException;

	/**
	 * 批量 删除对象
	 * 
	 * @param list
	 * @throws SQLException
	 */
	void batchDelete(List<T> list) throws SQLException;

	/**
	 * 更新对象信息
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 * @return 返回值则是执行UPDATE语句所影响的行数
	 */
	int update(String statementId, Object parameters) throws SQLException;

	/**
	 * sql查询单个对象
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 * @return 查询结果
	 */
	T queryForObject(String statementId, Object parameters) throws SQLException;

	/**
	 * sql查询列表
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 * @return 查询结果
	 */
	List<T> queryForList(String statementId, Object parameters) throws SQLException;

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
	Page pagedQuery(Object parameterObject, int start, int limit) throws SQLException;

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
	Page pagedQuery(Map parameterObject, int start, int limit) throws SQLException;

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
	Page pagedQuery(Map parameterObject, int start, int limit, String countSqlId,
			String pageQuerySqlId) throws SQLException;

}
