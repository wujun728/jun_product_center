package com.jun.plugin.query.jpa.expr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;


public class AttrBetweenExpression extends AttrExpression{
	private Object lo;
	private Object hi;

	protected AttrBetweenExpression(Attribute attr, Object lo, Object hi) {
		this.attr = attr;
		this.lo = lo;
		this.hi = hi;
		this.op=Operator.between;
	}

	@Override
	public boolean hasEmptyValue() {
		return lo == null || "".equals(lo) || hi == null || "".equals(hi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) throws NullPointerException {
		if(ignoreEmpty==false&&(lo==null||hi==null))
			throw new NullPointerException("between表达式值为空:"+this.getAttr().getName());			
		Path path=root.get(this.getAttr().getName());
		return builder.between(path,(Comparable)lo, (Comparable)hi);
	}
}
