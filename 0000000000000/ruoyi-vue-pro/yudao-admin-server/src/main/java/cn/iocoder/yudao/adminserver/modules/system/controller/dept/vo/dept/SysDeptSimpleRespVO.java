package cn.iocoder.yudao.adminserver.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("部门精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptSimpleRespVO {

    @ApiModelProperty(value = "部门编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "部门名称", required = true, example = "芋道")
    private String name;

    @ApiModelProperty(value = "父部门 ID", required = true, example = "1024")
    private Long parentId;

}
