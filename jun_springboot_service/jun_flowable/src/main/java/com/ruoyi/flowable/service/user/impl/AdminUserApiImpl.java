package com.ruoyi.flowable.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.convert.user.UserConvert;
import com.ruoyi.flowable.domain.dto.user.AdminUserRespDTO;
import com.ruoyi.flowable.service.user.AdminUserApi;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.user.ErrorCodeConstants.USER_NOT_EXISTS;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.USER_IS_DISABLE;

/**
 * Admin 用户 API 实现类
 * <p>
 * hasPermi
 */
@Service
public class AdminUserApiImpl implements AdminUserApi {

    @Autowired
    private ISysUserService userService;

    @Override
    public AdminUserRespDTO getUser(Long id) {
        SysUser sysUser = userService.selectUserById(id);
        return UserConvert.INSTANCE.convert(sysUser);
    }

    @Override
    public List<AdminUserRespDTO> getUsers(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<SysUser> sysUsers = userService.listByIds(ids);
        return UserConvert.INSTANCE.convertList(sysUsers);
    }

    @Override
    public List<AdminUserRespDTO> getUsersByDeptIds(Collection<Long> deptIds) {
        if (CollUtil.isEmpty(deptIds)) {
            return Collections.emptyList();
        }
        List<SysUser> sysUsers = userService.list(Wrappers.<SysUser>query().lambda().in(SysUser::getDeptId, deptIds));
        return UserConvert.INSTANCE.convertList(sysUsers);
    }

    @Override
    public List<AdminUserRespDTO> getUsersByPostIds(Collection<Long> postIds) {
        List<SysUser> sysUsers = userService.getUsersByPostIds(postIds);
        return UserConvert.INSTANCE.convertList(sysUsers);
    }

    @Override
    public void validUsers(Set<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得岗位信息
        List<SysUser> users = userService.listByIds(ids);
        Map<Long, SysUser> userMap = CollectionUtils.convertMap(users, SysUser::getUserId);
        // 校验
        ids.forEach(id -> {
            SysUser user = userMap.get(id);
            if (user == null) {
                throw exception(USER_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(Integer.parseInt(user.getStatus()))) {
                throw exception(USER_IS_DISABLE, user.getNickName());
            }
        });

    }
}
