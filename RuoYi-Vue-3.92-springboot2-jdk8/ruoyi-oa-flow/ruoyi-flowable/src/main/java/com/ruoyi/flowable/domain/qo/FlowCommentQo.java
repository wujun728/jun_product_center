package com.ruoyi.flowable.domain.qo;

import lombok.Data;

/**
 * <p> 流程审批意见查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowCommentQo {

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;
}
