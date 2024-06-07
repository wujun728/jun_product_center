package io.github.wujun728.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wujun728.system.entity.SysRolePermission;
import io.github.wujun728.system.vo.req.RolePermissionOperationReqVO;

/**
 * 角色权限关联
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface RolePermissionService extends IService<SysRolePermission> {

    /**
     * 角色绑定权限
     *
     * @param vo vo
     */
    void addRolePermission(RolePermissionOperationReqVO vo);
}
