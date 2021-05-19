package com.platform.dao;

import com.platform.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<String> queryRoleIdList(String userId);
}
