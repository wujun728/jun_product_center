package com.pearadmin.system.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysRole;
import com.pearadmin.system.domain.SysUser;
import com.pearadmin.system.domain.SysMenu;
import java.util.List;

/**
 * Describe: 用户服务接口类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public interface ISysUserService {

    /**
     * Describe: 根据条件查询用户列表数据
     * Param: username
     * Return: 返回用户列表数据
     * */
    List<SysUser> list(SysUser param);

    /**
     * Describe: 根据条件查询用户列表数据  分页
     * Param: username
     * Return: 返回分页用户列表数据
     * */
    PageInfo<SysUser> page(SysUser param, PageDomain pageDomain);

    /**
     * Describe: 根据 id 获取用户数据
     * Param: id
     * Return: SysUser
     * */
    SysUser getById(String id);

    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: 操作结果
     * */
    boolean remove(String id);

    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(SysUser sysUser);

    /**
     * Describe: 根据 id 删除用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean batchRemove(String[] ids);

    /**
     * Describe: 保存用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    boolean save(SysUser sysUser);

    /**
     * Describe: 保存用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    boolean saveUserRole(String userId,List<String> roleIds);

    /**
     * Describe: 获取用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    List<SysRole> getUserRole(String userId);

    /**
     * Describe: 获取用户菜单数据
     * Param: SysUser
     * Return: 操作结果
     * */
    List<SysMenu> getUserMenu(String username);

    /**
     * Describe: 递归获取菜单tree
     * Param: sysMenus
     * Return: 操作结果
     * */
    List<SysMenu> toUserMenu(List<SysMenu> sysMenus,String parentId);

}

