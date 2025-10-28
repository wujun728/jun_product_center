package com.ruoyi.flowable.domain.vo.task;


import lombok.Data;

/**
 * @author wangzongrun
 */
@Data
public class BackTaskVo {

    /**
     * 需要驳回的节点id 必填
     */
    private String distFlowElementId;

    /**
     * 操作人code 必填
     */
    private String userCode;

    /**
     * 任务id 必填
     */
    private String taskId;

    /**
     * 流程实例的id 必填
     */
    private String processInstanceId;

    /**
     * 审批意见 必填
     */
    private String message;

    /**
     * 审批类型 必填
     */
    private String type;

}
