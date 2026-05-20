package com.ruoyi.worksetting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程办理秘书记录对象 t_workflow_secretary_record
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowSecretaryRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 领导ID
     */
    private String leaderId;

    /**
     * 秘书ID
     */
    private String secretaryId;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 待办ID
     */
    private String todoId;

    /**
     * 创建人ID
     */
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("leaderId", getLeaderId())
                .append("secretaryId", getSecretaryId())
                .append("procInstId", getProcInstId())
                .append("taskId", getTaskId())
                .append("todoId", getTodoId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
