package org.myframework.dao.id;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.util.Assert;

/**
 * 主键策略工厂：根据idGeneratorType，生成对应的主键值。
 * <ol>
 * <li>{@link SequenceGenerator }</li>
 * <li>{@link IdentityGenerator }</li>
 * <li>{@link UuidGenerator }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class IdUtil {

	private NamedParameterJdbcOperations jdbc ;

	public  Object generateValue(  String idGeneratorType , String sequence){
		IdGenerator idGenerator  ;
		if(idGeneratorType.equals(SequenceGenerator.SEQUENCE)){
			Assert.notNull(jdbc, "JDBC is empty");
			SequenceGenerator seq = new SequenceGenerator();
			seq.setJdbc(jdbc);
			seq.setSequence(sequence);
			idGenerator = seq ;
		}else if (idGeneratorType.equals(IdentityGenerator.IDENTITY)){
			idGenerator = new IdentityGenerator();
		}else{
			idGenerator = new UuidGenerator();
		}
		return idGenerator.generateValue();
	}

	public void setJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
}
