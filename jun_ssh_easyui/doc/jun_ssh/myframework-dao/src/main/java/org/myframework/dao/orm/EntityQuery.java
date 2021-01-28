package org.myframework.dao.orm;

import java.util.List;
import java.util.Map;

import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <T>数据库实体的类
 * <ol>
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年4月13日
 *
 */
public interface EntityQuery<T> {

	List<T> selectPageList(String sql, Map<String, Object> param, int offset,
			int limit);
	
	List<T> selectAllList(String sql, Map<String, Object> param );
 
	T selectOne(String sql, Map<String, Object> param);
	
 	public Long selectCount(String sql, Map<String, Object> map) ;
 	
	Page<T> findPageList(String sql, Map<String, Object> param,
			Pageable pageable);
	
	int update (String sql, Map<String, Object> param);
	
	public <S extends T> S save(S entity) ;
	
	public <S extends T> S saveAndFlush(S entity);
	
	public <S extends T> List<S> save(Iterable<S> entities);
	
	public void flush() ;
	
	Page<T> findAll(Pageable pageable);
	
	public long count()  ;
}