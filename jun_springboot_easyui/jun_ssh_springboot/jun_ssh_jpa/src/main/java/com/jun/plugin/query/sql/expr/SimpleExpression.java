package com.jun.plugin.query.sql.expr;


public class SimpleExpression extends SQLExpression {
	private final Object value;

	public SimpleExpression(String field, Object value,
			com.jun.plugin.query.expr.Expression.Operator op) {
		this.field = field;
		this.value = value;
		this.op = op;
	}

	public SimpleExpression(String field, Object value,
			com.jun.plugin.query.expr.Expression.Operator op, boolean ignoreCase) {
		this.field = field;
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.op = op;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toSQL() throws NullPointerException {
		if(!ignoreEmpty&&value == null){
			if(op.equals(Operator.EQ)){
				return field + " IS NULL ";
			}else if(op.equals(Operator.NE)){
				return field + " IS NOT NULL ";
			}else{
				throw new NullPointerException("sql表达式值不能为空！:"+field);
			}
		}
		else if (value instanceof String)
			return field + " " + op.toString() + " '" + value + "'";
		else
			return field + " " + op.toString() + " " + value;
	}

	@Override
	public boolean hasEmptyValue() {
		return value==null||"".equals(value);
	}
}
