package com.ruoyi.flowable.domain.vo.process;

import com.ruoyi.common.mybatis.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 流程定义列表 Request VO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BpmProcessDefinitionListReqVO extends PageParam {

    @ApiModelProperty(value = "中断状态", example = "1", notes = "参见 SuspensionState 枚举")
    private Integer suspensionState;

}
