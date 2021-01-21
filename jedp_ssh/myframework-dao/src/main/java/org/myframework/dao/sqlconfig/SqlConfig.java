package org.myframework.dao.sqlconfig;

import java.util.Set;
/**
 * SQL配置信息加载，获取，添加，删除
 * <ol>
 * <li>{@link XmlSqlConfig }</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public interface SqlConfig {

	public void reload(String dist) ;

	public SqlMapper getSqlMapper(String sqlKey );

	public void addSqlMapper(String sqlKey, SqlMapper sqlMapper);

	public void removeSqlMapper(String sqlKey);

	public Set<String> getKeyList() ;


}
