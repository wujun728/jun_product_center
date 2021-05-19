package com.platform.service;

import com.platform.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;


/**
 * 菜单管理
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysMenuService {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList, String domainId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenuEntity> getUserMenuList(String userId, String domainId);


    /**
     * 查询菜单
     */
    SysMenuEntity queryObject(String menuId);

    /**
     * 查询菜单列表
     */
    List<SysMenuEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存菜单
     */
    void save(SysMenuEntity menu);

    /**
     * 修改
     */
    void update(SysMenuEntity menu);

    /**
     * 删除
     */
    void deleteBatch(String[] menuIds);

    /**
     * 查询用户的权限列表
     */
    List<SysMenuEntity> queryUserList(String userId);
}
