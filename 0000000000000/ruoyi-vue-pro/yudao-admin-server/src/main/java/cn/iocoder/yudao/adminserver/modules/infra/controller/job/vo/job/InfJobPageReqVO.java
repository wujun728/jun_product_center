package cn.iocoder.yudao.adminserver.modules.infra.controller.job.vo.job;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("定时任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfJobPageReqVO extends PageParam {

    @ApiModelProperty(value = "任务名称", example = "测试任务", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "任务状态", example = "1", notes = "参见 InfJobStatusEnum 枚举")
    private Integer status;

    @ApiModelProperty(value = "处理器的名字", example = "sysUserSessionTimeoutJob", notes = "模糊匹配")
    private String handlerName;

}
