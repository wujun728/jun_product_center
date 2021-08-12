package cn.iocoder.yudao.adminserver.modules.system.controller.permission.vo.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

@ApiModel("赋予用户角色 Request VO")
@Data
public class SysPermissionAssignUserRoleReqVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色编号列表", example = "1,3,5")
    private Set<Long> roleIds = Collections.emptySet(); // 兜底

}
