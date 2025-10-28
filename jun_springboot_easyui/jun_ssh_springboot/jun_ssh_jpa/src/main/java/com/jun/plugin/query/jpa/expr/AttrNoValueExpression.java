package com.jun.plugin.query.jpa.expr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import com.jun.plugin.query.expr.Expression;


public class AttrNoValueExpression extends AttrExpression{
	public AttrNoValueExpression(Attribute attr,Expression.Operator op){
		this.attr=attr;
		this.op=op;
	}

	@Override
	public boolean hasEmptyValue() {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) throws NullPointerException {
		Path path=root.get(this.getAttr().getName());
		switch (op) {
		case isNull:
			return builder.isNull(path);
		case isNotNull:
			return builder.isNotNull(path);
		default:
			return null;
		}
	}
}
