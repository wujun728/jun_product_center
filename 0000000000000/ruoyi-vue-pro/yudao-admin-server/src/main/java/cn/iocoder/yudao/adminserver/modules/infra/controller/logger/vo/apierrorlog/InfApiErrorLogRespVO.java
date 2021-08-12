package cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("API 错误日志 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfApiErrorLogRespVO extends InfApiErrorLogBaseVO {

    @ApiModelProperty(value = "编号", required = true, example = "1024")
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "处理时间", required = true)
    private Date processTime;

    @ApiModelProperty(value = "处理用户编号", example = "233")
    private Integer processUserId;

}
