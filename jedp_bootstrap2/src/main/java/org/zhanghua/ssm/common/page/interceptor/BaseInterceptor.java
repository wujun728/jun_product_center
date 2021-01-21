package org.zhanghua.ssm.common.page.interceptor;

import java.util.Properties;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.zhanghua.ssm.common.page.Page;
import org.zhanghua.ssm.common.page.dialect.Dialect;
import org.zhanghua.ssm.common.page.dialect.db.DB2Dialect;
import org.zhanghua.ssm.common.page.dialect.db.DerbyDialect;
import org.zhanghua.ssm.common.page.dialect.db.H2Dialect;
import org.zhanghua.ssm.common.page.dialect.db.HSQLDialect;
import org.zhanghua.ssm.common.page.dialect.db.MySQLDialect;
import org.zhanghua.ssm.common.page.dialect.db.OracleDialect;
import org.zhanghua.ssm.common.page.dialect.db.PostgreSQLDialect;
import org.zhanghua.ssm.common.page.dialect.db.SQLServer2005Dialect;
import org.zhanghua.ssm.common.page.dialect.db.SybaseDialect;
import org.zhanghua.ssm.common.utils.Reflections;


/**
 * Mybatis分页拦截器基类
 * @author Wujun
 *
 */
public abstract class BaseInterceptor implements Interceptor {

	protected Log log = LogFactory.getLog(this.getClass());

	protected static final String PAGE = "page";

	protected static final String DELEGATE = "delegate";

	protected static final String MAPPED_STATEMENT = "mappedStatement";

	protected Dialect dialect;

	/**
	 * 对参数进行转换和检查
	 * 
	 * @param parameterObject
	 *            参数对象
	 * @param page
	 *            分页对象
	 * @return 分页对象
	 * @throws NoSuchFieldException
	 *             无法找到参数
	 */
	protected static Page<?> convertParameter(Object parameterObject,
			Page<?> page) {
		try {
			if (parameterObject instanceof Page) {
				return (Page<?>) parameterObject;
			} else {
				return (Page<?>) Reflections.getFieldValue(
						parameterObject, PAGE);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 设置属性，支持自定义方言类和制定数据库的方式 <code>dialectClass</code>,自定义方言类。可以不配置这项
	 * <ode>dbms</ode> 数据库类型，插件支持的数据库 <code>sqlPattern</code> 需要拦截的SQL ID
	 * 
	 * @param p
	 *            属性
	 */
	protected void initProperties(Properties properties) {
		String dbType = properties.getProperty("dbType");
		log.debug("数据库：" + dbType);
		if ("db2".equals(dbType)) {
			dialect = new DB2Dialect();
		} else if ("derby".equals(dbType)) {
			dialect = new DerbyDialect();
		} else if ("h2".equals(dbType)) {
			dialect = new H2Dialect();
		} else if ("hsql".equals(dbType)) {
			dialect = new HSQLDialect();
		} else if ("mysql".equals(dbType)) {
			dialect = new MySQLDialect();
		} else if ("oracle".equals(dbType)) {
			dialect = new OracleDialect();
		} else if ("postgre".equals(dbType)) {
			dialect = new PostgreSQLDialect();
		} else if ("mssql".equals(dbType) || "sqlserver".equals(dbType)) {
			dialect = new SQLServer2005Dialect();
		} else if ("sybase".equals(dbType)) {
			dialect = new SybaseDialect();
		} else {
			throw new RuntimeException("mybatis dialect error.");
		}
	}
}
