package cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.type;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("字典类型分页列表 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictTypePageReqVO extends PageParam {

    @ApiModelProperty(value = "字典类型名称", example = "芋道", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "字典类型", example = "sys_common_sex", notes = "模糊匹配")
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String type;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
