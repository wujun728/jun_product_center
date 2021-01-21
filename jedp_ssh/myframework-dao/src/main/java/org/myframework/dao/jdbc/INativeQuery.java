package org.myframework.dao.jdbc;

import java.util.List;
import java.util.Map;


/**
 * 本地化JDBC分页查询，分两种类型：直接传入SQL,或者根据SQL对应的KEY;
 * <ol>
 * <li>{@link  }
 *
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public  interface INativeQuery {
	
	public final static String CACHE_KEY_PREFIX = INativeQuery.class.getSimpleName()+":";

	/**
	 *
	 * @param sqlKey
	 * @param map
	 * @return
	 */
	public abstract List<Map<String, Object>> selectAllList(String sqlKey,
			Map<String, Object> map);

	/**
	 * @param sqlKey
	 * @param map
	 * @param offset
	 * @param limit
	 * @return offset+1~offset+limit
	 */
	public abstract List<Map<String, Object>> selectPageList(String sqlKey,
			Map<String, Object> map, int offset, int limit);

	/**
	 * @param sqlKey
	 * @param map
	 * @param offset
	 * @param limit
	 * @return offset+1~offset+limit
	 */
	public abstract List<Map<String, Object>> selectPageListBySql(String sql,
			Map<String, Object> map, int offset, int limit) ;
	
	
	/**
	 * @param sqlKey
	 * @param map
	 * @return
	 */
	public abstract List<Map<String, Object>> selectPageList(String sqlKey,
			Map<String, Object> map);

	/**
	 *
	 * @param sqlKey
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public abstract Long selectCount(String sqlKey, Map<String, Object> map);

	/**
	 *
	 * @param mapperId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public abstract Long selectCountBySql(String sql, Map<String, Object> map);

	/**
	 * select * from table where user_id = :userId
	 *
	 * @param sql
	 * @param map
	 */
	public abstract List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> map);

	/**
	 * select * from table where user_id = :userId
	 *
	 * @param sql
	 * @param map
	 */
	public abstract Map<String, Object> queryForMap(String sql,
			Map<String, Object> map);

	/**
	 *
	 * EXAMPLE: sql:select COUNT(1) cnt from table where user_id = :userId map:
	 * { userId : "1"} requiredType : Long.class return Long value of cnt
	 *
	 * @param sql
	 * @param map
	 * @param requiredType
	 * @return
	 */
	public abstract <T> T queryForSingleValue(String sql, Map<String, Object> map,
			Class<T> requiredType);
	
	

}