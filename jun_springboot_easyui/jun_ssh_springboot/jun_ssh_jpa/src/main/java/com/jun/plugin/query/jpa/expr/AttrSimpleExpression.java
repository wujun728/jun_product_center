package com.jun.plugin.query.jpa.expr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import com.jun.plugin.query.expr.Expression;

public class AttrSimpleExpression extends AttrExpression {
	private final Object value;

	public AttrSimpleExpression(Attribute attr, Object value, Expression.Operator op) {
		this.attr = attr;
		this.value = value;
		this.op = op;
	}

	public AttrSimpleExpression(Attribute attr, Object value, Expression.Operator op,
			boolean ignoreCase) {
		this.attr = attr;
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.op = op;
	}

	@Override
	public boolean hasEmptyValue() {
		return value == null || "".equals(value);
	}

	public Object getValue() {
		return value;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) throws NullPointerException {
		Path path = root.get(this.getAttr().getName());
		switch (op) {
		case EQ:
			return builder.equal(path, getValue());
		case NE:
			return builder.notEqual(path, getValue());
		case LIKE:
			return builder.like(path, getValue().toString());
		case CONTAIN:
			return builder.like(path, "%" + getValue().toString().trim() + "%");
		case LT:
			return builder.lessThan(path, (Comparable) getValue());
		case GT:
			return builder.greaterThan(path, (Comparable) getValue());
		case LTE:
			return builder.lessThanOrEqualTo(path, (Comparable) getValue());
		case GTE:
			return builder.greaterThanOrEqualTo(path, (Comparable) getValue());
		default:
			return null;
		}
	}
}
