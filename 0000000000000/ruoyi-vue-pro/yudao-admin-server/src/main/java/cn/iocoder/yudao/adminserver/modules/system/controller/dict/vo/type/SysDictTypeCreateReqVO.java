package cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("字典类型创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeCreateReqVO extends SysDictTypeBaseVO {

    @ApiModelProperty(value = "字典类型", required = true, example = "sys_common_sex")
    @NotNull(message = "字典类型不能为空")
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String type;

}
