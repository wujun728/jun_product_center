package com.pearadmin.system.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysPower;
import com.pearadmin.system.domain.SysRole;
import java.util.List;

/**
 * Describe: 角色服务接口类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public interface ISysRoleService {

    /**
     * Describe: 查询角色数据
     * Param: queryRoleParam
     * Return: 操作结果
     * */
    List<SysRole> list(SysRole queryRoleParam);

    /**
     * Describe: 分页查询角色数据
     * Param: queryRoleParam
     * Param: pageDomain
     * Return: 操作结果
     * */
    PageInfo<SysRole> page(SysRole param, PageDomain pageDomain);

    /**
     * Describe: 保存角色数据
     * Param: SysRole
     * Return: 操作结果
     * */
    boolean save(SysRole sysRole);

    /**
     * Describe: 根据 id 获取角色信息
     * Param: id
     * Return: 操作结果
     * */
    SysRole getById(String id);

    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(SysRole sysRole);

    /**
     * Describe: 获取角色权限
     * Param: roleId
     * Return: 操作结果
     * */
    List<SysPower> getRolePower(String roleId);

    /**
     * Describe: 保存角色权限
     * Param: roleId , powerIds
     * Return: 操作结果
     * */
    Boolean saveRolePower(String roleId,List<String> powerIds);

    /**
     * Describe: 根据 id 删除角色数据
     * Param: id
     * Return: 操作结果
     * */
    Boolean remove(String id);

    /**
     * Describe: 根据 id 删除角色数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean batchRemove(String[] ids);

}
