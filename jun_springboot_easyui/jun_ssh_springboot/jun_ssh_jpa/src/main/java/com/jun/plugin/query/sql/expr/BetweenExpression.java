package com.jun.plugin.query.sql.expr;


public class BetweenExpression extends SQLExpression {
	private Object lo;
	private Object hi;

	protected BetweenExpression(String field, Object lo, Object hi) {
		this.field = field;
		this.lo = lo;
		this.hi = hi;
		this.op = Operator.between;
	}

	@Override
	public String toSQL() throws NullPointerException {
		if(!ignoreEmpty&&(lo==null||hi==null))
			throw new NullPointerException("between and 表达式值不能为空:"+field);
		if (lo instanceof String)
			return field + " BETWEEN '" + lo + "' and '" + hi + "'";
		else
			return field + " BETWEEN " + lo + " and " + hi;
	}

	@Override
	public boolean hasEmptyValue() {
		return lo == null || "".equals(lo) || hi == null || "".equals(hi);
	}
}
