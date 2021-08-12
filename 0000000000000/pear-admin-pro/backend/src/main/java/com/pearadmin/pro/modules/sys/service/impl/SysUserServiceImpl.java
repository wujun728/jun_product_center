package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.*;
import com.pearadmin.pro.modules.sys.repository.*;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.service.SysUserRoleService;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> role(String userId) {
        return sysRoleRepository.selectRoleByUserId(userId);
    }

    @Override
    public List<SysUser> list(SysUserRequest request) {
        return sysUserRepository.selectUser(request);
    }

    @Override
    public PageResponse<SysUser> page(SysUserRequest request) {
        return Pageable.of(request, (()-> sysUserRepository.selectUser(request)));
    }

    @Override
    public List<SysPower> power(String userId) {
        return sysPowerRepository.selectPowerByUserId(userId);
    }

    @Override
    public List<SysPower> menu(String userId) {
        return toTree(sysPowerRepository.selectMenu(userId),"0");
    }

    @Override
    @Transactional
    public Boolean give(String userId, List<String> roleIds) {
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, userId).remove();
        List<SysUserRole> userRoles = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        return sysUserRoleService.saveBatch(userRoles);
    }

    public List<SysPower> toTree(List<SysPower> sysMenus, String parent) {
        List<SysPower> list = new ArrayList<>();
        for (SysPower menu : sysMenus) {
            if (parent.equals(menu.getParent())) {
                menu.setChildren(toTree(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        sysUserRepository.deleteById(id);
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId,id).remove();
        return true;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }

    @Override
    public List<SysDept> dept(String userId) {
        return sysDeptRepository.selectDeptByUserId(userId);
    }

}
