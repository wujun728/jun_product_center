package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Describe: 系统角色接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysRoleMapper {
    /**
     * Describe: 根据 username 查询用户权限
     * Param: username
     * Return: SysPower
     * */
    List<SysRole> selectByUsername(String username);
    /**
     * Describe: 查询角色列表
     * Param: SysRole
     * Return: List<SysRole>
     * */
     List<SysRole> selectList(SysRole param);

    /**
     * Describe: 添加角色数据
     * Param: SysRole
     * Return: 执行结果
     * */
    Integer insert(SysRole sysRole);

    /**
     * Describe: 根据 Id 查询角色
     * Param: id
     * Return: SysRole
     * */
    SysRole selectById(@Param("id") String id);

    /**
     * Describe: 根据 Id 修改角色
     * Param: SysRole
     * Return: Integer
     * */
    Integer updateById(SysRole sysRole);

    /**
     * Describe: 根据 Id 删除用户
     * Param: id
     * Return: Integer
     * */
    Integer deleteById(String id);

    /**
     * Describe: 根据 Id 批量删除
     * Param: ids
     * Return: Integer
     * */
    Integer deleteByIds(String[] ids);

}
