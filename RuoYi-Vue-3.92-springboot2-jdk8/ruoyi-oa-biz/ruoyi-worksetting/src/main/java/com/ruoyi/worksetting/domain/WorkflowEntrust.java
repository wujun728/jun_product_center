package com.ruoyi.worksetting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程办理委托对象 t_workflow_entrust
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowEntrust extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 委托人ID
     */
    private String entrustId;

    /**
     * 委托人名称
     */
    private String entrustName;

    /**
     * 被委托人ID
     */
    private String beEntrustId;

    /**
     * 被委托人名称
     */
    private String beEntrustName;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 委托方式，0-全部， 1-部分
     */
    private String type;

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
                .append("entrustId", getEntrustId())
                .append("beEntrustId", getBeEntrustId())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("type", getType())
                .append("enableFlag", getEnableFlag())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
