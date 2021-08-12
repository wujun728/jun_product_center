package cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("定时任务更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfJobUpdateReqVO extends InfJobBaseVO {

    @ApiModelProperty(value = "任务编号", required = true, example = "1024")
    @NotNull(message = "任务编号不能为空")
    private Long id;

}
