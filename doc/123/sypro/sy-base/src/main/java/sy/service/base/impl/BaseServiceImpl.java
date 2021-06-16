package sy.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.service.base.BaseService;
import sy.util.base.QueryFilter;

/**
 * 基础service实现类
 * 
 * 实现此service的类必须提供泛型dao
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 * @param <T>
 * @param <PK>
 */
@Service("baseService")
public abstract class BaseServiceImpl<T extends Serializable, PK extends Serializable> implements BaseService<T, PK> {

	protected BaseDao<T, PK> dao;

	/**
	 * 抽象方法，需要子类提供dao
	 * 
	 * @param dao
	 */
	public abstract void setDao(BaseDao<T, PK> dao);

	@Override
	public Serializable save(T t) {
		return this.dao.save(t);
	}

	@Override
	public void saveAll(List<T> l) {
		this.dao.saveAll(l);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.dao.saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		this.dao.delete(t);
	}

	@Override
	public void update(T t) {
		this.dao.update(t);
	}

	@Override
	public T get(PK pk) {
		return this.dao.get(pk);
	}

	@Override
	public T get(QueryFilter filter) {
		return this.dao.get(filter);
	}

	@Override
	public List<T> find() {
		return this.dao.find();
	}

	@Override
	public List<T> find(QueryFilter filter) {
		return this.dao.find(filter);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public Long count(QueryFilter filter) {
		return this.dao.count(filter);
	}

}
