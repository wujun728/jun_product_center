package com.ruoyi.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 待办对象 t_workflow_todo
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Todo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 当前环节
     */
    @Excel(name = "当前环节")
    private String curNode;

    /**
     * 当前处理人
     */
    private String curHandler;

    /**
     * 处理人名称
     */
    @Excel(name = "处理人名称")
    private String curHandlerName;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 发送人名称
     */
    @Excel(name = "发送人名称")
    private String senderName;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 业务表单ID
     */
    private String businessId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 模板ID
     */
    private String templateId;

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
     * 催办标识，0-否，1-是
     */
    private String urgeFlag;

    /**
     * 是否已读，0-未读，1-已读
     */
    private String readFlag;

    /**
     * 是否删除，0-否，1-是
     */
    private String delFlag;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 更新人ID
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("curNode", getCurNode())
                .append("curHandler", getCurHandler())
                .append("curHandlerName", getCurHandlerName())
                .append("sender", getSender())
                .append("senderName", getSenderName())
                .append("sendTime", getSendTime())
                .append("procInstId", getProcInstId())
                .append("businessId", getBusinessId())
                .append("taskId", getTaskId())
                .append("templateId", getTemplateId())
                .append("templateName", getTemplateName())
                .append("type", getType())
                .append("urgencyStatus", getUrgencyStatus())
                .append("urgeFlag", getUrgeFlag())
                .append("readFlag", getReadFlag())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
