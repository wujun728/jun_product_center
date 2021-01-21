package org.myframework.dao.jdbc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.myframework.dao.sqlconfig.SqlConfig;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

/**
 *
 * <ol>从sqlvelocity配置文件中读取SQL，并执行增删改SQL
 * <li>{@link SqlConfig }</li>
 * <li>{@link NamedParameterJdbcOperations }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月15日
 *
 */
public class ConfigedJdbc extends JdbcNativeQuery{

	public int update(String sqlkey, Map<String, Object> paramMap) {
		String sql = sqlTemplate.getSql(sqlkey,paramMap);
		fillMap(sql,paramMap);
		return jdbc.update(sql, paramMap);
	}
	
	public int update(String sqlkey ) {
		return update(sqlkey,new HashMap<String, Object>() );
	}
	
	public int[] batchUpdate(String sqlkey, Map<String, ?>[] batchValues) {
		String sql = sqlTemplate.getSql(sqlkey,new HashMap<String, Object>());
		fillList(sql,batchValues);
		return jdbc.batchUpdate(sql, batchValues);
	}
	
	/**
	 * 直接使用NamedParameterJdbcTemplate
	 * @return
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(getJdbcTemplate());
	}
	
	/**
	 * 直接使用JdbcTemplate
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource()) ;
	}
	
	/**
	 * 直接传SQL，不建议使用；推荐使用sqlkey方式 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	@Deprecated
	public int updateBySql(String sql, Map<String, Object> paramMap) {
		fillMap(sql,paramMap);
		return jdbc.update(sql, paramMap);
	}
	
	@Deprecated
	public int updateBySql(String sql ) {
		return jdbc.update(sql,new HashMap<String, Object>() );
	}
	
	@Deprecated
	public int[] batchUpdateBySql(String sql, Map<String, ?>[] batchValues) {
		return jdbc.batchUpdate(sql, batchValues);
	}

	private void fillMap(String sql, Map<String, ?> paramMap) {
		List<String> paramlist = getParameterNames(sql ) ;
		for(String param :paramlist) {
			if(!paramMap.containsKey(param)) {
				paramMap.put(param, null);
			}
		}
	}

	private void fillList(String sql, Map<String, ?>[] batchValues) {
		for (int i = 0; i < batchValues.length; i++) {
			Map<String, ?> map = batchValues[i];
			fillMap(sql,map);
		}
	}

	private static List<String> getParameterNames(final String sql) {
		Assert.notNull(sql, "SQL must not be null");
		String sqlToUse = sql;
		List<String> parameterList = new ArrayList<String>();
		char[] statement = sql.toCharArray();
		int escapes = 0;
		int i = 0;
		while (i < statement.length) {
			int skipToPosition = i;
			while (i < statement.length) {
				skipToPosition = skipCommentsAndQuotes(statement, i);
				if (i == skipToPosition) {
					break;
				}
				else {
					i = skipToPosition;
				}
			}
			if (i >= statement.length) {
				break;
			}
			char c = statement[i];
			if (c == ':' || c == '&') {
				int j = i + 1;
				if (j < statement.length && statement[j] == ':' && c == ':') {
					// Postgres-style "::" casting operator - to be skipped.
					i = i + 2;
					continue;
				}
				String parameter = null;
				if (j < statement.length && c == ':' && statement[j] == '{') {
					// :{x} style parameter
					while (j < statement.length && !('}' == statement[j])) {
						j++;
						if (':' == statement[j] || '{' == statement[j]) {
							throw new InvalidDataAccessApiUsageException("Parameter name contains invalid character '" +
									statement[j] + "' at position " + i + " in statement: " + sql);
						}
					}
					if (j >= statement.length) {
						throw new InvalidDataAccessApiUsageException(
								"Non-terminated named parameter declaration at position " + i + " in statement: " + sql);
					}
					if (j - i > 3) {
						parameter = sql.substring(i + 2, j);
						parameterList.add(parameter);
					}
					j++;
				}
				else {
					while (j < statement.length && !isParameterSeparator(statement[j])) {
						j++;
					}
					if (j - i > 1) {
						parameter = sql.substring(i + 1, j);
						parameterList.add(parameter);
					}
				}
				i = j - 1;
			}
			else {
				if (c == '\\') {
					int j = i + 1;
					if (j < statement.length && statement[j] == ':') {
						// this is an escaped : and should be skipped
						sqlToUse = sqlToUse.substring(0, i - escapes) + sqlToUse.substring(i - escapes + 1);
						escapes++;
						i = i + 2;
						continue;
					}
				}
			}
			i++;
		}

		return parameterList ;
	}


	/**
	 * Set of characters that qualify as comment or quotes starting characters.
	 */
	private static final String[] START_SKIP =
			new String[] {"'", "\"", "--", "/*"};

	/**
	 * Set of characters that at are the corresponding comment or quotes ending characters.
	 */
	private static final String[] STOP_SKIP =
			new String[] {"'", "\"", "\n", "*/"};

	/**
	 * Skip over comments and quoted names present in an SQL statement
	 * @param statement character array containing SQL statement
	 * @param position current position of statement
	 * @return next position to process after any comments or quotes are skipped
	 */
	private static int skipCommentsAndQuotes(char[] statement, int position) {
		for (int i = 0; i < START_SKIP.length; i++) {
			if (statement[position] == START_SKIP[i].charAt(0)) {
				boolean match = true;
				for (int j = 1; j < START_SKIP[i].length(); j++) {
					if (!(statement[position + j] == START_SKIP[i].charAt(j))) {
						match = false;
						break;
					}
				}
				if (match) {
					int offset = START_SKIP[i].length();
					for (int m = position + offset; m < statement.length; m++) {
						if (statement[m] == STOP_SKIP[i].charAt(0)) {
							boolean endMatch = true;
							int endPos = m;
							for (int n = 1; n < STOP_SKIP[i].length(); n++) {
								if (m + n >= statement.length) {
									// last comment not closed properly
									return statement.length;
								}
								if (!(statement[m + n] == STOP_SKIP[i].charAt(n))) {
									endMatch = false;
									break;
								}
								endPos = m + n;
							}
							if (endMatch) {
								// found character sequence ending comment or quote
								return endPos + 1;
							}
						}
					}
					// character sequence ending comment or quote not found
					return statement.length;
				}

			}
		}
		return position;
	}



	/**
	 * Set of characters that qualify as parameter separators,
	 * indicating that a parameter name in a SQL String has ended.
	 */
	private static final char[] PARAMETER_SEPARATORS =
			new char[] {'"', '\'', ':', '&', ',', ';', '(', ')', '|', '=', '+', '-', '*', '%', '/', '\\', '<', '>', '^'};

	/**
	 * Determine whether a parameter name ends at the current position,
	 * that is, whether the given character qualifies as a separator.
	 */
	private static boolean isParameterSeparator(char c) {
		if (Character.isWhitespace(c)) {
			return true;
		}
		for (char separator : PARAMETER_SEPARATORS) {
			if (c == separator) {
				return true;
			}
		}
		return false;
	}

}
