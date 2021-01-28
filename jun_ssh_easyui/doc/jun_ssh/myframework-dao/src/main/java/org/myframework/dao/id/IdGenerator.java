package org.myframework.dao.id;


/**
 * 数据库主键生成策略
 * <ol>
 * <li>{@link IdentityGenerator }</li>
 * <li>{@link IdentityGenerator }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public interface IdGenerator {
	public static final String 	SEQUENCE_KEY ="sequence" ;
	public static final String 	ID_TYPE_KEY ="idType" ;
	Object generateValue( );
}
