package org.myframework.dao.sqlconfig.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.myframework.dao.exception.SqlConfigDuplicateException;
import org.myframework.dao.sqlconfig.SqlConfig;
import org.myframework.dao.sqlconfig.SqlMapper;

/**
 * 说明：SQL配置基类，可根据项目实际需要进行扩展，
 * 比如将SQL配置信息存储到数据库中，从数据库中读取SQL信息
 * public class DBSqlConfig extends BaseSqlConfig {
 * 	 String Sql = "SELECT isUseCache ,isFlushCache, SqlKey ,SqlCode FROM SQL_CONFIG";
 *   public DBSqlConfig(){
 *		loadConfig();
 *	}
 *	void loadConfig(){
 *    //读取数据库
 *    for (final Object result : results) {
			SqlMapper sqlMapper =  new SqlMapper (){
			 // 相关字段赋值

			} ;
			this.addSqlMapper(sqlMapper.getSqlKey(), sqlMapper);
	  }
 *  }
 *
 * }
 * <ol>
 * <li>{@link XmlSqlConfig 默认子类 }</li>
 * <li>{@link SqlMapper SQL配置实体接口 }</li>
 * <li>{@link SqlConfig   }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public abstract class BaseSqlConfig implements SqlConfig {

	protected Map<String, SqlMapper> sqlConfigCache = new HashMap<String, SqlMapper>();

	public BaseSqlConfig() {
		super();
	}

	public SqlMapper getSqlMapper(String sqlKey) {
		return sqlConfigCache.get(sqlKey);
	}

	public void addSqlMapper(String sqlKey, SqlMapper sqlMapper) {
		check(sqlKey);
		sqlConfigCache.put(sqlKey, sqlMapper);
	}

	protected void check(String sqlKey) {
		if (sqlConfigCache.containsKey(sqlKey)) {
			throw new SqlConfigDuplicateException("Duplicate sqlKey : "
					+ sqlKey);
		}
	}

	public void removeSqlMapper(String sqlKey) {
		sqlConfigCache.remove(sqlKey);
	}

	public Set<String> getKeyList() {
		return sqlConfigCache.keySet();
	}

	@Override
	public String toString() {
		return sqlConfigCache.toString();
	}

}