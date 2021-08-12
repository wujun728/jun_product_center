package com.shuogesha.platform.web.mongo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.shuogesha.platform.web.util.BeanUtil;

@Repository
public abstract class MongoBaseDaoImpl<T> implements MongoBaseDao<T>{
    private static final int DEFAULT_SKIP = 0;
    private static final int DEFAULT_LIMIT = 20;

    /**
     * spring mongodb　集成操作类　
     */
    @Autowired
    protected MongoTemplate mongoTemplate;
 
    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    } 

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    } 

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    public void update(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, this.getEntityClass()); 
    } 
    
    /**
     * 删除
     * @param id
     */
    public void removeById(Object id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, this.getEntityClass());
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     * @return
     */
    public T saveEntity(T entity) {
        mongoTemplate.save(entity);
        return entity;
    } 

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    public T findById(Object id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    } 

    /**
     * 分页查询
     * @param page
     * @param query
     * @return
     */
    public Pagination findPage(Pagination page, Query query){
        long count = this.count(query);
        page.setTotalCount(count);
        int pageNumber = page.getPageNo();
        int pageSize = page.getPageSize();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
        List<T> rows = this.find(query);
        page.setDatas(rows);
        return page;
    }
 

    /**
     * 求数据总和
     * @param query
     * @return
     */
    public long count(Query query){
        return mongoTemplate.count(query, this.getEntityClass());
    } 

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    public Class<T> getEntityClass(){
        Type superclass = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType)superclass).getActualTypeArguments();
        return (Class) actualTypeArguments[0];
    }
    
    /**
     * 更新
     */
    public T updateEntity(T entity,Object id) { 
    	Query query = new Query(); 
        query.addCriteria(Criteria.where("_id").is(id));
     	return (T) mongoTemplate.updateFirst(query,BeanUtil.fromBean(entity,false), this.getEntityClass());
     }
    /**
     * 选择更新
     */
    public T updateEntityOne(T entity,Object id) { 
    	Query query = new Query(); 
        query.addCriteria(Criteria.where("_id").is(id));
     	return (T) mongoTemplate.updateFirst(query,BeanUtil.fromBean(entity,true), this.getEntityClass());
     }
    
    
    ///////////////////
    
    public void remove(Query query){ 
        mongoTemplate.remove(query, this.getEntityClass());
    }
}
