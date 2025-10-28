package com.jun.plugin.query.jpa.expr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.metamodel.Attribute;

import com.jun.plugin.query.expr.Expression;
import com.jun.plugin.query.expr.Expression.Operator;


public class AttrInExpression extends AttrExpression{
	Object[] value;
	public AttrInExpression(Attribute attr, Object...value) {
		this.attr = attr;
		this.value = value;
		this.op = Operator.IN;
	}
	@Override
	public boolean hasEmptyValue() {
		if(value==null||"".equals(value))
			return true;
		for(Object obj:value){
			if(obj==null||"".equals(obj))
				return true;
		}		
		return false;
	}
	
	public boolean hasNullValue(){
		for(Object obj:value){
			if(obj==null)
				return true;
		}		
		return false;
	}
	
	public Object[] getValue() {
		return value;
	}
	public void setValue(Object[] value) {
		this.value = value;
	}
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) throws NullPointerException {
		if(value==null)
			throw new NullPointerException("in表达式值为空:"+attr.getName());		
		Path path=root.get(this.getAttr().getName());
		return path.in(value);
	}
}
