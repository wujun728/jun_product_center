package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysRolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Describe: 角色权限接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysRolePowerMapper {

    List<SysRolePower> selectByRoleId(String roleId);

    int batchInsert(List<SysRolePower> sysRolePowers);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    int deleteByPowerId(String powerId);

    int deleteByPowerIds(String[] powerIds);
}
