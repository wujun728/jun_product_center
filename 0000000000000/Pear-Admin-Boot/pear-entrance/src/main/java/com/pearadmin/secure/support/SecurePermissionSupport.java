package com.pearadmin.secure.support;

import com.pearadmin.common.config.proprety.SecurityProperty;
import com.pearadmin.system.domain.SysPower;
import com.pearadmin.system.domain.SysUser;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describe: 自定义 Security 权限注解实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@Component
public class SecurePermissionSupport implements PermissionEvaluator {

    @Resource
    private SecurityProperty securityProperty;

    /**
     * Describe: 自定义 Security 权限认证 @hasPermission
     * Param: Authentication
     * Return Boolean
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SysUser securityUserDetails = (SysUser) authentication.getPrincipal();
        if (securityProperty.isSuperAuthOpen() && securityProperty.getSuperAdmin().equals(securityUserDetails.getUsername())) {
            return true;
        }
        List<SysPower> powerList = securityUserDetails.getPowerList();
        Set<String> permissions = new HashSet<>();
        for (SysPower sysPower : powerList) {
            permissions.add(sysPower.getPowerCode());
        }
        return permissions.contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
