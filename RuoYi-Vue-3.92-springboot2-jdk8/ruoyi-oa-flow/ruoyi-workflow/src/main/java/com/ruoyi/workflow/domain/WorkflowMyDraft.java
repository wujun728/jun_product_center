package com.ruoyi.workflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我起草的流程表对象 t_workflow_my_draft
 * 
 * @author wocurr.com
 * @date 2025-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowMyDraft extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 业务ID */
    private String bizId;

    /** 业务标题 */
    @Excel(name = "业务标题")
    private String bizTitle;

    /** 模板ID */
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 类型，0-动态表单 */
    private String type;

    /** 文档状态，0-草稿，1-审批中，2-已办结，3-已终止 */
    @Excel(name = "文档状态，0-草稿，1-审批中，2-已办结，3-已终止")
    private String status;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

    /** 创建人 */
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bizId", getBizId())
            .append("bizTitle", getBizTitle())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("type", getType())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
