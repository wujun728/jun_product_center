package com.nbclass.mapper;

import com.nbclass.model.Role;
import com.nbclass.util.MyMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 根据用户id查询角色集合
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(String userId);

    /**
     * 根据role参数查询角色列表
     * @param role role
     * @return list
     */
    List<Role> selectRoles(Role role);

    /**
     * 根据参数批量更新状态
     * @param params
     * @return int
     */
    int updateStatusBatch(Map<String, Object> params);

    /**
     * 根据roleId更新角色信息
     * @param params
     * @return int
     */
    int updateByRoleId(Map<String, Object> params);



}