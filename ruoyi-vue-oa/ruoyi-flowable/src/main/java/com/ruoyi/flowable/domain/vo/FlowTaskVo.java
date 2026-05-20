package com.ruoyi.flowable.domain.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>流程任务<p>
 *
 * @author wocurr.com
 */
@Data
public class FlowTaskVo {

    /**
     * 任务Id
     */
    private String taskId;

    /**
     * 任务定义Key
     */
    private String taskDefKey;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 任务意见
     */
    private String comment;

    /**
     * 流程实例Id
     */
    private String procInsId;

    /**
     * 待办Id
     */
    private String todoId;

    /**
     * 节点
     */
    private String targetKey;

    /**
     * 流程定义ID
     */
    private String defId;

    /**
     * 子执行流ID, 多个用逗号分隔
     */
    private String currentChildExecutionIds;

    /**
     * 流程变量信息
     */
    private Map<String, Object> variables = new HashMap<>();

    /**
     * 审批人, 多个用逗号分隔
     */
    private String assignee;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 业务标题
     */
    private String title;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 待办类型，1-待办，2-阅办
     */
    private String type;

    /**
     * 处理类型, 0-草稿 1-审批 2-驳回，3-退回，4-取回 5-多实例取回 6-抄送 7-委派 8-转办 9-加签 10-委托 11-秘书办理
     */
    private String handleType;

    /**
     * 紧急状态，0-普通，1-加急，2-紧急，3-特急
     */
    private String urgencyStatus;

    /**
     * 已办Id
     */
    private String doneId;

    /**
     * 委托映射关系，key和value不同时，则存在委托关系
     */
    private Map<String, String> entrustIdMap;
}
