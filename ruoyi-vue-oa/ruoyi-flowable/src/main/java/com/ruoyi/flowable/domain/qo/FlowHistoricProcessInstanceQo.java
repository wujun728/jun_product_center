package com.ruoyi.flowable.domain.qo;

import lombok.Data;

/**
 * <p> 历史流程实例查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowHistoricProcessInstanceQo {

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 环节ID
     */
    private String activityId;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 流程定义名称
     */
    private String procDefName;

    /**
     * 业务key
     */
    private String businessKey;

    /**
     * 排序字段
     */
    private String orderBy;
}
