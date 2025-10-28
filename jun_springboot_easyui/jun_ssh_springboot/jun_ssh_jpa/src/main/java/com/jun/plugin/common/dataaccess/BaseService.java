package com.jun.plugin.common.dataaccess;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jun.plugin.query.jpa.expr.AttrExpression;
import com.jun.plugin.query.sql.expr.SQLExpression;


public interface BaseService<T,ID extends Serializable>{
	//-----------------CRUD-------------------
	public T save(T entity);
	public T update(T entity);
	
	
	public List<T> save(Iterable<T> entities);
	
	/**
	 * 
	 * 默认已经忽略BaseEntity的属性
	 * @param entity
	 * @param ignoreProperties
	 * @return
	 */
	
	public T update(T entity, String... ignoreProperties);
	public void delete(ID id);
	/**
	 * 删除实体
	 * @param id
	 * @param checkRelation 是否检查关联
	 * @return 是否删除成功
	 */
	public boolean delete(ID id, boolean checkRelation);
	public void deleteIds(Iterable<ID> ids);
	/**
	 * 
	 * EntityManager会刷新实体，refresh
	 * 
	 * @param id
	 * @return
	 */
	public T getById(ID id);
	
	public List<T> findAll();
	public List<T> findAll(Iterable<ID> ids);
	public List<T> findAll(Sort sort);
	public Page<T> findAll(Pageable pageable);
	
	public List<T> findList(Iterable<ID> ids);

	
	/**
	 * 随机从数据库中抽出n条记录
	 * @param n
	 * @return
	 */
	public List<T> random(int n);
	public List<T> random(int n,SQLExpression...exprs);
	//--------------query by example------------
	
	public Page<T> findPage(T example,Pageable pageable);
	public List<T> findList(T example,Sort sort);
	public List<T> findList(T example);
	public T getOne(T example);
	public long count(T example);
	
	//--------------query by example with matcher------------
	/**
	 * 	匹配模式<br>
	 ExampleMatcher matcher = ExampleMatcher.matching()
  			.withMatcher("firstname", endsWith())
  			.withMatcher("lastname", startsWith().ignoreCase());
  	 Example<Person> example = Example.of(person, matcher); 
	 * 
	 * @param example
	 * @param pageable
	 * @return
	 */
	public Page<T> findPage(Example<T> example,Pageable pageable);
	public List<T> findList(Example<T> example, Sort sort);
	public List<T> findList(Example<T> example);
	public long count(Example<T> example);
	
	//-------------find by attribute---------------
	
	public Page<T> findPage(Pageable pageable,AttrExpression...exprs);
	public List<T> findList(Sort sort,AttrExpression...exprs);
	public List<T> findList(AttrExpression...exprs);
	public T getOne(AttrExpression...exprs);
	public long count(AttrExpression...exprs);
	
	long executeUpdate(String updateName,Object obj); 
}
