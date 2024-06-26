package io.github.wujun728.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.wujun728.system.entity.SysRolePermission;
import io.github.wujun728.system.mapper.SysRolePermissionMapper;
import io.github.wujun728.system.service.RolePermissionService;
import io.github.wujun728.system.vo.req.RolePermissionOperationReqVO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限关联
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements RolePermissionService {
    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {
        List<SysRolePermission> list = new ArrayList<>();
        for (String permissionId : vo.getPermissionIds()) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(vo.getRoleId());
            list.add(sysRolePermission);
        }
        this.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, vo.getRoleId()));
        this.saveBatch(list);
    }

}
