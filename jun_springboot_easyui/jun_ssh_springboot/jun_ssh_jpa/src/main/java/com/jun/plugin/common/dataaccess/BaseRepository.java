package com.jun.plugin.common.dataaccess;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;

import com.jun.plugin.query.jpa.expr.AttrExpression;
import com.jun.plugin.query.sql.expr.SQLExpression;
/**
 * orm查询最佳实践：<br>
 * 1.页面参数封装到实体，然后运用qbe(query by example)<br>
 * 2.单表查询使用属性表达式，通过AExpr表达式工厂构造<br>
 * 3.多表复杂查询写sql最简单，参考spring-data-jpa-extra<br>
 *   或者使用SQLQuery动态拼接<br>
 * 取得一个实体，方法以get开头<br>
 * 取得一个列表或分页查询，方法以find开头<br>
 * 
 * 
 * @author klguang
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{
	
	//--------------entity manager methods----------------
//	public boolean isManaged(T entity);
//	public ID getIdentifier(T entity);
//	public void lock(T entity, LockModeType lockModeType);
	public EntityManager getEntityManager();
	
	public Class<T> getEntityClass();
	
	public String getEntityName();
	
	public String getTableName();
	
	public JpaEntityInformation<T, ?> getEntityInformation();
	
	/**
	 * 删除实体
	 * @param id
	 * @param checkRelation 是否检查关联
	 * @return 是否删除成功
	 */
	public boolean delete(ID id,boolean checkRelation);
	public void deleteIds(Iterable<ID> ids);
	
	
	/**
	 * 
	 * EntityManager会刷新实体，refresh
	 * 
	 * @param id
	 * @return
	 */
	public T getById(ID id);
	/**
	 * 随机从数据库中抽出n条记录
	 * @param n
	 * @return
	 */
	public List<T> random(int n);
	public List<T> random(int n,SQLExpression...exprs);
	public T update(T entity);
	public T update(T entity, String... ignoreProperties);
	
	//--------------qbe(query by example)--------------------
	public Page<T> findPage(T example,Pageable pageable);
	public List<T> findList(T example,Sort sort);
	public List<T> findList(T example);
	public T getOne(T example);
	public long count(T example);
//	public List findList(T example, Sort sort,String...as);
//	public List findList(Example<T> example,Sort sort, String...as);
	//-------------find by attribute------------------------
	
	public Page<T> findPage(Pageable pageable,AttrExpression...exprs);
	public List<T> findList(Sort sort,AttrExpression...exprs);
	public List<T> findList(AttrExpression...exprs);
	public T getOne(AttrExpression...exprs);
	public long count(AttrExpression...exprs);
		
	public long executeUpdate(String updateName, Object obj);
	
}
