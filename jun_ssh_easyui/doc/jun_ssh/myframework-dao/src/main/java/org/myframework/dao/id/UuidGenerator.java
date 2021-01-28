package org.myframework.dao.id;

import java.util.UUID;

/**
 * 使用UUID来生成主键
 * <ol>
 * <li>{@link IdGenerator }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class UuidGenerator implements IdGenerator {
	public static final String 	UUID_ ="UUID" ;

	@Override
	public Object generateValue( ) {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
