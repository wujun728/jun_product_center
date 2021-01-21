package com.nbclass.service;

import com.nbclass.model.Permission;

import java.util.List;
import java.util.Set;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
public interface PermissionService {

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return set
     */
    Set<String> findPermsByUserId(String userId);

    /**
     * 查询全部权限
     * @param status
     * @return list
     */
    List<Permission> selectAll(Integer status);

    /**
     * 查询全部菜单
     * @param status
     * @return list
     */
    List<Permission> selectAllMenuName(Integer status);

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return list
     */
    List<Permission> selectMenuByUserId(String userId);

    /**
     * 插入权限
     * @param permission
     * @return int
     */
    int insert(Permission permission);

    /**
     * 根据权限id更新状态
     * @param permissionId
     * @param status
     * @return int
     */
    int updateStatus(String permissionId, Integer status);

    /**
     * 根据权限id查询权限
     * @param permissionId
     * @return permission
     */
    Permission findByPermissionId(String permissionId);

    /**
     * 根据id查询权限
     * @param id
     * @return permission
     */
    Permission findById(Integer id);

    /**
     * 更新权限
     * @param permission
     * @return int
     */
    int updateByPermissionId(Permission permission);

    /**
     * 查询子权限条数
     * @param permissionId
     * @return int
     */
    int selectSubPermsByPermissionId(String permissionId);
}
