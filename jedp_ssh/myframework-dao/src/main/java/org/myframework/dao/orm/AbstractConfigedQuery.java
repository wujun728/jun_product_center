package org.myframework.dao.orm;

import java.util.List;
import java.util.Map;

import org.myframework.dao.sqlconfig.SqlTemplate;
import org.myframework.dao.sqlconfig.impl.VelocitySqlTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractConfigedQuery<T> extends AbstractEntityQuery<T>{
	
	private SqlTemplate sqlTemplate = new VelocitySqlTemplate();
	
	@Override
	public List<T> selectAllList(String sqlKey, Map<String, Object> param) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return super.selectAllList(sql, param);
	}
 

	@Override
	public List<T> selectPageList(String sqlKey, Map<String, Object> param,
			int offset, int limit) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return super.selectPageList(sql, param, offset, limit);
	}
	 

	@Override
	public T selectOne(String sqlKey, Map<String, Object> param) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return super.selectOne(sql, param);
	}


	@Override
	public Long selectCount(String sqlKey, Map<String, Object> param) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return super.selectCount(sql, param);
	}


	@Override
	public Page<T> findPageList(String sqlKey, Map<String, Object> param,
			Pageable pageable) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return super.findPageList(sql, param, pageable);
	}

	@Override
	public int update(String sqlKey, Map<String, Object> param) {
		String sql = sqlTemplate.getSql(sqlKey, param);
		return getSqlQuery(sql,param).executeUpdate();
	}

}
