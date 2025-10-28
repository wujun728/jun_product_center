package com.jun.plugin.query.sql.expr;

import com.jun.plugin.query.ArraysUtil;
import com.jun.plugin.query.ItemValue;
import com.jun.plugin.query.expr.Expression;

public class InExpression extends SQLExpression {
	Object[] value;

	public InExpression(String fieldName, Object...value) {
		this.field = fieldName;
		this.value = value;
		this.op = Operator.IN;
	}

	@Override
	public String toSQL() throws NullPointerException{
		if(!ignoreEmpty&&hasEmptyValue())
			throw new NullPointerException("in表达式值不能为空："+field);
		String sql = field + " IN(";
		try {
			sql += ArraysUtil.toString(value, new ItemValue<Object>() {
				@Override
				public String of(Object item) throws NullPointerException {
					if (ignoreEmpty && (item == null || "".equals(item)))
						return null;
					else if (item == null)
						return "NULL";
					else if (item instanceof String)
						return "'" + item + "'";
					else
						return String.valueOf(item);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql += ")";
		return sql;
	}

	@Override
	public boolean hasEmptyValue() {
		if(value==null||"".equals(value))
			return true;
		for (Object obj : value) {
			if (obj == null || "".equals(obj))
				return true;
		}
		return false;
	}
}
