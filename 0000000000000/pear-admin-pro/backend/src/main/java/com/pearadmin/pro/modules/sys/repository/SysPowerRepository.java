package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.common.web.interceptor.annotation.TenantIgnore;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysPowerRepository extends BaseMapper<SysPower> {

    /**
     * 获取权限列表
     *
     * @return {@link SysPower}
     * */
    List<SysPower> selectPower();

    /**
     * 获取权限列表
     *
     * @return {@link SysPower}
     * */
    List<SysPower> selectPowerByTenantId(@Param("tenantId") String tenantId);

    /**
     * 根据 userId 获取权限列表
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectPowerByUserId(@Param("userId") String userId);

    /**
     * 根据 roleId 获取权限列表
     *
     * @param roleId 角色编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectPowerByRoleId(@Param("roleId") String roleId);

    /**
     * 根据 userId 获取菜单
     *
     * @param userId 用户编号
     * @return {@link SysPower}
     * */
    List<SysPower> selectMenu(String userId);
}
