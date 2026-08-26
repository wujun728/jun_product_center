package com.ruoyi.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 已办对象 t_workflow_done
 *
 * @author wocurr.com
 */
@Data
public class Done extends BaseEntity {
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
     * 处理环节
     */
    @Excel(name = "处理环节")
    private String handleNode;

    /**
     * 处理人
     */
    @Excel(name = "处理人")
    private String handler;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String handlerName;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date handleTime;

    /**
     * 待办ID
     */
    @Excel(name = "待办ID")
    private String todoId;

    /**
     * 流程实例ID
     */
    @Excel(name = "流程实例ID")
    private String procInstId;

    /**
     * 业务表单ID
     */
    @Excel(name = "业务表单ID")
    private String businessId;

    /**
     * 任务ID
     */
    @Excel(name = "任务ID")
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
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("handleNode", getHandleNode())
                .append("handler", getHandler())
                .append("handlerName", getHandlerName())
                .append("handleTime", getHandleTime())
                .append("todoId", getTodoId())
                .append("procInstId", getProcInstId())
                .append("businessId", getBusinessId())
                .append("taskId", getTaskId())
                .append("templateId", getTemplateId())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
