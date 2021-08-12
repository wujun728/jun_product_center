package com.shuogesha.platform.web.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface MongoBaseDao<T> {
	    /**
	     * 通过条件查询实体(集合)
	     *
	     * @param query
	     */
	    public List<T> find(Query query) ;

	    /**
	     * 通过一定的条件查询一个实体
	     *
	     * @param query
	     * @return
	     */
	    public T findOne(Query query) ;

	    /**
	     * 通过条件查询更新数据
	     *
	     * @param query
	     * @param update
	     * @return
	     */
	    public void update(Query query, Update update) ;

	    /**
	     * 保存一个对象到mongodb
	     *
	     * @param entity
	     * @return
	     */
	    public T saveEntity(T entity) ;

	    /**
	     * 通过ID获取记录
	     *
	     * @param id
	     * @return
	     */
	    public T findById(Object id) ;

	    /**
	     * 分页查询
	     * @param pager
	     * @param query
	     * @return
	     */
	    public Pagination findPage(Pagination pager, Query query);

	    /**
	     * 求数据总和
	     * @param query
	     * @return
	     */
	    public long count(Query query);
	    

	    /** 更新全部
	     * @param entity
	     * @return
	     */
	    public T updateEntityOne(T entity,Object id) ;
	    
	    /**
	     * 更新全部
	     * @param entity
	     * @return
	     */
	    public T updateEntity(T entity,Object id) ;
	    
	    /**
	     * 删除
	     * @param id
	     */
	    public void removeById(Object id);
	    /**
	     * 删除查询
	     * @param query
	     */
	    void remove(Query query);  
}
