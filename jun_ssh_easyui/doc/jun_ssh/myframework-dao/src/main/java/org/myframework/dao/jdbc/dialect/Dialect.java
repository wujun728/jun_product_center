package org.myframework.dao.jdbc.dialect;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 数据库方言父类，获取总数查询SQL，以及判断数据库数据类型功能
 * <ol>
 * <li>{@link OracleDialect  }</li>
 * <li>{@link MySQLDialect  }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public abstract class Dialect {

	public static String ROWS_COUNT = "ROWS_COUNT" ;

	public static String OFFSET = "OFFSET" ;

	public static String LIMIT = "LIMIT" ;


	protected Log log = LogFactory.getLog(getClass());

	public abstract String getPageSql(String sql, int offset, int limit ,Map<String, Object> map);

    public String getCountString(String sql )
    {
    	StringBuffer countSelect = new StringBuffer(sql.length() + 100);
		countSelect.append("select count(1) "+ROWS_COUNT+" from ( ");
		countSelect.append(sql);
		countSelect.append(" ) a  ");
		return countSelect.toString();
    }

    public boolean isNumber(String dbType){
    	return StringUtils.contains(dbType, "INT")||StringUtils.contains(dbType, "NUMBER") ;
    }

    public boolean isDate(String dbType){
    	return StringUtils.contains(dbType, "DATE")||StringUtils.contains(dbType, "TIME") ;
    }


}