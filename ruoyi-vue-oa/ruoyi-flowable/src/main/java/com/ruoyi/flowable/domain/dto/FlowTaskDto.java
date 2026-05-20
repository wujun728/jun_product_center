package com.ruoyi.flowable.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.flowable.domain.vo.FlowCommentVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>工作流任务<p>
 *
 * @author wocurr.com
 */
@Getter
@Setter
public class FlowTaskDto implements Serializable {

    /** 任务编号 */
    private String taskId;

    /** 任务执行编号 */
    private String executionId;

    /** 任务名称 */
    private String taskName;

    /** 任务Key */
    private String taskDefKey;

    /** 任务执行人Id，多个逗号分隔 */
    private String assignee;

    /** 任务执行人Id */
    private String assigneeId;

    /** 部门名称 */
    private String deptName;

    /** 流程发起人部门名称 */
    private String startDeptName;

    /** 任务执行人名称 */
    private String assigneeName;

    /** 任务执行人部门 */
    private String assigneeDeptName;

    /** 流程发起人Id */
    private String startUserId;

    /** 流程发起人名称 */
    private String startUserName;

    /** 流程类型 */
    private String category;

    /** 流程变量信息 */
    private Object variables;

    /** 局部变量信息 */
    private Object taskLocalVars;

    /** 流程部署编号 */
    private String deployId;

    /** 流程ID */
    private String procDefId;

    /** 流程环节id */
    private String actId;

    /** 流程key */
    private String procDefKey;

    /** 流程定义名称 */
    private String procDefName;

    /** 流程定义内置使用版本 */
    private int procDefVersion;

    /** 流程实例ID */
    private String procInsId;

    /** 历史流程实例ID */
    private String hisProcInsId;

    /** 任务耗时 */
    private String duration;

    /** 任务意见 */
    private FlowCommentVo comment;

    /** 候选执行人 */
    private String candidate;

    /** 任务创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 任务完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 审批状态，0-未处理，1-已提交
     */
    private String status;

}
