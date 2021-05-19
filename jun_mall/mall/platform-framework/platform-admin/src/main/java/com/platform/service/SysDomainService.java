package com.platform.service;

import com.platform.entity.SysDomainEntity;

import java.util.List;
import java.util.Map;

/**
 * 域对象Service接口
 *
 * @author lipengjun
 * @date 2017-11-20 18:05:59
 */
public interface SysDomainService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysDomainEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysDomainEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param domain 实体
     * @return 保存条数
     */
    int save(SysDomainEntity domain);

    /**
     * 根据主键更新实体
     *
     * @param domain 实体
     * @return 更新条数
     */
    int update(SysDomainEntity domain);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(String[] ids);
}
