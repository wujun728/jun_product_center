package com.ruoyi.flowable.domain.dto;

import lombok.Data;

/**
 * <p> 流程处理人身份 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowIdentityLinkDto {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String procInstId;
}
