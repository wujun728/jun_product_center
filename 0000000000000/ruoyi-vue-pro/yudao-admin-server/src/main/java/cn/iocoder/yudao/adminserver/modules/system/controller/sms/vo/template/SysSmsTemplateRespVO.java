package cn.iocoder.yudao.adminserver.modules.system.controller.sms.vo.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ApiModel("短信模板 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysSmsTemplateRespVO extends SysSmsTemplateBaseVO {

    @ApiModelProperty(value = "编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "短信渠道编码", required = true, example = "ALIYUN")
    private String channelCode;

    @ApiModelProperty(value = "参数数组", example = "name,code")
    private List<String> params;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
