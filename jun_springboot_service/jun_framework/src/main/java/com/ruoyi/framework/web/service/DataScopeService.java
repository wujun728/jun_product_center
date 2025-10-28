package com.ruoyi.framework.web.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.DataScopeAspect;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据权限范围操作类
 * @author wangzongrun
 */

@Component
public class DataScopeService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 根据用户id集合获取数据范围id集合
     *
     * @param userId 用户id集合
     * @param orgId  机构id
     * @return 数据范围id集合
     * @author wangzongrun
     */
    public List<Long> getUserDataScopeIdList(Long userId, Long orgId){
        List<Long> roleIdList = CollectionUtil.newArrayList();
        // 获取用户所有角色
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        userRoleMapper.selectList(queryWrapper).forEach(sysUserRole -> roleIdList.add(sysUserRole.getRoleId()));
        // 获取这些角色对应的数据范围
        if (ObjectUtil.isNotEmpty(roleIdList)) {
            return getUserDataScopeIdList(roleIdList, orgId);
        }
        return CollectionUtil.newArrayList();
    }

    /**
     * 根据角色id集合获取数据范围id集合
     *
     * @param roleIdList 角色id集合
     * @param orgId      机构id
     * @return 数据范围id集合
     */
    public List<Long> getUserDataScopeIdList(List<Long> roleIdList, Long orgId) {
        Set<Long> resultList = CollectionUtil.newHashSet();

        //定义角色中最大数据范围的类型，目前系统按最大范围策略来，如果你同时拥有ALL和SELF的权限，系统最后按ALL返回
        int strongerDataScopeType = Integer.parseInt(DataScopeAspect.DATA_SCOPE_SELF);

        //获取用户自定义数据范围的角色集合
        List<Long> customDataScopeRoleIdList = CollectionUtil.newArrayList();
        if (ObjectUtil.isNotEmpty(roleIdList)) {
            List<SysRole> sysRoleList = sysRoleService.listByIds(roleIdList);
            for (SysRole sysRole : sysRoleList) {
                if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(sysRole.getDataScope())) {
                    customDataScopeRoleIdList.add(sysRole.getRoleId());
                } else {
                    if (Integer.parseInt(sysRole.getDataScope()) <= strongerDataScopeType) {
                        strongerDataScopeType = Integer.parseInt(sysRole.getDataScope());
                    }
                }
            }
        }

        //自定义数据范围的角色对应的数据范围
        List<Long> roleDataScopeIdList = getRoleDataScopeIdList(customDataScopeRoleIdList);

        //角色中拥有最大数据范围类型的数据范围
        List<Long> dataScopeIdList = getDataScopeListByDataScopeType(strongerDataScopeType, orgId);

        resultList.addAll(dataScopeIdList);
        resultList.addAll(roleDataScopeIdList);
        return CollectionUtil.newArrayList(resultList);
    }

    /**
     * 根据角色id获取角色数据范围集合
     *
     * @param roleIdList 角色id集合
     * @return 数据范围id集合
     */
    List<Long> getRoleDataScopeIdList(List<Long> roleIdList){
        List<Long> resultList = CollectionUtil.newArrayList();
        if (ObjectUtil.isNotEmpty(roleIdList)) {
            LambdaQueryWrapper<SysRoleDept> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SysRoleDept::getRoleId, roleIdList);
            sysRoleDeptMapper.selectList(queryWrapper).forEach(sysRoleDataScope -> resultList.add(sysRoleDataScope.getDeptId()));
        }
        return resultList;
    }

    /**
     * 根据数据范围类型获取当前登录用户的数据范围id集合
     *
     * @param dataScopeType 数据范围类型（1全部数据 2本部门及以下数据 3本部门数据 4仅本人数据）
     * @param orgId   组织机构id
     * @return 数据范围id集合
     */
    public List<Long> getDataScopeListByDataScopeType(Integer dataScopeType, Long orgId) {
        List<Long> resultList = CollectionUtil.newArrayList();

        if (ObjectUtil.isEmpty(orgId)) {
            return CollectionUtil.newArrayList();
        }

        // 如果是范围类型是全部数据，则获取当前系统所有的组织架构id
        switch (dataScopeType.toString()) {
            case DataScopeAspect.DATA_SCOPE_ALL: {
                LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysDept::getStatus, CommonStatusEnum.ENABLE.getStatus());
                List<SysDept> list = sysDeptService.list(queryWrapper);
                resultList = list.stream().map(SysDept::getDeptId).collect(Collectors.toList());
                break;
            }
            // 如果范围类型是本部门及以下部门，则查询本节点和子节点集合，包含本节点
            case DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD: {
                LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysDept::getStatus, CommonStatusEnum.ENABLE.getStatus());
                String format = StringUtils.format(" AND dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )", orgId, orgId);
                queryWrapper.last(format);
                List<SysDept> list = sysDeptService.list(queryWrapper);
                resultList = list.stream().map(SysDept::getDeptId).collect(Collectors.toList());
                break;
            }
            // 如果数据范围是本部门，不含子节点，则直接返回本部门
            case DataScopeAspect.DATA_SCOPE_SELF:
                resultList.add(orgId);
                break;
            default:
                break;
        }
        return resultList;
    }

}
