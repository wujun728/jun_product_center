package cn.iocoder.yudao.adminserver.modules.system.controller.errorcode.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("错误码 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysErrorCodeRespVO extends SysErrorCodeBaseVO {

    @ApiModelProperty(value = "错误码编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "错误码类型", required = true, example = "1", notes = "参见 SysErrorCodeTypeEnum 枚举类")
    private Integer type;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
