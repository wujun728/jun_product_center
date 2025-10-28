package com.jun.plugin.query.sql;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Table;

import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.jun.plugin.query.Query;
import com.jun.plugin.query.SmartAliasToBean;

public class SQLQuery<T> implements Query<T> {
	EntityManager em;
	Class<T> resultClass;//结果集封装到什么类型中
	String select;
	String from;
	Condition condition;
	List<String> parts = new ArrayList<String>();
	String rowClause=null;
	public SQLQuery(EntityManager em, Class<T> resultClass) {
		super();
		this.em = em;
		this.resultClass = resultClass;
	}

	/**
	 * 不需要带select关键字
	 * @param select
	 * @return
	 */
	public SQLQuery<T> select(String select) {
		this.select = select;
		return this;
	}

	/**
	 * 不需要带from关键字
	 * @param from
	 * @return
	 */
	public SQLQuery<T> from(String from) {
		this.from = from;
		return this;
	}

	public SQLQuery<T> where(Condition condition) {
		this.condition = condition;
		return this;
	}
	/**
	 * 是一个子查询 或者 是limit等
	 * @param part
	 * @return
	 */
	public SQLQuery<T> addPart(String part) {
		parts.add(part);
		return this;
	}

	public void buildRowClause() {
		StringBuffer sb=new StringBuffer();
		try {
			sb.append(" FROM ").append(from)
		      .append(" WHERE 1=1 ").append(condition.toSQL())
		      .append(partsToString());
			rowClause=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(Sort sort) {
		buildRowClause();
		if(resultClass==null)
			return null;
		String sql="SELECT "+select+rowClause;
		if(sort!=null)
			sql+= " " + getSortSQL(sort);
		System.out.println(sql);
		return queryList(sql, null);
	}	
	@SuppressWarnings("unchecked")
	private List<T> queryList(String sql,Pageable pageable){
		javax.persistence.Query query=null;
		Table t = resultClass.getAnnotation(Table.class);
		if(t!=null){	
			query= em.createNativeQuery(sql, resultClass);
		}else if(resultClass==java.util.Map.class){
			query=em.createNativeQuery(sql);
			query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}else{
			query=em.createNativeQuery(sql);
			query.unwrap(org.hibernate.SQLQuery.class).setResultTransformer(new SmartAliasToBean(resultClass));
		}
		if(pageable==null){
			return query.getResultList();
		}else{
			return query.setFirstResult(pageable.getOffset())
						.setMaxResults(pageable.getPageSize())
						.getResultList();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> page(Pageable pageable) {
		buildRowClause();
		if(rowClause==null)
			return null;
		String row_clause=rowClause;
		String sql="SELECT "+select+row_clause;
		if(pageable.getSort()!=null)
			sql+=" " +getSortSQL(pageable.getSort());
		System.out.println(sql);
		Long count=getCount(row_clause, em);
		return new PageImpl<T>(queryList(sql, pageable), pageable, count);
	}
	
	@Override
	public Long count() {
		buildRowClause();
		if(rowClause==null)
			return null;
		return getCount(rowClause, em);
	}
	public static Long getCount(String sql_row_clause,EntityManager em){
		String sql=null;
		if(sql_row_clause==null||"".equals(sql_row_clause))
			return 0L;
		//count(*)、count('anything')和count(1)在有索引的条件下效率几乎一样
		if(sql_row_clause.toLowerCase().contains("group by"))
			sql="select count(*) from (select count(*) "+sql_row_clause+") as counttb";
		else
			sql="select count(*) "+sql_row_clause;
		BigInteger count=(BigInteger) em.createNativeQuery(sql).getResultList().iterator().next();
		return count.longValue();
	}
	
	private String partsToString(){
		if(parts.isEmpty())
			return "";
		StringBuffer sb=new StringBuffer();
		for(String part:parts){
			sb.append(" "+part);
		}
		return sb.toString();
	}
	
	public static String getSortSQL(Sort sort){

		if(sort == null || (!sort.iterator().hasNext())){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		sb.append("ORDER BY ");
		for(Iterator<Order> it=sort.iterator();it.hasNext();){
			Order order=it.next();
			sb.append(order.getProperty()+" " +order.getDirection()+",");
		}
		return sb.substring(0, sb.length()-1);
	}

}
