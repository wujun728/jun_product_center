package com.platform.dao;

import com.platform.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);
}
