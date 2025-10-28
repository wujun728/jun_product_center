package com.ruoyi.flowable.domain.vo.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wangzongrun
 */
@ApiModel("流程节点对象")
@Data
public class FlowNodeVo implements Serializable {

    @ApiModelProperty(value = "节点id")
    private String nodeId;

    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    @ApiModelProperty(value = "执行人的code")
    private String userCode;

    @ApiModelProperty(value = "执行人姓名")
    private String userName;

    @ApiModelProperty(value = "任务节点结束时间")
    private Date endTime;

}
