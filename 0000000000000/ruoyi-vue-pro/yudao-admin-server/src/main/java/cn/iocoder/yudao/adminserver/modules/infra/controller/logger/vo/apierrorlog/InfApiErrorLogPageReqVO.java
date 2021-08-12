package cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("API 错误日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfApiErrorLogPageReqVO extends PageParam {

    @ApiModelProperty(value = "用户编号", example = "666")
    private Long userId;

    @ApiModelProperty(value = "用户类型", example = "1")
    private Integer userType;

    @ApiModelProperty(value = "应用名", example = "dashboard")
    private String applicationName;

    @ApiModelProperty(value = "请求地址", example = "/xx/yy")
    private String requestUrl;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始异常发生时间")
    private Date beginExceptionTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束异常发生时间")
    private Date endExceptionTime;

    @ApiModelProperty(value = "处理状态", example = "0")
    private Integer processStatus;

}
