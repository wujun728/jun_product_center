package com.ruoyi.flowable.domain.qo;

import lombok.Data;

/**
 * <p> 流程实例查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowProcessInstanceQo {

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
     * 父流程实例ID是否为空
     */
    private Boolean isParentIdEmpty;

    /**
     * 父流程实例ID是否为空
     */
    private Boolean isBusinessKeyNotEmpty;

    /**
     * 排序字段
     */
    private String orderBy;
}
