package cn.iocoder.yudao.adminserver.modules.system.controller.logger.vo.loginlog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("登陆日志分页列表 Request VO")
@Data
public class SysLoginLogExportReqVO {

    @ApiModelProperty(value = "用户 IP", example = "127.0.0.1", notes = "模拟匹配")
    private String userIp;

    @ApiModelProperty(value = "用户账号", example = "芋道", notes = "模拟匹配")
    private String username;

    @ApiModelProperty(value = "操作状态", example = "true")
    private Boolean status;

    @ApiModelProperty(value = "开始时间", example = "2020-10-24")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-10-24")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

}
