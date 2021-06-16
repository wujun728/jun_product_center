package sy.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;

import sy.dao.base.BaseDao;
import sy.util.base.ConfigUtil;
import sy.util.base.QueryFilter;

/**
 * 基础数据库操作
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 * @param <T>
 *            操作对象
 * @param <PK>
 *            主键
 */
public abstract class BaseDaoImpl<T extends Serializable, PK extends Serializable> implements BaseDao<T, PK> {

	/**
	 * 注入hibernate sessionFactory
	 */
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * 注入nutz的dao，用来操作纯sql语句
	 */
	@Resource(name = "nutzDao")
	private Dao nutzDao;

	private final Class<T> entityClass;// 泛型类的class

	private final String hql;// 查询语句
	private final String countHql;// 统计语句
	private static final String JOINFETCH = " LEFT JOIN FETCH ";// 预先抓取关键字
	private static final String JOIN = " LEFT JOIN ";// 级联查询关键字

	public String getCountHql() {
		return countHql;
	}

	public String getHql() {
		return hql;
	}

	/**
	 * 构造时，告诉baseDao要查询表的类型，并且初始化查询和统计语句
	 */
	public BaseDaoImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.hql = " SELECT DISTINCT t FROM " + this.entityClass.getSimpleName() + " t ";// select DISTINCT t from Txxx t
		this.countHql = " SELECT COUNT(DISTINCT t) FROM " + this.entityClass.getSimpleName() + " t ";// select count(DISTINCT t) from Txxx t
	}

	/**
	 * 由于hibernate4必须开启session，所以通过这个方法获得当前线程的session
	 * 
	 * @return
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 更改hql和添加参数
	 * 
	 * 调用方法后，hql的条件会变成 and t.xxx like :t_xxx这种形式，并且添加了params
	 * 
	 * 如果有预先抓取参数，则hql还会加上例如 join fetch t.company as company类似的语句
	 * 
	 * @param filter
	 *            过滤器
	 * @param params
	 *            参数
	 * @param hql
	 *            查询语句
	 * @return
	 */
	public String changeHqlAndParams(QueryFilter filter, Map<String, Object> params, String hql) {
		if (filter.getJoinFetch() != null && filter.getJoinFetch().length > 0) {// 如果有预先抓取需求，这拼装hql
			for (String joinFetch : filter.getJoinFetch()) {
				hql += " " + JOINFETCH + " " + joinFetch + " AS " + joinFetch.substring(joinFetch.indexOf(".") + 1) + " ";// left join fetch t.company as company
			}
		}
		if (filter.getJoin() != null && filter.getJoin().length > 0) {// 如果有关联需要，拼装hql
			for (String join : filter.getJoin()) {
				hql += " " + JOIN + " " + join + " AS " + join.substring(join.indexOf(".") + 1) + " ";// left join t.company as company
			}
		}
		hql += " WHERE 1=1 ";
		if (filter.getParams().size() > 0) {
			int paramIndex = 0;// 参数占位符索引
			for (Object[] o : filter.getParams()) {// filter.getParams()=[["t.name", "like", "%%孙%%"],["id", "in", [1, 2, 3, 4]]]
				if (o[1].toString().equalsIgnoreCase("In") || o[1].toString().equalsIgnoreCase("Not In")) {// In条件拼装，最终会拼装成类似 and id in (:_1,:_2,:_3,:_4)
					List<Object> values = (ArrayList<Object>) o[2];
					String inParamString = "";
					for (int i = 0; i < values.size(); i++) {
						if (i > 0) {
							inParamString += " , ";
						}
						++paramIndex;// 增加参数占位符索引
						String paramName = "_" + paramIndex;
						inParamString += " :" + paramName + " ";
						params.put(paramName, values.get(i));
					}
					if (o[1].toString().equalsIgnoreCase("In")) {
						hql += " AND " + o[0] + " IN (" + inParamString + ") ";
					}
					if (o[1].toString().equalsIgnoreCase("Not In")) {
						hql += " AND " + o[0] + " NOT IN (" + inParamString + ") ";
					}
				} else if (o[1].toString().equalsIgnoreCase("Is Null")) {// is null条件拼装
					hql += " AND " + o[0] + " IS NULL ";
				} else if (o[1].toString().equalsIgnoreCase("Is Not Null")) {// is not null条件拼装
					hql += " AND " + o[0] + " IS NOT NULL ";
				} else {
					++paramIndex;// 增加参数占位符索引
					String paramName = "_" + paramIndex;
					hql += " AND " + o[0] + " " + o[1] + " :" + paramName + " ";
					params.put(paramName, o[2]);
				}
			}
		}
		return hql;
	}

	@Override
	public Serializable save(T t) {
		return this.getCurrentSession().save(t);
	}

	@Override
	public void saveAll(List<T> l) {
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				this.getCurrentSession().save(l.get(i));
				if (i % Integer.parseInt(ConfigUtil.getBatchSize()) == 0) {
					this.getCurrentSession().flush();
					this.getCurrentSession().clear();
				}
			}
		}
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		this.getCurrentSession().delete(t);
	}

	@Override
	public void update(T t) {
		this.getCurrentSession().update(t);
	}

	@Override
	public T get(PK pk) {
		return (T) this.getCurrentSession().get(this.entityClass, pk);
	}

	@Override
	public T get(QueryFilter filter) {
		return this.get(filter, this.getHql());
	}

	@Override
	public T get(QueryFilter filter, String newHql) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = newHql;
		hql = changeHqlAndParams(filter, params, hql);
		return this.getByHql(hql, params);
	}

	@Override
	public T getByHql(String hql) {
		return this.getByHql(hql, null);
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.setFirstResult((1 - 1) * 1).setMaxResults(1).list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<T> find(String hql) {
		return this.find(hql, null, null, null);
	}

	@Override
	public List<T> find() {
		return this.find(this.getHql(), null, null, null);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		return this.find(hql, params, null, null);
	}

	@Override
	public List<T> find(Map<String, Object> params) {
		return this.find(this.getHql(), params, null, null);
	}

	@Override
	public List<T> find(QueryFilter filter) {
		return this.find(filter, this.getHql());
	}

	@Override
	public List<T> find(QueryFilter filter, String newHql) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = newHql;
		hql = changeHqlAndParams(filter, params, hql);
		if (StringUtils.isNotBlank(filter.getSort())) {// 如果有排序需求，这拼装排序条件
			hql += " ORDER BY " + filter.getSort();
		}
		return this.find(hql, params, filter.getPage(), filter.getPageSize());
	}

	@Override
	public List<T> find(String hql, Integer page, Integer pageSize) {
		return this.find(hql, null, page, pageSize);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, Integer page, Integer pageSize) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if (page == null || pageSize == null) {
			return q.list();
		}
		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long count(String hql) {
		return this.count(hql, null);
	}

	@Override
	public Long count() {
		return this.count(this.getCountHql());
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(QueryFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = this.getCountHql();
		hql = changeHqlAndParams(filter, params, hql);
		return this.count(hql.replaceAll(JOINFETCH, JOIN), params);// 统计是不可以预先抓取的，所以要更换hql
	}

	@Override
	public Long count(QueryFilter filter, String newHql) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = newHql;
		hql = changeHqlAndParams(filter, params, hql);
		return this.count(hql.replaceAll(JOINFETCH, JOIN), params);// 统计是不可以预先抓取的，所以要更换hql
	}

	@Override
	public int executeHql(String hql) {
		return this.executeHql(hql, null);
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Map<String, Object> getBySql(String sql) {
		return this.getBySql(sql, null);
	}

	@Override
	public Map<String, Object> getBySql(String sql, Map<String, Object> params) {
		List<Map<String, Object>> l = this.findBySql(sql, params, 1, 1);
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findBySql(String sql) {
		return this.findBySql(sql, null, null, null);
	}

	@Override
	public List<Map<String, Object>> findBySql(String sql, Map<String, Object> params) {
		return this.findBySql(sql, params, null, null);
	}

	@Override
	public List<Map<String, Object>> findBySql(String sql, Integer page, Integer pageSize) {
		return this.findBySql(sql, null, page, pageSize);
	}

	/**
	 * 返回Map的Key可都是大写的哦
	 */
	@Override
	public List<Map<String, Object>> findBySql(String sql, Map<String, Object> params, Integer page, Integer pageSize) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if (page == null || pageSize == null) {
			return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}
		return q.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public int executeSql(String sql) {
		return this.executeSql(sql, null);
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Long countBySql(String sql) {
		return this.countBySql(sql, null);
	}

	@Override
	public Long countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	@Override
	public Record getRecordBySql(String sql) {
		return getRecordBySql(sql, new QueryFilter());
	}

	@Override
	public Record getRecordBySql(String sql, QueryFilter filter) {
		filter.setPage(1);
		filter.setPageSize(1);
		List<Record> l = this.findRecordBySql(sql, filter);
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Record getRecordBySql(String sql, Map<String, Object> params) {
		List<Record> l = this.findRecordBySql(sql, params, 1, 1);
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<Record> findRecordBySql(String sql) {
		return this.findRecordBySql(sql, new QueryFilter());
	}

	@Override
	public List<Record> findRecordBySql(String sql, QueryFilter filter) {
		Sql daoSql = Sqls.queryEntity(sql + " $condition");
		daoSql.setCallback(Sqls.callback.records());
		daoSql.setCondition(filter.getCondition());
		if (filter.getPage() != null && filter.getPageSize() != null) {
			daoSql.setPager(nutzDao.createPager(filter.getPage(), filter.getPageSize()));
		}
		nutzDao.execute(daoSql);
		return daoSql.getList(Record.class);
	}

	@Override
	public List<Record> findRecordBySql(String sql, Map<String, Object> params) {
		return this.findRecordBySql(sql, params, null, null);
	}

	@Override
	public List<Record> findRecordBySql(String sql, Map<String, Object> params, Integer page, Integer pageSize) {
		Sql daoSql = Sqls.queryEntity(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				daoSql.params().set(key, params.get(key));
			}
		}
		daoSql.setCallback(Sqls.callback.records());
		if (page != null && pageSize != null) {
			daoSql.setPager(nutzDao.createPager(page, pageSize));
		}
		nutzDao.execute(daoSql);
		return daoSql.getList(Record.class);
	}

	@Override
	public Long countRecordBySql(String sql) {
		return this.countRecordBySql(sql, new QueryFilter());
	}

	@Override
	public Long countRecordBySql(String sql, QueryFilter filter) {
		Sql daoSql = Sqls.fetchLong(sql + " $condition").setCondition(filter.getCondition());
		nutzDao.execute(daoSql);
		return (long) daoSql.getInt();
	}

	@Override
	public void executeBatch(String sql, List<Map<String, Object>> paramsList) {
		Sql daoSql = Sqls.create(sql);
		if (paramsList != null && paramsList.size() > 0) {
			for (Map<String, Object> param : paramsList) {
				for (String key : param.keySet()) {
					daoSql.params().set(key, param.get(key));
				}
				daoSql.addBatch();
			}
		}
		nutzDao.execute(daoSql);
	}

}
