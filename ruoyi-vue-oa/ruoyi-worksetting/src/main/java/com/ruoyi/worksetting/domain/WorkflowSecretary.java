package com.ruoyi.worksetting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程办理秘书对象 t_workflow_secretary
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowSecretary extends BaseEntity {
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
     * 领导姓名
     */
    private String leaderName;

    /**
     * 秘书ID
     */
    private String secretaryId;

    /**
     * 秘书姓名
     */
    private String secretaryName;

    /**
     * 启用状态，0-禁用，1-启用
     */
    private String enableFlag;

    /**
     * 删除标识，0-否，1-是
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 更新人
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("leaderId", getLeaderId())
                .append("secretaryId", getSecretaryId())
                .append("enableFlag", getEnableFlag())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
