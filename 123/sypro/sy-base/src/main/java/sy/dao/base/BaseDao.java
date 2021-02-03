package sy.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.Record;

import sy.util.base.QueryFilter;

/**
 * 基础DAO
 * 
 * 其他DAO集成此类，用来获得CRUD功能
 * 
 * 针对所有的数据库操作都提供了接口
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T extends Serializable, PK extends Serializable> {

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
	 * 主要用于，查询一个对象，但是传递的参数不是主键
	 * 
	 * @param filter
	 * @return
	 */
	public T get(QueryFilter filter);

	/**
	 * 可以传递一个hql的查询，主要用于复杂hql或者手动级联查询时用到
	 * 
	 * @param newHql
	 * @param filter
	 * @return
	 */
	public T get(QueryFilter filter, String newHql);

	/**
	 * 通过hql获得第一个对象
	 * 
	 * @param hql
	 * @return
	 */
	public T getByHql(String hql);

	/**
	 * 通过hql获得第一个对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T getByHql(String hql, Map<String, Object> params);

	/**
	 * 查找一批对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查找一批对象
	 * 
	 * @return
	 */
	public List<T> find();

	/**
	 * 通过hql查找一批对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 查找一批对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param params
	 * @return
	 */
	public List<T> find(Map<String, Object> params);

	/**
	 * 查找一批对象
	 * 
	 * @param filter
	 * @return
	 */
	public List<T> find(QueryFilter filter);

	/**
	 * 查找一批对象
	 * 
	 * 主要用于复杂hql或者手动级联查询时用到
	 * 
	 * @param newHql
	 * @param filter
	 * @return
	 */
	public List<T> find(QueryFilter filter, String newHql);

	/**
	 * 查找一批对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @param page
	 *            查找第几页
	 * @param pageSize
	 *            每页查多少条
	 * @return
	 */
	public List<T> find(String hql, Integer page, Integer pageSize);

	/**
	 * 查找一批对象
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @param params
	 *            参数
	 * @param page
	 *            查找第几页
	 * @param pageSize
	 *            每页查找多少条
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params, Integer page, Integer pageSize);

	/**
	 * 统计数目
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 统计
	 * 
	 * @return
	 */
	public Long count();

	/**
	 * 统计数目
	 * 
	 * 不建议在service直接调用此方法，请使用带QueryFilter参数的方法
	 * 
	 * @param hql
	 * @param params
	 *            参数
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 统计数目
	 * 
	 * @param filter
	 * @return
	 */
	public Long count(QueryFilter filter);

	/**
	 * 统计数目
	 * 
	 * 一般用于复杂hql或者手动级联查询时用到
	 * 
	 * @param filter
	 * @param newHql
	 * @return
	 */
	public Long count(QueryFilter filter, String newHql);

	/**
	 * 执行hql
	 * 
	 * 一般在删除某些对象，不经过查询直接删除的时候用到
	 * 
	 * delete from T as t where t.id = 1
	 * 
	 * @param hql
	 * @return
	 */
	public int executeHql(String hql);

	/**
	 * 执行hql
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int executeHql(String hql, Map<String, Object> params);

	/**
	 * 通过sql语句，查找
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> getBySql(String sql);

	/**
	 * 通过sql语句，查找
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> getBySql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句查找列表
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> findBySql(String sql);

	/**
	 * 通过sql语句查找列表
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findBySql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句查找列表，分页查找
	 * 
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findBySql(String sql, Integer page, Integer pageSize);

	/**
	 * 通过sql语句查找列表，分页查找
	 * 
	 * @param sql
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findBySql(String sql, Map<String, Object> params, Integer page, Integer pageSize);

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @return
	 */
	public int executeSql(String sql);

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @param params
	 *            参数
	 * @return
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句进行统计
	 * 
	 * @param sql
	 * @return
	 */
	public Long countBySql(String sql);

	/**
	 * 通过sql语句进行统计
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句获取一条记录
	 * 
	 * @param sql
	 * @return
	 */
	public Record getRecordBySql(String sql);

	/**
	 * 通过sql语句获取一条记录
	 * 
	 * @param sql
	 * @param filter
	 * @return
	 */
	public Record getRecordBySql(String sql, QueryFilter filter);

	/**
	 * 通过sql语句获取一条记录
	 * 
	 * sql中字段占位符请使用@
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Record getRecordBySql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句获得集合
	 * 
	 * @param sql
	 * @return
	 */
	public List<Record> findRecordBySql(String sql);

	/**
	 * 通过sql语句获得集合
	 * 
	 * @param sql
	 * @param filter
	 * @return
	 */
	public List<Record> findRecordBySql(String sql, QueryFilter filter);

	/**
	 * 通过sql语句获得集合
	 * 
	 * sql中字段占位符请使用@
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Record> findRecordBySql(String sql, Map<String, Object> params);

	/**
	 * 通过sql语句获得集合，带分页
	 * 
	 * sql中字段占位符请使用@
	 * 
	 * @param sql
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Record> findRecordBySql(String sql, Map<String, Object> params, Integer page, Integer pageSize);

	/**
	 * 通过sql语句统计
	 * 
	 * @param sql
	 * @return
	 */
	public Long countRecordBySql(String sql);

	/**
	 * 通过sql语句统计
	 * 
	 * @param sql
	 * @param filter
	 * @return
	 */
	public Long countRecordBySql(String sql, QueryFilter filter);

	/**
	 * 批量执行sql语句
	 * 
	 * sql中字段占位符请使用@，例如：
	 * 
	 * "UPDATE t_pet SET name=@name WHERE id=@id"
	 * 
	 * @param sql
	 * @param paramsList
	 */
	public void executeBatch(String sql, List<Map<String, Object>> paramsList);

}
