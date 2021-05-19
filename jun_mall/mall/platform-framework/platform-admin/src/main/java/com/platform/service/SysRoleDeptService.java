package com.platform.service;

import java.util.List;


/**
 * 角色与部门对应关系
 *
 * @author lipengjun
 * @date 2017年9月18日 上午9:18:38
 */
public interface SysRoleDeptService {

    void saveOrUpdate(String roleId, List<String> deptIdList);

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<String> queryDeptIdList(String roleId);

    /**
     * 根据用户ID获取权限部门列表
     *
     * @param userId
     * @return
     */
    List<String> queryDeptIdListByUserId(String userId);
}
