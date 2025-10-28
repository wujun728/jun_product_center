package com.jun.plugin.query.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import com.jun.plugin.query.Query;
import com.jun.plugin.query.expr.Expression;
import com.jun.plugin.query.expr.Expression.Operator;
import com.jun.plugin.query.jpa.expr.AttrExpression;
import com.jun.plugin.query.jpa.expr.AttrInExpression;
import com.jun.plugin.query.jpa.expr.AttrSimpleExpression;
import com.jun.plugin.query.sql.Condition;

public class JpaQuery<T> implements Query<T> {
	EntityManager em;
	CriteriaBuilder cb;
	CriteriaQuery<T> cq;
	Root<T> root;
	Class<T> entityClass;
	Condition condition;
	Predicate restrictions;
	@Override
	public Long count() {
		toPredicate();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		query.where(restrictions);
		query.select(cb.count(restrictions));
		return executeCountQuery(getCountQuery());
	}

	@Override
	public List<T> list(Sort sort) {
		return null;
	}

	@Override
	public Page<T> page(Pageable pageable) {
		return null;
	}

	public static <T> JpaQuery<T> create(Class<T> entityClass, EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> entityToot = criteriaQuery.from(entityClass);
		return new JpaQuery<T>(entityClass, criteriaBuilder, criteriaQuery, entityToot,em);
	}

	public JpaQuery<T> where(Condition condition) {
		this.condition=condition;
		return this;
	}
	
	private JpaQuery(Class<T> entityClass, CriteriaBuilder cb, CriteriaQuery<T> cq,
			Root<T> root,EntityManager em) {
		super();
		this.cb = cb;
		this.cq = cq;
		this.root = root;
		this.em=em;
	}

	public CriteriaBuilder getCb() {
		return cb;
	}

	public void setCb(CriteriaBuilder cb) {
		this.cb = cb;
	}

	public CriteriaQuery<T> getCq() {
		return cq;
	}

	public void setCq(CriteriaQuery<T> cq) {
		this.cq = cq;
	}

	public Root<T> getRoot() {
		return root;
	}

	public void setRoot(Root<T> root) {
		this.root = root;
	}

	public Predicate toPredicate(AttrExpression... expressions) {
		if (expressions == null || expressions.length == 0) {
			return null;
		}
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (AttrExpression expr : expressions) {
			Predicate predicate=toPredicate(root,cq,cb,expr);
			predicates.add(predicate);
		}
		// 将所有条件用 and 联合起来
		if (predicates.size() > 0) {
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}
		return cb.conjunction();
	}
	
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
			CriteriaBuilder builder,AttrExpression expression){
		Path path=root.get(expression.getAttr().getName());
		Operator operator=expression.getOp();
		switch (operator) {
		case EQ:
			return builder.equal(path, ((AttrSimpleExpression)expression).getValue() );
		case NE:
			return builder.notEqual(path, ((AttrSimpleExpression)expression).getValue());
		case LIKE:
			return builder.like(path, ((AttrSimpleExpression)expression).getValue().toString());
			
		case CONTAIN:
			return builder.like(path, "%" + ((AttrSimpleExpression)expression).getValue().toString() +"%");
		case LT:
			return builder.lessThan(path, (Comparable)((AttrSimpleExpression)expression).getValue());
		case GT:
			return builder.greaterThan(path,  (Comparable)((AttrSimpleExpression)expression).getValue());
		case LTE:
			return builder.lessThanOrEqualTo(path, (Comparable)((AttrSimpleExpression)expression).getValue());
		case GTE:
			return builder.greaterThanOrEqualTo(path, (Comparable)((AttrSimpleExpression)expression).getValue());
		case isNotNull:
			return builder.isNotNull(path);
		case isNull:
			return builder.isNull(path);
		case IN:
			AttrInExpression inExpression=(AttrInExpression)expression;
			In in = builder.in(path);
			Object[] values=inExpression.getValue();
			for(Object obj:values){
				in.value(obj);
			}
			return builder.conjunction();		
		default:
			return null;
		}		
	}
	private static Long executeCountQuery(TypedQuery<Long> query) {
		Assert.notNull(query);
		List<Long> totals = query.getResultList();
		Long total = 0L;

		for (Long element : totals) {
			total += element == null ? 0 : element;
		}

		return total;
	}
	
	protected <S extends T> TypedQuery<Long> getCountQuery() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.where(restrictions);
		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}
		return em.createQuery(query);
	}
		
}
