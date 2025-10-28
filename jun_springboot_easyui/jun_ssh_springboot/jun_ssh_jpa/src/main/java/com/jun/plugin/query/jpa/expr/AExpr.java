package com.jun.plugin.query.jpa.expr;

import javax.persistence.metamodel.Attribute;

import com.jun.plugin.query.expr.Expression.Operator;


public class AExpr {
	/**
	 * 等于
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression eq(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.EQ);
	}

	/**
	 * 不等于
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression ne(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.NE);
	}

	/**
	 * 模糊匹配,注意必须包含"%"通配符
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression like(Attribute attr, String value) {
		return new AttrSimpleExpression(attr, value, Operator.LIKE);
	}

	
	/**
	 * 包含字符串，即like查询，在两边都有"%"通配符
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression contain(Attribute attr, String value) {
		return new AttrSimpleExpression(attr, value, Operator.CONTAIN);
	}
	
	/**
	 * 大于
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression gt(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.GT);
	}

	/**
	 * 小于
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression lt(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.LT);
	}

	/**
	 * 小于等于 
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression lte(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.LTE);
	}

	/**
	 * 大于等于
	 * 
	 * @param attr
	 * @param value
	 * @param ignoreEmpty
	 * @return
	 */
	public static AttrSimpleExpression gte(Attribute attr, Object value) {
		return new AttrSimpleExpression(attr, value, Operator.GTE);
	}

	/**
	 * 为null
	 * 
	 * @param attr
	 * @return
	 */
	public static AttrNoValueExpression isNull(Attribute attr) {
		return new AttrNoValueExpression(attr, Operator.isNull);
	}

	/**
	 * 不为null
	 * 
	 * @param attr
	 * @return
	 */
	public static AttrNoValueExpression isNotNull(Attribute attr) {
		return new AttrNoValueExpression(attr, Operator.isNotNull);
	}

	/**
	 * 之间 ，mysql包含边界
	 * 
	 * @param <X>
	 * @param <Y>
	 * @param attr
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static <X, Y> AttrBetweenExpression between(Attribute<X, Y> attr, Object lo,
			Object hi) {
		return new AttrBetweenExpression(attr, lo, hi);
	}

	/**
	 * in ，常用来代替or
	 * 
	 * @param attr
	 * @param value
	 * @return
	 */
	public static AttrInExpression in(Attribute attr, Object... value) {
		return new AttrInExpression(attr, value);
	}
}
