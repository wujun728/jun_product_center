package com.nbclass.service;

import com.nbclass.model.Permission;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.vo.base.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
public interface RoleService {

    /**
     * 根据用户id查询角色集合
     * @param userId
     * @return set
     */
    Set<String> findRoleByUserId(String userId);

    /**
     * 根据条件查询角色列表
     * @param role
     * @return list
     */
    List<Role> selectRoles(Role role);

    /**
     * 插入角色
     * @param role
     * @return int
     */
    int insert(Role role);

    /**
     * 批量更新状态
     * @param roleIds
     * @param status
     * @return int
     */
    int updateStatusBatch(List<String> roleIds, Integer status);

    /**
     * 根据id查询角色
     * @param id
     * @return role
     */
    Role findById(Integer id);

    /**
     * 根据角色id更新角色信息
     * @param role
     * @return int
     */
    int updateByRoleId(Role role);

    /**
     * 根据角色id查询权限集合
     * @param roleId
     * @return list
     */
    List<Permission> findPermissionsByRoleId(String roleId);

    /**
     * 根据角色id保存分配权限
     * @param roleId
     * @param permissionIdsList
     * @return list
     */
    ResponseVo addAssignPermission(String roleId, List<String> permissionIdsList);

    /**
     * 根据角色id下的所有用户
     * @param roleId
     * @return list
     */
    List<User> findByRoleId(String roleId);

    /**
     * 根据角色id下的所有用户
     * @param roleIds
     * @return list
     */
    List<User> findByRoleIds(List<String> roleIds);


}
