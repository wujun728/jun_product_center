package com.jun.plugin.query.sql;

import java.util.ArrayList;

import com.jun.plugin.query.expr.Expression;
import com.jun.plugin.query.sql.expr.SQLExpression;

public class Condition {
	ArrayList<SQLExpression> expressions = new ArrayList();
	boolean allIgnoreCase = Expression.DEFAULT_IGNORE_CASE;
	boolean allIgnoreEmpty = Expression.DEFAULT_IGNORE_EMPTY;

	public ArrayList<SQLExpression> getExpressions() {
		return expressions;
	}

	public Condition add(ArrayList<SQLExpression> exprs){
		return this.add(exprs.toArray(new SQLExpression[exprs.size()]));
	}
	
	public Condition add(SQLExpression... exprs) {
		for (SQLExpression expr : exprs) {
			expressions.add(expr);
		}
		return this;
	}

	public Condition add(SQLExpression expr) {
		expressions.add(expr);
		return this;
	}

	public Condition allIgCase() {
		this.allIgnoreCase = true;
		return this;
	}

	public Condition allIgEmpty() {
		this.allIgnoreEmpty = true;
		return this;
	}
	
	
	

//	public String toSQL() throws Exception {
//		return ArraysUtil.toString(expressions.toArray(), new ItemValue<SQLExpression>() {
//			@Override
//			public String of(SQLExpression item) throws NullPointerException {
//				if ((allIgnoreEmpty || item.isIgnoreEmpty()) && item.hasEmptyValue())
//					return null;
//				else {
//					return item.toSQL();
//				}
//			}
//		}, " AND ");
//	}


	public String toSQL() throws Exception {
		StringBuffer sql=new StringBuffer();
		for(SQLExpression expr:expressions){
			if ((allIgnoreEmpty || expr.isIgnoreEmpty()) && expr.hasEmptyValue())
				sql.append("");
			else {
				sql.append(" AND "+ expr.toSQL());
			}
		}
		return sql.toString();
	}
}
