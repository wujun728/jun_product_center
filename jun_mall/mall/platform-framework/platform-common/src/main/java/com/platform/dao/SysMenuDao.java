package com.platform.dao;

import com.platform.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param map
     */
    List<SysMenuEntity> queryListParentId(Map<String, Object> map);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenuEntity> queryUserList(String userId);

    /**
     * 查询用户的权限列表
     */
    String queryMaxIdByParentId(String parentId);
}
