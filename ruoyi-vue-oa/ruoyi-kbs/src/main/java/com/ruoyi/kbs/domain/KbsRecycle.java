package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档回收站对象 t_kbs_recycle
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsRecycle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 回收对象ID
     */
    private String objectId;

    /**
     * 回收对象名称
     */
    @Excel(name = "回收对象名称")
    private String objectName;

    /**
     * 回收对象类型，1-主题，2-文档
     */
    @Excel(name = "回收对象类型，1-主题，2-文档")
    private String objectType;

    /**
     * 父回收对象ID
     */
    private String parentObjectId;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 更新人ID
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("objectId", getObjectId())
                .append("objectName", getObjectName())
                .append("objectType", getObjectType())
                .append("parentObjectId", getParentObjectId())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
