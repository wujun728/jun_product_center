package com.ruoyi.flowable.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p> 流程历史环节实例 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowHistoricActivityInstanceDto {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 环节ID
     */
    private String activityId;

    /**
     * 环节名称
     */
    private String activityName;

    /**
     * 环节类型
     */
    private String activityType;

    /**
     * 执行ID
     */
    private String executionId;

    /**
     * 处理人
     */
    private String assignee;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 被调用流程实例ID
     */
    private String callProcInstId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 删除理由
     */
    private String deleteReason;

    /**
     * 耗时
     */
    private Long durationInMillis;
}
