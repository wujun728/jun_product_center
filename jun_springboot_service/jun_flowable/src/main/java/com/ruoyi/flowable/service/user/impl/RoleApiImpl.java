package com.ruoyi.flowable.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.service.user.RoleApi;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.ROLE_IS_DISABLE;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.ROLE_NOT_EXISTS;


/**
 * 角色 API 实现类
 * <p>
 * hasPermi
 */
@Service
public class RoleApiImpl implements RoleApi {

    @Autowired
    private ISysRoleService roleService;

    @Override
    public void validRoles(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得角色信息
        List<SysRole> roles = roleService.listByIds(ids);
        Map<Long, SysRole> roleMap = CollectionUtils.convertMap(roles, SysRole::getRoleId);
        // 校验
        ids.forEach(id -> {
            SysRole role = roleMap.get(id);
            if (role == null) {
                throw exception(ROLE_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(Integer.parseInt(role.getStatus()))) {
                throw exception(ROLE_IS_DISABLE, role.getRoleName());
            }
        });
    }
}
