package com.erp.util;

import org.hibernate.dialect.Oracle9iDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.sql.ANSIJoinFragment;
import org.hibernate.sql.JoinFragment;
import org.hibernate.type.StandardBasicTypes;

/**
 * 修复Oracle Dialect BUG
 * 
 * java.util.Date类型，在使用[HOUR]函数的时候，报错信息[ORA-30076: 对析出来源无效的析出字段]
 * 
 * @author 孙宇
 * 
 */
public class Oracle10gDialect extends Oracle9iDialect {

	public Oracle10gDialect() {
		super();
		registerFunction("second", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_char(?1,'SS')"));
		registerFunction("minute", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_char(?1,'MI')"));
		registerFunction("hour", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "to_char(?1,'HH24')"));
	}

	public JoinFragment createOuterJoinFragment() {
		return new ANSIJoinFragment();
	}

}
