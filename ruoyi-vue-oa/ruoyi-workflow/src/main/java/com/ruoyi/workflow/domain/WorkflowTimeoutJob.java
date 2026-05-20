package com.ruoyi.workflow.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程任务超时记录对象 t_workflow_timeout_job
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowTimeoutJob extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 流程任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String procInstId;

    /**
     * 超时时间
     */
    private Date expireTime;

    /**
     * 创建人
     */
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskId", getTaskId())
                .append("procInstId", getProcInstId())
                .append("expireTime", getExpireTime())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
