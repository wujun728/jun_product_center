package com.platform.service;

import com.platform.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author liepngjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysDeptService {

    SysDeptEntity queryObject(String deptId);

    List<SysDeptEntity> queryList(Map<String, Object> map);

    void save(SysDeptEntity sysDept);

    void update(SysDeptEntity sysDept);

    void delete(String deptId);

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<String> queryDetpIdList(String parentId);

    /**
     * 获取子部门ID(包含本部门ID)，用于数据过滤
     */
    String getSubDeptIdList(String deptId);
}
