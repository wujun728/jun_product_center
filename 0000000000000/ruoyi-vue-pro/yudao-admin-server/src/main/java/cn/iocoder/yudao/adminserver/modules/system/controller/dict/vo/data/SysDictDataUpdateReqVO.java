package cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@ApiModel("字典数据更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictDataUpdateReqVO extends SysDictDataBaseVO {

    @ApiModelProperty(value = "字典数据编号", required = true, example = "1024")
    @NotNull(message = "字典数据编号不能为空")
    private Long id;

}
