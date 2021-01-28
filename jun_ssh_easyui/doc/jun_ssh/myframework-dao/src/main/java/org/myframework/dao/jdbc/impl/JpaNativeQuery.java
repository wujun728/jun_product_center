package org.myframework.dao.jdbc.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.myframework.commons.cache.EhcacheCache;
import org.myframework.commons.cache.ICacheService;
import org.myframework.commons.util.EncryptUtils;
import org.myframework.commons.util.MapUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.jdbc.INativeQuery;
import org.myframework.dao.jdbc.dialect.Dialect;
import org.myframework.dao.sqlconfig.SqlMapper;
import org.myframework.dao.sqlconfig.SqlTemplate;
import org.myframework.dao.sqlconfig.impl.VelocitySqlTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

import com.alibaba.fastjson.JSON;

/**
 * 1.SQL可配置可热部署
 * <ol>
 * <li>{@see ISimpleCrud}
 * <li>{@see INativeQuery}
 * </ol>
 * 
 * @author Wujun
 * @since 1.0
 */
public class JpaNativeQuery implements INativeQuery    {
	private static Log log = LogFactory.getLog(JpaNativeQuery.class);

	private static int DEFAULT_PAGE_SIZE = 50;

	protected ICacheService dataCache = new EhcacheCache(getClass().getName());

	private SqlTemplate sqlTemplate = new VelocitySqlTemplate();

	/**
	 * SPRING 获取JPA entityManager的两种方式 LocalContainerEntityManagerFactoryBean
	 * entityManagerFactory =
	 * ctx.getBean(LocalContainerEntityManagerFactoryBean.class); entityManager
	 * = entityManagerFactory.nativeEntityManagerFactory.createEntityManager();
	 * 
	 * EntityManager entityManager = ctx.getBean(EntityManager.class);
	 * System.out.println(entityManager);
	 */
	private EntityManager entityManager;

	/**
	 *
	 * @param mapperId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAllList(String sqlKey,
			Map<String, Object> map) {
		String cacheKey = getCacheKey(sqlKey, map);
		SqlMapper sqlMapper = sqlTemplate.getSqlMapper(sqlKey);
		String sql = sqlTemplate.getSql(sqlKey, map);
		if (sqlMapper == null) {
			throw new RuntimeException("load sqlconfig error :" + sqlKey);
		}
		if (sqlMapper.isFlushCache()) {
			dataCache.delete(cacheKey);
		}
		if (sqlMapper.isUseCache()) {
			if (dataCache.get(cacheKey) != null) {
				log.debug("load from cacheKey : " + cacheKey);
				Object cacheVal = dataCache.get(cacheKey);
				if (cacheVal instanceof String) {
					log.debug("load from dataCache : " + dataCache);
					List<Map> blist = JSON.parseArray((String) cacheVal,
							Map.class);
					// 规避泛型检查
					List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
					for (Map map2 : blist) {
						cList.add(new MapUtils(map2).toSimpleMap());
					}
					return cList;
				}
				return (List<Map<String, Object>>) dataCache.get(cacheKey);
			} else {
				log.debug("reload   cacheKey : " + cacheKey);
				List<Map<String, Object>> rs = this.queryForList(sql, map);
				dataCache.put(cacheKey, rs);
				return rs;
			}
		} else {
			List<Map<String, Object>> rs = this.queryForList(sql, map);
			return rs;
		}
	}

	/**
	 * @param sqlKey
	 * @param map
	 * @param offset
	 * @param limit
	 * @return offset+1~offset+limit
	 */
	public List<Map<String, Object>> selectPageList(String sqlKey,
			Map<String, Object> map, int offset, int limit) {
		log.debug("offset " + offset + " limit " + limit);
		String sql = sqlTemplate.getSql(sqlKey, map);
		return selectPageListBySql(sql, map, offset, limit);
	}

