package com.platform.dao;

import com.platform.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

    /**
     * 根据key，查询value
     */
    String queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("confKey") String confKey, @Param("confValue") String confValue);

}
