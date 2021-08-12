package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Describe: 用户角色接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysUserRoleMapper {

    int batchInsert(List<SysUserRole> sysUserRoles);

    int deleteByUserId(String userId);

    int deleteByUserIds(String[] userIds);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    List<SysUserRole> selectByUserId(String userId);
}
