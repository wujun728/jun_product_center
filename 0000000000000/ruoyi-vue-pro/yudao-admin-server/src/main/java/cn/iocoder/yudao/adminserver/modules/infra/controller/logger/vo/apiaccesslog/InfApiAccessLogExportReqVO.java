package cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apiaccesslog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "API 访问日志 Excel 导出 Request VO", description = "参数和 InfApiAccessLogPageReqVO 是一致的")
@Data
public class InfApiAccessLogExportReqVO {

    @ApiModelProperty(value = "用户编号", example = "666")
    private Long userId;

    @ApiModelProperty(value = "用户类型", example = "2")
    private Integer userType;

    @ApiModelProperty(value = "应用名", example = "dashboard")
    private String applicationName;

    @ApiModelProperty(value = "请求地址", example = "/xxx/yyy", notes = "模糊匹配")
    private String requestUrl;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始开始请求时间")
    private Date beginBeginTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束开始请求时间")
    private Date endBeginTime;

    @ApiModelProperty(value = "执行时长", example = "100", notes = "大于等于，单位：毫秒")
    private Integer duration;

    @ApiModelProperty(value = "结果码", example = "0")
    private Integer resultCode;

}
