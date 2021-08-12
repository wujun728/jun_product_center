package cn.iocoder.yudao.adminserver.modules.system.controller.permission.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@ApiModel("角色信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysRoleRespVO extends SysRoleBaseVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "数据范围", required = true, example = "1", notes = "参见 DataScopeEnum 枚举类")
    private Integer dataScope;

    @ApiModelProperty(value = "数据范围(指定部门数组)", example = "1")
    private Set<Long> dataScopeDeptIds;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty(value = "角色类型", required = true, example = "1", notes = "参见 SysRoleTypeEnum 枚举类")
    private Integer type;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
