package org.myframework.dao.sqlconfig.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.myframework.dao.sqlconfig.SqlConfig;
import org.myframework.dao.sqlconfig.SqlMapper;
import org.myframework.dao.sqlconfig.SqlTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

public abstract class BaseSqlTemplate implements SqlTemplate,  InitializingBean{
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(BaseSqlTemplate.class);

	protected SqlConfig sqlConfig   ;

	public SqlConfig getSqlConfig() {
		return sqlConfig;
	}

	public void setSqlConfig(SqlConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}


	public  SqlMapper getSqlMapper(String sqlKey ){
		return sqlConfig.getSqlMapper(sqlKey) ;
	}

	@Override
	public void afterPropertiesSet() {
		if (getSqlConfig() == null) {
			throw new IllegalArgumentException("Property 'sqlConfig' is required");
		}
	}
	protected abstract String getParsedSql(String sqlKey, Map<String, Object> map);

	@Override
	public String getSql(String sqlKey, Map<String, Object> map) {
		String parsedSql = getParsedSql(sqlKey,map) ;
		return sqlConvert(parsedSql,map);
	}
	
	@Override
	public String getExecuteSql(String sqlKey, Map<String, Object> map) {
		String sql = getSql(sqlKey, map);
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(map);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		List<Object> paramList = Arrays.asList(params);
		String executeSql = getSql(sqlToUse, paramList);
		logger.debug("sql :" + sql);
		logger.debug("parsedSql :" + parsedSql);
		logger.debug("sqlToUse :" + sqlToUse);
		logger.debug("params :" + paramList);
		logger.debug("executeSql :" + executeSql);
		return executeSql;
	}

	private String getSql(String sql, List<Object> lstParam) {
		for (int i = 0; i < lstParam.size(); i++) {
			Object value = lstParam.get(i);
			if (value instanceof Integer || value instanceof Double
					|| value instanceof Float || value instanceof Long) {
				sql = StringUtils.replaceOnce(sql, "?", value.toString());
			} else if (value instanceof String) {
				sql = StringUtils.replaceOnce(sql, "?", "'" + value.toString()
						+ "'");
			} else {
				sql = StringUtils.replaceOnce(sql, "?",
						"'" + String.valueOf(value) + "'");
			}
		}
		return sql;
	}

	private String sqlConvert(String sql, Map  qryMap) {
		// replace #param to value
		if (qryMap != null && !qryMap.isEmpty()) {
			List<String> params = getReplaceParameterList(sql);
			for (String param : params) {
				sql = StringUtils.replaceOnce(sql, "#" + param,
						ObjectUtils.toString(qryMap.get(param)));
			}
		}
		return sql;
	}

	/**
	 * sql ( select * from table where user_id = #userId ) #userId will be
	 * replaced into list as param
	 *
	 * @param sql
	 * @return
	 */
	private List<String> getReplaceParameterList(String sql) {
		String regex = "\\#(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		List<String> params = new ArrayList<String>();
		while (matcher.find())
			params.add(matcher.group(1));
		return params;
	}

}
