package com.ruoyi.schedule.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 日程分类对象 t_schedule_type
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScheduleType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String title;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签类型
     */
    private String tagType;

    /**
     * 标签主题
     */
    private String tagEffect;

    /**
     * 主题颜色
     */
    private String color;

    /**
     * 删除状态，0-否，1-是
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
                .append("title", getTitle())
                .append("tag", getTagName())
                .append("color", getColor())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
