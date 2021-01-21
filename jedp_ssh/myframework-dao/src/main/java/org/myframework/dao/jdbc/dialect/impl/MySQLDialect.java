package org.myframework.dao.jdbc.dialect.impl;

import java.util.Map;

import org.myframework.dao.jdbc.dialect.Dialect;

/**
 * 获取基于MYSQL数据库的分页语句
 * <ol>
 * <li>{@link  Dialect}</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class MySQLDialect extends Dialect {


	public String getPageSql(String sql, int offset, int limit,
			Map<String, Object> map) {
		map.put("offset", offset);
		map.put("limit", limit);
		return (new StringBuffer(sql.length() + 20)).append(sql)
				.append(" limit  :offset , :limit ").toString();
	}
}
