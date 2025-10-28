package com.erp.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//import javax.persistence.EntityManagerFactory;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dao.PublicDao;
import com.erp.util.Constants;

@SuppressWarnings("unchecked")
@Repository("PublicDaoImpl")
public class PublicDaoImpl<T>  implements PublicDao<T> {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	/**获取sessionFactory*/
//	@Autowired
//    private EntityManagerFactory entityManagerFactory;
//    public SessionFactory getSessionFactory() {
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }
//    private HibernateTemplate getHibernateTemplate(){
//        return new HibernateTemplate(getSessionFactory());
//    }
 
//	========================================以下封装一些常用的方法=============================================
	@Transactional
	public Serializable save(T o) {
		Transaction tx = getCurrentSession().beginTransaction();
		Serializable serializable = this.getCurrentSession().save(o);
		Constants.getLogs(this.getCurrentSession(), o, Constants.LOGS_INSERT, Constants.LOGS_INSERT_TEXT, Constants.LOGS_INSERT_NAME);
		tx.commit();
		return serializable;
	}
	@Transactional
	public void delete(T o) {
		Transaction tx = getCurrentSession().beginTransaction();
		this.getCurrentSession().delete(o);
		tx.commit();
	}

	@Transactional
	public void update(T o) {
		Transaction tx = getCurrentSession().beginTransaction();
		this.getCurrentSession().update(o);
		Constants.getLogs(this.getCurrentSession(), o, Constants.LOGS_UPDATE, Constants.LOGS_UPDATE_TEXT, Constants.LOGS_UPDATE_NAME);
		tx.commit();
	}
	
	@Transactional
	public void deleteToUpdate(T o) {
		Transaction tx = getCurrentSession().beginTransaction();
		this.getCurrentSession().update(o);
		Constants.getLogs(this.getCurrentSession(), o, Constants.LOGS_DELETE, Constants.LOGS_DELETE_TEXT, Constants.LOGS_DELETE_NAME);
		tx.commit();
	}
	
	@Transactional
	public void saveOrUpdate(T o) {
		Transaction tx = getCurrentSession().beginTransaction();
		this.getCurrentSession().saveOrUpdate(o);
		tx.commit();
	}

	@Transactional(readOnly=true)
	public List<T> find(String hql) {
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public List findBySQL(String sql) {
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	}
	
	@Transactional(readOnly=true)
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	
	@Transactional(readOnly=true)
	public List<T> find(String hql, Map<String, Object> params, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	@Transactional(readOnly=true)
	public T get(Class<T> c, Serializable id) {
		return (T) this.sessionFactory.getCurrentSession().get(c, id);
	}
	
	@Transactional(readOnly=true)
	public T get(String hql, Map<String, Object>  param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly=true)
	public Long count(String hql) {
		return (Long) this.sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Transactional(readOnly=true)
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}
	

	@Transactional
	public Integer executeHql(String hql) {
		return this.sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Transactional
	public Integer executeHql(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}
	

 
}
