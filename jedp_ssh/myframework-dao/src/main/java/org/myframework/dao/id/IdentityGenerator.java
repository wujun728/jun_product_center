package org.myframework.dao.id;

/**
 * 需要数据库提供对自增长字段的支持，SQL Server、MySQL、DB2、Derby等支持。
 * <ol>
 * <li>{@link  }</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class IdentityGenerator implements IdGenerator  {
	public static final String 	IDENTITY ="IDENTITY" ;
	@Override
	public Object generateValue( ) {
		return null;
	}

}
