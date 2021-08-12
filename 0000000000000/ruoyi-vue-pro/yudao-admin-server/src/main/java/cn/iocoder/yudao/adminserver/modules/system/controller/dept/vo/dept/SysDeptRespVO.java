package cn.iocoder.yudao.adminserver.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel("部门信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptRespVO extends SysDeptBaseVO {

    @ApiModelProperty(value = "部门编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
