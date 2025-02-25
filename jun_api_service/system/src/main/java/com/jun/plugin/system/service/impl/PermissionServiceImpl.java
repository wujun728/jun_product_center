package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.system.entity.SysPermission;
import com.jun.plugin.system.entity.SysRolePermission;
import com.jun.plugin.system.entity.SysUserRole;
import com.jun.plugin.system.mapper.SysPermissionMapper;
import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.system.service.PermissionService;
import com.jun.plugin.system.service.RolePermissionService;
import com.jun.plugin.system.service.UserRoleService;
import com.jun.plugin.system.vo.resp.PermissionRespNode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单权限
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements PermissionService {
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    @Lazy
    private HttpSessionService httpSessionService;

    /**
     * 根据用户查询拥有的权限
     * 先查出用户拥有的角色
     * 再去差用户拥有的权限
     * 也可以多表关联查询
     */
    @Override
    public List<SysPermission> getPermission(String userId) {
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        List<Object> permissionIds = rolePermissionService.listObjs(Wrappers.<SysRolePermission>lambdaQuery().select(SysRolePermission::getPermissionId).in(SysRolePermission::getRoleId, roleIds));
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }

        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.<SysPermission>lambdaQuery().in(SysPermission::getId, permissionIds).orderByAsc(SysPermission::getOrderNum);
        return sysPermissionMapper.selectList(queryWrapper);
    }

    /**
     * 删除菜单权限
     * 判断是否 有角色关联
     * 判断是否有子集
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(String permissionId) {
        //获取关联userId
        List<String> userIds = getUserIdsById(permissionId);
        SysPermission sysPermission = sysPermissionMapper.selectById(permissionId);
        if (null == sysPermission) {
            log.error("传入 的 id:{}不合法", permissionId);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        //获取下一级
        List<SysPermission> childs = sysPermissionMapper.selectList(Wrappers.<SysPermission>lambdaQuery().eq(SysPermission::getPid, permissionId));
        if (!CollectionUtils.isEmpty(childs)) {
//            throw new BusinessException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        	childs.forEach(item->{
        		sysPermissionMapper.deleteById(item.getId());
        	});
        }
        sysPermissionMapper.deleteById(permissionId);
        //删除和角色关联
        rolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getPermissionId, permissionId));

        if (!CollectionUtils.isEmpty(userIds)) {
            //刷新权限
            userIds.parallelStream().forEach(httpSessionService::refreshUerId);
        }

    }

    @Override
    public void updatePermission(SysPermission vo) {
        sysPermissionMapper.updateById(vo);
        httpSessionService.refreshPermission(vo.getId());
    }

    /**
     * 获取所有菜单权限
     */
    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> result = sysPermissionMapper.selectList(Wrappers.<SysPermission>lambdaQuery().orderByAsc(SysPermission::getOrderNum));
        if (!CollectionUtils.isEmpty(result)) {
            for (SysPermission sysPermission : result) {
            	SysPermission parent = null;
            	for (SysPermission sysPermission2 : result) {
            		if(sysPermission2.getId().equalsIgnoreCase(sysPermission.getPid())) {
            			parent = sysPermission2;
            		}
            	}
                //SysPermission parent = sysPermissionMapper.selectById(sysPermission.getPid());
                if (parent != null) {
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return result;
    }

    /**
     * 获取权限标识
     */
    @Override
    public Set<String> getPermissionsByUserId(String userId) {

        List<SysPermission> list = getPermission(userId);
        Set<String> permissions = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (SysPermission sysPermission : list) {
            if (!StringUtils.isEmpty(sysPermission.getPerms())) {
                permissions.add(sysPermission.getPerms());
            }

        }
        return permissions;
    }

    /**
     * 以树型的形式把用户拥有的菜单权限返回给客户端
     */
    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        List<SysPermission> list = getPermission(userId);
        return getTree(list, true);
    }

    /**
     * 递归获取菜单树
     */
    private List<PermissionRespNode> getTree(List<SysPermission> all, boolean type) {

        List<PermissionRespNode> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(all)) {
            return list;
        }
        for (SysPermission sysPermission : all) {
            if ("0".equals(sysPermission.getPid())) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setValue(sysPermission.getUrl());

                if (type) {
                    permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                    permissionRespNode.setChild(getChildExcBtn(sysPermission.getId(), all));
                } else {
                    permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                    permissionRespNode.setChild(getChildAll(sysPermission.getId(), all));
                }
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /**
     * 递归遍历所有
     */
    private List<PermissionRespNode> getChildAll(String id, List<SysPermission> all) {

        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id)) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setValue(sysPermission.getUrl());
                permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                permissionRespNode.setChild(getChildAll(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /**
     * 只递归获取目录和菜单
     */
    private List<PermissionRespNode> getChildExcBtn(String id, List<SysPermission> all) {

        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id) && sysPermission.getType() != 3) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setValue(sysPermission.getUrl());
                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                permissionRespNode.setChild(getChildExcBtn(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /**
     * 获取所有菜单权限按钮
     */
    @Override
    public List<PermissionRespNode> selectAllByTree() {

        List<SysPermission> list = selectAll();
        return getTree(list, false);
    }

    /**
     * 获取所有的目录菜单树排除按钮
     * 因为不管是新增或者修改
     * 选择所属菜单目录的时候
     * 都不可能选择到按钮
     * 而且编辑的时候 所属目录不能
     * 选择自己和它的子类
     */
    @Override
    public List<PermissionRespNode> selectAllMenuByTree(String permissionId) {

        List<SysPermission> list = selectAll();
        if (!CollectionUtils.isEmpty(list) && !StringUtils.isEmpty(permissionId)) {
            for (SysPermission sysPermission : list) {
                if (sysPermission.getId().equals(permissionId)) {
                    list.remove(sysPermission);
                    break;
                }
            }
        }
        List<PermissionRespNode> result = new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode = new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list, true));
        respNode.setChild(getTree(list, true));
        result.add(respNode);
        return result;
    }

    @Override
    public List getUserIdsById(String id) {
        //根据权限id，获取所有角色id
        //根据权限id，获取所有角色id
        List<Object> roleIds = rolePermissionService.listObjs(Wrappers.<SysRolePermission>lambdaQuery().select(SysRolePermission::getRoleId).eq(SysRolePermission::getPermissionId, id));
        if (!CollectionUtils.isEmpty(roleIds)) {
            //根据角色id， 获取关联用户
            return userRoleService.listObjs(Wrappers.<SysUserRole>lambdaQuery().select(SysUserRole::getUserId).in(SysUserRole::getRoleId, roleIds));
        }
        return null;
    }


}
