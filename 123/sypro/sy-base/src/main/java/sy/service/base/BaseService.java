package sy.service.base;

import java.io.Serializable;
import java.util.List;

import sy.util.base.QueryFilter;

/**
 * 基础Service
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T extends Serializable, PK extends Serializable> {

	/**
	 * 保存一个对象
	 * 
	 * @param t
	 * @return
	 */
	public Serializable save(T t);

	/**
	 * 保存一批对象
	 * 
	 * @param l
	 */
	public void saveAll(List<T> l);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param t
	 */
	public void saveOrUpdate(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param t
	 */
	public void delete(T t);

	/**
	 * 修改一个对象
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 获得一个对象
	 * 
	 * @param pk
	 *            主键
	 * @return
	 */
	public T get(PK pk);

	/**
	 * 获得第一个对象
	 * 
	 * @param filter
	 * @return
	 */
	public T get(QueryFilter filter);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	public List<T> find();

	/**
	 * 查找所有符合条件的
	 * 
	 * @param filter
	 * @return
	 */
	public List<T> find(QueryFilter filter);

	/**
	 * 统计所有
	 * 
	 * @return
	 */
	public Long count();

	/**
	 * 统计符合条件的
	 * 
	 * @param filter
	 * @return
	 */
	public Long count(QueryFilter filter);

}
