package com.ruoyi.flowable.domain.qo;

import lombok.Data;

import java.util.List;

/**
 * <p> 流程历史环节实例查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowHistoricActivityInstanceQo {

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务ID
     */
    private List<String> taskIds;

    /**
     * 环节ID
     */
    private String activityId;

    /**
     * 环节类型
     */
    private String activityType;

    /**
     * 执行ID
     */
    private String executionId;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 是否完成
     */
    private Boolean isFinished;

    /**
     * 排序字段
     */
    private String orderBy;
}
