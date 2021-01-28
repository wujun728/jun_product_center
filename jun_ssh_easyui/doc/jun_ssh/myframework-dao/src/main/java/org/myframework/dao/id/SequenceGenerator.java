package org.myframework.dao.id;

import java.util.HashMap;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

/**
 *  需要数据库提供对序列号的支持，Oracle支持。
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
public class SequenceGenerator implements IdGenerator {
	public static final String 	SEQUENCE ="SEQUENCE" ;

	private String sequence ;
	private NamedParameterJdbcOperations jdbc ;
	@Override
	public Object generateValue( ) {
		String sql = "SELECT " + sequence + ".NEXTVAL FROM DUAL ";
		return jdbc.queryForObject(sql, new HashMap(), Long.class);
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public void setJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

}
