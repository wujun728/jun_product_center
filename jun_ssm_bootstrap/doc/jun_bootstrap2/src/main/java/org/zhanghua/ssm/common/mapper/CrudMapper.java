package org.zhanghua.ssm.common.mapper;

import java.io.Serializable;
import java.util.List;

import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * Mapper层增删改查基类
 * 
 * @author Wujun
 * 
 * @param <T>
 */
public interface CrudMapper<T extends DataEntity<T>, ID extends Serializable> extends BaseMapper {

	public void save(T t);

	public void delete(ID id);

	public void delete(List<ID> ids);

	public void update(T t);

	public T get(ID id);

	public T get(T t);

	public List<T> queryList();

	public List<T> queryList(T t);

}
