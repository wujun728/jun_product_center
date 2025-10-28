package com.ruoyi.flowable.service.user.impl;

import com.ruoyi.flowable.service.user.PermissionApi;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * 权限 API 实现类
 *
 * hasPermi
 */
@Service
public class PermissionApiImpl implements PermissionApi {

    @Autowired
    private ISysUserService userService;

    @Override
    public Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return userService.getUserRoleIdListByRoleIds(roleIds);
    }
}
