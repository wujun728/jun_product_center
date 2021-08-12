package cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("定时任务 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfJobRespVO extends InfJobBaseVO {

    @ApiModelProperty(value = "任务编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "任务状态", required = true, example = "1")
    private Integer status;

    @ApiModelProperty(value = "处理器的名字", required = true, example = "sysUserSessionTimeoutJob")
    @NotNull(message = "处理器的名字不能为空")
    private String handlerName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
