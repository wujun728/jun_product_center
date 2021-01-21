package org.myframework.dao.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.myframework.dao.jpashowcase.dao.UserDao;
import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

public class NativeQueryShowcase {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(NativeQueryShowcase.class);

	EntityManagerFactory entityManagerFactory;

	UserDao userDao;

	NamedParameterJdbcTemplate jdbc;

	@Before
	public void init() {
		// TODO Auto-generated method stub
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"jpa/spring-jpa-data.xml");
		entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
		userDao = ctx.getBean("userDao", UserDao.class);
		jdbc = ctx.getBean(NamedParameterJdbcTemplate.class);
	}

	/**
	 * MUST @Transactional
	 */
	@Test
	public void updateUser() {
		userDao.update("delete from t_accountinfo where 1=0" ,null);
	}

	@Test
	public void getAll() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery("select * from  t_accountinfo ",
						AccountInfo.class);
		System.out.println(nativeQuery.getResultList().size());
	}

	@Test
	public void getPageInfo() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery("select * from  t_accountinfo ",
						AccountInfo.class);
		nativeQuery.setFirstResult(40);
		nativeQuery.setMaxResults(10);
		System.out.println(nativeQuery.getResultList());
	}

	@Test
	public void getInfoByParam() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(
						"select * from  t_accountinfo where accountId = :accountId ",
						AccountInfo.class);
		nativeQuery.setParameter("accountId",
				"2c90801f531659940153165999470000");
		System.out.println(nativeQuery.getResultList());
	}

	@Test
	public void getInfoByParam2() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(
						"select * from  t_accountinfo where accountId > :accountId ",
						AccountInfo.class);
		nativeQuery.setParameter("accountId", "2c90801f5316599401531659994700");
		nativeQuery.setParameter("accountId1",
				"2c90801f5316599401531659994700");
		System.out.println(nativeQuery.getResultList());
	}
	
	@Test
	public void getInfoByParam3() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("accountId", "2c90801f531659940153165999470000");
		param.put("accountId1", "2c90801f531659940153165999470000");
		String sql = "select * from  t_accountinfo where accountId = :accountId ";
		//解析SQL
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		//设置JDBC
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(sqlToUse, AccountInfo.class);
		for (int i = 0; i < params.length; i++) {
			nativeQuery.setParameter(i+1,params[i] );
		}
		//获取最终值
		System.out.println(nativeQuery.getResultList().size());
	}
	
	@Test
	public void getInfoByParam4() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("accountId", "2c90801f531659940153165999470000");
		param.put("accountId1", "2c90801f531659940153165999470000");
		String sql = "select * from  t_accountinfo where accountId = :accountId and  accountId = :accountId ";
		//设置JDBC
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(sql, AccountInfo.class);
		for (Map.Entry<String, ?> entry : param.entrySet()) {
			String paramName = entry.getKey();
			if (sql.indexOf(":" + paramName) > 0) {
				nativeQuery.setParameter(paramName, entry.getValue());
			}
		}
		//获取最终值
		System.out.println(nativeQuery.getResultList().size());
	}

	@Test
	public void parseSql() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("accountId", "2c90801f5316599401531659994700");
		param.put("accountId1", "2c90801f5316599401531659994700");
		String sql = "select * from  t_accountinfo where accountId > :accountId ";
//		System.out.println(jdbc.queryForList(
//				sql,
//				param));
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(param);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, new MapSqlParameterSource(param));
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		List<Object> paramList = Arrays.asList(params);
		logger.debug("sql :" + sql);
		logger.debug("parsedSql :" + parsedSql);
		logger.debug("sqlToUse :" + sqlToUse);
		logger.debug("params :" + paramList);
	}
	
	@Test
	public void parseSqlNullParam() {
		Map<String, String> param =  null;
		String sql = "select * from  t_accountinfo where accountId  ";
//		System.out.println(jdbc.queryForList(
//				sql,
//				param));
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		MapSqlParameterSource paramSource = new MapSqlParameterSource(null);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(
				parsedSql, new MapSqlParameterSource(null));
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql,
				paramSource, null);
		List<Object> paramList = Arrays.asList(params);
		logger.debug("sql :" + sql);
		logger.debug("parsedSql :" + parsedSql);
		logger.debug("sqlToUse :" + sqlToUse);
		logger.debug("params :" + paramList);
	}

	/**
	 * THROW TransactionRequiredException
	 */
	@Test
	public void update() {
		EntityManager createEntityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = createEntityManager.getTransaction();
		transaction.begin();
		System.out.println(transaction.isActive());
		Query nativeQuery = createEntityManager
				.createNativeQuery("delete * from  t_accountinfo where 1=0 ");
		transaction.commit();
		System.out.println(nativeQuery.executeUpdate());
	}

	/**
	 * 获取记录数
	 */
	@Test
	public void getCount() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery("select count(1) cnt from  t_accountinfo");
		Object singleResult = nativeQuery.getSingleResult();
		System.out.println(Number.class.cast(singleResult).longValue() );
		if (singleResult instanceof Number)
			System.out.println(((Number) singleResult).longValue());
	}

	/**
	 * 获取单条记录
	 */
	@Test
	public void NoResultException() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(
						"select accountId ,balance ,balance CONTENT_DESC  from  t_accountinfo where accountId = '1' ",
						AccountInfo.class);
		System.out.println(nativeQuery.getSingleResult());
	}

	/**
	 * 获取单条记录
	 */
	@Test
	public void getSingleResult() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(
						"select accountId ,balance ,balance CONTENT_DESC  from  t_accountinfo where accountId = '2c90801f531659940153165999470000' ",
						AccountInfo.class);
		System.out.println(nativeQuery.getSingleResult());
	}

	
	/**
	 * 获取单值
	 */
	@Test
	public void getSingleValue() {
		Query nativeQuery = entityManagerFactory.createEntityManager()
				.createNativeQuery(
						"select accountId   from  t_accountinfo where accountId = '2c90801f531659940153165999470000' ");
		System.out.println(nativeQuery.getSingleResult().getClass());
	}
}
