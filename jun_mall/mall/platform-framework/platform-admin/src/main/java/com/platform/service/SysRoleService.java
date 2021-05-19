package com.platform.service;

import com.platform.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysRoleService {

    SysRoleEntity queryObject(String roleId);

    List<SysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(String[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<String> queryRoleIdList(String createUser);
}
