package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Describe: 用户接口层
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysUserMapper {

    /**
     * Describe: 根据 username 查询用户
     * Param: username
     * Return: SysUser
     * */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * Describe: 根据 Id 查询用户
     * Param: id
     * Return: SysUser
     * */
    SysUser selectById(@Param("id") String id);

    /**
     * Describe: 根据 SysUser 条件查询用户
     * Param: username
     * Return: SysUser
     * */
    List<SysUser> selectList(SysUser param);

    /**
     * Describe: 根据 SysUser 条件查询用户数量
     * Param: username
     * Return: Integer
     * */
    Integer count(SysUser sysUser);

    /**
     * Describe: 添加用户数据
     * Param: username
     * Return: Integer
     * */
    Integer insert(SysUser sysUser);

    /**
     * Describe: 根据 Id 修改用户
     * Param: username
     * Return: Integer
     * */
    Integer updateById(SysUser sysUser);

    /**
     * Describe: 根据 Id 删除用户
     * Param: username
     * Return: Integer
     * */
    Integer deleteById(String id);


    /**
     * Describe: 根据 Id 批量删除
     * Param: username
     * Return: Integer
     * */
    Integer deleteByIds(String[] ids);

    /**
     * Describe: 重置部门
     * Param: deptId
     * Return: Integer
     * */
    Integer resetDeptByDeptId(String deptId);

    /**
     * Describe: 批量重置部门
     * Param: deptIds
     * Return: Integer
     * */
    Integer resetDeptByDeptIds(String[] deptIds);
}
