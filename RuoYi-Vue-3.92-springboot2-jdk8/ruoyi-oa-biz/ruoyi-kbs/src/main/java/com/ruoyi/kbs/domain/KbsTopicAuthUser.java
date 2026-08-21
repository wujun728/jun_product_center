package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库主题权限用户对象 t_kbs_topic_auth_user
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsTopicAuthUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 主题ID
     */
    private String topicId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String name;

    /**
     * 权限类型，1-可管理，2-可编辑，3-可阅读
     */
    @Excel(name = "权限类型，1-可管理，2-可编辑，3-可阅读")
    private String type;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private String deptId;

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
                .append("topicId", getTopicId())
                .append("userId", getUserId())
                .append("name", getName())
                .append("type", getType())
                .append("sort", getSort())
                .append("deptId", getDeptId())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
