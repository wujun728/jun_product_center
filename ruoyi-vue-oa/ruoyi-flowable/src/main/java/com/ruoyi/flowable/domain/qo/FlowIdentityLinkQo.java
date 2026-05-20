package com.ruoyi.flowable.domain.qo;

import lombok.Data;

import java.util.List;

/**
 * <p> 流程处理人身份查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowIdentityLinkQo {

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务ID列表
     */
    private List<String> taskIds;
}
