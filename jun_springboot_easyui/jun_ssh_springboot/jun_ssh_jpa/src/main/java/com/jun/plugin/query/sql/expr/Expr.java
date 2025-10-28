package com.jun.plugin.query.sql.expr;

import com.jun.plugin.query.expr.Expression.Operator;

/**
 * 用于构建sql拼接的表达式工厂，字段需要带上别名
 * 
 * @author klguang
 * 
 */

public class Expr {
	/**
	 * 等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression eq(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.EQ);
	}

	/**
	 * 不等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression ne(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.NE);
	}

	/**
	 * 模糊匹配
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression like(String fieldName, String value) {
		return new SimpleExpression(fieldName, value, Operator.LIKE);
	}

	/**
	 * 大于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression gt(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.GT);
	}

	/**
	 * 小于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression lt(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.LT);
	}

	/**
	 * 大于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression gte(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.GTE);
	}

	/**
	 * 小于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static SimpleExpression lte(String fieldName, Object value) {
		return new SimpleExpression(fieldName, value, Operator.LTE);
	}
	/**
	 * 为null
	 * @param fieldName
	 * @return
	 */
	public static NoValueExpression isNull(String fieldName){
		return  new NoValueExpression(fieldName, Operator.isNull);
	}
	/**
	 * 不为null
	 * @param fieldName
	 * @return
	 */
	public static NoValueExpression isNotNull(String fieldName){
		return new NoValueExpression(fieldName, Operator.isNotNull);
	}
	
	/**
	 * 之间 ，mysql包含边界
	 * @param fieldName
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static BetweenExpression between(String fieldName,Object lo, Object hi) {
		return new BetweenExpression(fieldName, lo, hi);
	}
	/**
	 * in ，常用来代替or
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static InExpression in(String fieldName,Object...value){
		return new InExpression(fieldName, value);
	}
	
}
