package com.ruoyi.flowable.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 流程实例信息
 *
 * @author wocurr.com
 */
@Data
public class FlowInstanceInfoVo {
    /**
     * 流程实例ID
     */
    private String processInstanceId;
    /**
     * 执行ID
     */
    private String executionId;
    /**
     * 父执行ID
     */
    private String parentExecutionId;
    /**
     * 业务key
     */
    private String businessKey;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 是否挂起
     */
    private Boolean suspended;
    /**
     * 是否结束
     */
    private Boolean ended;
    /**
     * 是否存活
     */
    private Boolean active;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 发起人
     */
    private String startUserId;
    /**
     * 当前节点
     */
    String currentTask;
    /**
     * 当前节点
     */
    private String currentTaskIds;
    /**
     * 当前节点定义key
     */
    private String currentTaskDefKeys;
    /**
     * 当前办理人
     */
    private String assignee;
    /**
     * 流程部署ID
     */
    private String deployId;
    /**
     * 流程定义ID
     */
    private String procDefId;
    /**
     * 业务标题
     */
    private String title;
}
