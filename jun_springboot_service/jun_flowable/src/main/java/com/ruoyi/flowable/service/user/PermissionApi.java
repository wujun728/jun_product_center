package com.ruoyi.flowable.service.user;

import java.util.Collection;
import java.util.Set;

/**
 * 权限 API 接口
 *
 * hasPermi
 */
public interface PermissionApi {

    /**
     * 获得拥有多个角色的用户编号集合
     *
     * @param roleIds 角色编号集合
     * @return 用户编号集合
     */
    Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds);

}
