package com.ruoyi.workfile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 正文对象 t_workflow_main_text
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowMainText extends BaseEntity {
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
     * 源文件id
     */
    private String oriFileId;

    /**
     * 盖章状态,0-未盖章，1-已盖章
     */
    private String stampStatus;

    /**
     * 盖章人
     */
    private String stampUserId;

    /**
     * 盖章时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date stampTime;

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
                .append("oriFileId", getOriFileId())
                .append("stampStatus", getStampStatus())
                .append("stampUserId", getStampUserId())
                .append("stampTime", getStampTime())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
