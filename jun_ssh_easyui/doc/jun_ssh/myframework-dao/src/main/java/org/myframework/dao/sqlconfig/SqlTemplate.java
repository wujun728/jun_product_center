package org.myframework.dao.sqlconfig;

import java.util.Map;

/**
 * sql生成模板 ：生成SQL ，获取SQL其他关联信息
 * @author Wujun
 *
 */
public interface SqlTemplate {

	/**
	 * 根据存储的SQL文本及参数信息，生成最终SQL
	 * @param sqlKey  SQL文本对应的键名
	 * @param context 参数信息
	 * @return
	 */
	public String getSql(String sqlKey, Map<String, Object> context);
	
	/**
	 * 获取最终会被数据库指向的SQL
	 * @param sqlKey
	 * @param context
	 * @return
	 */
	public String getExecuteSql(String sqlKey, Map<String, Object> context);

	/**
	 * 根据sqlKey获取SQL其他关联信息，如：SQL查询结果是否需要缓存
	 * @param sqlKeySQL文本对应的键名
	 * @return
	 */
	public SqlMapper getSqlMapper(String sqlKey);

}
