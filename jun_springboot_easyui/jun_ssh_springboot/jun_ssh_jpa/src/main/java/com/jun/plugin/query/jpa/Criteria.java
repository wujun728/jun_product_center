package com.jun.plugin.query.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.jun.plugin.query.expr.Expression;
import com.jun.plugin.query.jpa.expr.AttrExpression;

public class Criteria<T> implements Specification<T> {
	private List<AttrExpression> expressions = new ArrayList<AttrExpression>();
	boolean allIgnoreCase = Expression.DEFAULT_IGNORE_CASE;
	boolean allIgnoreEmpty = Expression.DEFAULT_IGNORE_EMPTY;

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {
		if (!expressions.isEmpty()) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (AttrExpression attrExpression : expressions) {
				// 根据是否忽略空值，过滤掉空值
				if ((allIgnoreEmpty || attrExpression.isIgnoreEmpty())
						&& attrExpression.hasEmptyValue())
					continue;
				predicates.add(attrExpression.toPredicate(root, query, builder));
			}
			// 将所有条件用 and 联合起来
			if (predicates.size() > 0) {
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}
		return builder.conjunction();
	}

	public Criteria<T> add(AttrExpression expr) {
		if (expr != null)
			expressions.add(expr);
		return this;
	}

	public Criteria<T> add(AttrExpression... exprs) {
		for(AttrExpression expr:exprs){
			if (expr != null)
				expressions.add(expr);
		}
		return this;
	}

}