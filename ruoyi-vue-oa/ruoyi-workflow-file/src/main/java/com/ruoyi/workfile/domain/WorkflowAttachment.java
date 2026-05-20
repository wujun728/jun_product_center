package com.ruoyi.workfile.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程业务附件对象 t_workflow_attachment
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowAttachment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 文件ID
     */
    private String fileId;

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
                .append("businessId", getBusinessId())
                .append("fileId", getFileId())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
