package cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("参数配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfConfigUpdateReqVO extends InfConfigBaseVO {

    @ApiModelProperty(value = "参数配置序号", required = true, example = "1024")
    @NotNull(message = "参数配置编号不能为空")
    private Long id;

}
