package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库收藏对象 t_kbs_favorite
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsFavorite extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 被收藏对象ID
     */
    private String objectId;

    /**
     * 收藏对象类型，1-主题，2-文档
     */
    @Excel(name = "收藏对象类型，1-主题，2-文档")
    private String objectType;

    /**
     * 被收藏对象名称
     */
    @Excel(name = "被收藏对象名称")
    private String objectName;

    /**
     * 根对象ID
     */
    private String rootObjectId;

    /**
     * 父对象ID
     */
    private String parentObjectId;

    /**
     * 收藏组ID
     */
    @Excel(name = "收藏组ID")
    private String groupId;

    /**
     * 收藏用户ID
     */
    @Excel(name = "收藏用户ID")
    private String userId;

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
                .append("objectType", getObjectType())
                .append("objectName", getObjectName())
                .append("parentObjectId", getParentObjectId())
                .append("groupId", getGroupId())
                .append("userId", getUserId())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
