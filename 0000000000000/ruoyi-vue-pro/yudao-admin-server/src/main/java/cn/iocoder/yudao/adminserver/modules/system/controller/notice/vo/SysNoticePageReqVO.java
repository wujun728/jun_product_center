package cn.iocoder.yudao.adminserver.modules.system.controller.notice.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("通知公告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysNoticePageReqVO extends PageParam {

    @ApiModelProperty(value = "通知公告名称", example = "芋道", notes = "模糊匹配")
    private String title;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

}
