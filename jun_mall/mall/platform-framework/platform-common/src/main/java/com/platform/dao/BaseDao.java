package com.platform.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface BaseDao<T> {

    int save(T t);

    void save(Map<String, Object> map);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
