package com.ruoyi.workflow.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流程表单对象 t_workflow_form
 *
 * @author wocurr.com
 */
@Data
public class Form extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 表单标题
     */
    @Excel(name = "表单标题")
    private String title;

    /**
     * 表单数据
     */
    @Excel(name = "表单数据")
    private String formData;

    /**
     * 模板ID
     */
    @Excel(name = "模板ID")
    private String templateId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateId;

    /**
     * 待办ID
     */
    private String todoId;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("formData", getFormData())
                .append("templateId", getTemplateId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .append("todoId", getTodoId())
                .append("procInstId", getProcInstId())
                .append("taskId", getTaskId())
                .toString();
    }
}
