package com.platform.dao;

import com.platform.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色管理
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<String> queryRoleIdList(String createUser);
}
