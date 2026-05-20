package com.ruoyi.workflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 回收站对象 t_workflow_recycle
 * 
 * @author wocurr.com
 * @date 2025-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowRecycle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String procInstId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 业务ID */
    @Excel(name = "业务ID")
    private String businessId;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("procInstId", getProcInstId())
            .append("taskId", getTaskId())
            .append("businessId", getBusinessId())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("delFlag", getDelFlag())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
