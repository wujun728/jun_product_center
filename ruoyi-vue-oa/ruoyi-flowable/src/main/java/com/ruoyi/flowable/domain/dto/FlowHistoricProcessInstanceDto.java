package com.ruoyi.flowable.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p> 历史流程实例 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowHistoricProcessInstanceDto {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 版本
     */
    private String rev;
    /**
     * 流程实例ID
     */
    private String procInstId;
    /**
     * 业务key
     */
    private String businessKey;
    /**
     * 流程定义ID
     */
    private String procDefId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 耗时
     */
    private Long durationInMillis;
    /**
     * 开始用户ID
     */
    private String startUserId;
    /**
     * 开始活动ID
     */
    private String startActId;
    /**
     * 结束活动ID
     */
    private String endActId;
    /**
     * 父流程实例ID
     */
    private String superProcessInstanceId;
    /**
     * 删除原因
     */
    private String deleteReason;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 回调ID
     */
    private String callbackId;
    /**
     * 回调类型
     */
    private String callbackType;
    private String referenceId;
    /**
     * 引用类型
     */
    private String referenceType;
    /**
     * 阶段实例ID
     */
    private String propagatedStageInstanceId;
    /**
     * 业务状态
     */
    private String businessStatus;
    /**
     * 流程定义KEY
     */
    private String procDefKey;
    /**
     * 流程定义名称
     */
    private String procDefName;
    /**
     * 流程定义版本
     */
    private String procDefVersion;
    /**
     * 部署ID
     */
    private String deploymentId;
}
