package com.wangzhixuan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.mapper.UserRoleMapper;
import com.wangzhixuan.model.UserRole;
import com.wangzhixuan.service.IUserRoleService;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}