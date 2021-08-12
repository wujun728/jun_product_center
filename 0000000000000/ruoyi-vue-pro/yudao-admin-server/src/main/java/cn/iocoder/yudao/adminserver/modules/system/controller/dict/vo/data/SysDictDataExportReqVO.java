package cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@ApiModel("字典类型导出 Request VO")
@Data
public class SysDictDataExportReqVO {

    @ApiModelProperty(value = "字典标签", example = "芋道")
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;

    @ApiModelProperty(value = "字典类型", example = "sys_common_sex", notes = "模糊匹配")
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

}
