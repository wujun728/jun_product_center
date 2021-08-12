package cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.log;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("短信日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysSmsLogPageReqVO extends PageParam {

    @ApiModelProperty(value = "短信渠道编号", example = "10")
    private Long channelId;

    @ApiModelProperty(value = "模板编号", example = "20")
    private Long templateId;

    @ApiModelProperty(value = "手机号", example = "15601691300")
    private String mobile;

    @ApiModelProperty(value = "发送状态", example = "1",  notes = "参见 SysSmsSendStatusEnum 枚举类")
    private Integer sendStatus;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始发送时间")
    private Date beginSendTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束发送时间")
    private Date endSendTime;

    @ApiModelProperty(value = "接收状态", example = "0", notes = "参见 SysSmsReceiveStatusEnum 枚举类")
    private Integer receiveStatus;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始接收时间")
    private Date beginReceiveTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束接收时间")
    private Date endReceiveTime;

}
