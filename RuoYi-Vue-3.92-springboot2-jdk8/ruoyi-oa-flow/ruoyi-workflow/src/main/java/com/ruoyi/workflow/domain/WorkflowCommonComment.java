package com.ruoyi.workflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流程常用意见对象 t_workflow_common_comment
 * 
 * @author wocurr.com
 * @date 2025-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowCommonComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 意见 */
    @Excel(name = "意见")
    private String comment;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("comment", getComment())
            .append("createTime", getCreateTime())
            .toString();
    }
}
