package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库主题对象 t_kbs_topic_info
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsTopicInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 主题名称
     */
    @Excel(name = "主题名称")
    private String name;

    /**
     * 可见范围，1-公司可见 2-部分可见 3-仅自己可见
     */
    @Excel(name = "可见范围，1-公司可见 2-部分可见 3-仅自己可见")
    private String visualScope;

    /**
     * 操作类型，1-可编辑 2-可查看
     */
    @Excel(name = "操作类型，1-可编辑 2-可查看")
    private String operateType;

    /**
     * 主题类别ID
     */
    private String categoryId;

    /**
     * 封面图片ID
     */
    private String coverPicId;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

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
                .append("name", getName())
                .append("visualScope", getVisualScope())
                .append("operateType", getOperateType())
                .append("categoryId", getCategoryId())
                .append("coverPicId", getCoverPicId())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
