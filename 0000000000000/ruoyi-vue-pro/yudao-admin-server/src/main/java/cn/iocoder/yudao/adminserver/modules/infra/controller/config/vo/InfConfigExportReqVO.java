package cn.iocoder.yudao.adminserver.modules.infra.controller.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("参数配置导出 Request VO")
@Data
public class InfConfigExportReqVO {

    @ApiModelProperty(value = "参数名称", example = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "参数键名", example = "yunai.db.username", notes = "模糊匹配")
    private String key;

    @ApiModelProperty(value = "参数类型", example = "1", notes = "参见 SysConfigTypeEnum 枚举")
    private Integer type;

    @ApiModelProperty(value = "开始时间", example = "2020-10-24 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-10-24 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

}
