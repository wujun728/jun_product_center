package com.jun.plugin.query.sql.expr;

import com.jun.plugin.query.expr.Expression;

public class NoValueExpression extends SQLExpression{	
	public NoValueExpression(String field,com.jun.plugin.query.expr.Expression.Operator op){
		this.field=field;
		this.op=op;
	}

	@Override
	public String toSQL() {
		return field + " " +op.toString();
	}

	@Override
	public boolean hasEmptyValue() {
		return false;
	}
}
