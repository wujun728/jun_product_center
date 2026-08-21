package com.ruoyi.flowable.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * 流程意见
 *
 * @author wocurr.com
 */
@Data
public class FlowCommentDto {

    /**
     * 意见id
     */
    private String id;

    /**
     * 意见类型
     */
    private String type;

    /**
     * 意见用户id
     */
    private String userId;

    /**
     * 审批意见时间
     */
    private Date time;

    /**
     * 意见任务id
     */
    private String taskId;

    /**
     * 意见流程实例id
     */
    private String procInstId;

    /**
     * 意见动作
     */
    private String action;

    /**
     * 意见内容
     */
    private String message;

    /**
     * 意见内容
     */
    private String fullMessage;
}