	/**
	 * @param sqlKey
	 * @param map
	 * @param offset
	 * @param limit
	 * @return offset+1~offset+limit
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectLogicPageList(String sql,
			Map<String, Object> map, int offset, int limit) {
		log.debug("offset " + offset + " limit " + limit);
		log.debug(sql);
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>(
				limit);
		List<Map<String, Object>> ls = this.queryForList(sql, map);
		// offset+1~offset+limit
		for (int rownum = 0; rownum < ls.size()
				&& rownum <= offset + limit; rownum++) {
			log.debug(ls.get(rownum));
			if (rownum >= offset + 1 && rownum <= offset + limit) {
				log.debug("add ...." + ls.get(rownum));
				rsList.add(ls.get(rownum));
			}
		}
		return rsList;
	}

	/**
	 *
	 * @param sqlkey
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectPageList(String sqlkey,
			Map<String, Object> map) {
		MapUtils<String, Object> rm = new MapUtils<String, Object>(map);
		int offset = rm.getInt(Dialect.OFFSET);
		int limit = rm.hasKey(Dialect.LIMIT) ? rm.getInt(Dialect.LIMIT)
				: DEFAULT_PAGE_SIZE;
		return this.selectPageList(sqlkey, map, offset, limit);
	}

	/**
	 *
	 * @param mapperId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Long selectCount(String sqlKey, Map<String, Object> map) {
		String sql = String.format("select count(1) from ( %s )",
				sqlTemplate.getSql(sqlKey, map));
		return this.queryForSingleValue(sql, map, Number.class).longValue();
	}

	private String getCacheKey(String sqlKey, Map<String, Object> qryMap) {
		StringBuffer cacheKey = new StringBuffer(CACHE_KEY_PREFIX + sqlKey);
		SortedMap<String, Object> sortParams = new TreeMap<String, Object>(
				qryMap);
		List<String> paramList = new ArrayList<String>(4);
		for (String paramKey : sortParams.keySet()) {
			String value = StringUtils.asString(sortParams.get(paramKey));
			if ("sqlKey".equals(paramKey) || !StringUtils.hasLength(value)) {
				continue;
			}
			paramList.add(paramKey + "=" + value);
		}
		// 如果参数列表不为空，MD5编码后以_分割；
		if (!paramList.isEmpty()) {
			String str1 = StringUtils.collectionToDelimitedString(paramList,
					"&");
			String paramStr = EncryptUtils.encodeStr(str1).toUpperCase();
			cacheKey.append("_").append(paramStr);
		}
		return cacheKey.toString();
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> map) {
		Query query = getSQLQuery(sql, map);
		return query.getResultList();
	}

	@Override
	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> paramMap) {
		Query query = getSQLQuery(sql, paramMap);
		return (Map<String, Object>) query.getSingleResult();
	}

	@Override
	public <T> T queryForSingleValue(String sql, Map<String, Object> param,
			Class<T> requiredType) {
		Query query = getSQLQuery(sql, param);
		return requiredType.cast(query.getSingleResult());
	}

	private Query getSQLQuery(String sql, Map<String, ?> param) {
		//
	/*	ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);*/
		//
		/*Query query = entityManager.createNativeQuery(sqlToUse);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1,params[i] );
		}*/
		Query query = entityManager.createNativeQuery(sql);
		for (Map.Entry<String, ?> entry : param.entrySet()) {
			String paramName = entry.getKey();
			if (sql.indexOf(":" + paramName) > 0) {
				query.setParameter(paramName, entry.getValue());
			}
		}
		query.unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query;
	}

	public void setDataCache(ICacheService dataCache) {
		log.debug("setDataCache" + dataCache);
		this.dataCache = dataCache;
	}

	public void setSqlTemplate(SqlTemplate sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}

	public JpaNativeQuery() {
		super();
	}

	@Override
	public List<Map<String, Object>> selectPageListBySql(String sql,
			Map<String, Object> map, int offset, int limit) {
		Query query = getSQLQuery(sql, map);
		query.setFirstResult(offset);// 设置查询结果的开始记录数
		query.setMaxResults(offset + limit);// 设查询结果的结束记录数 查询记录数1~~10的数据
		List<Map<String, Object>> rows = query.getResultList();
		return rows;
	}

	@Override
	public Long selectCountBySql(String sql, Map<String, Object> map) {
		return this.queryForSingleValue(sql, map, Number.class).longValue();
	}

	
}
