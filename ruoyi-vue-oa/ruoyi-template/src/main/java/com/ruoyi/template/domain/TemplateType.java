package com.ruoyi.template.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板分类对象 t_template_type
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String name;

    /** 排序号 */
    @Excel(name = "排序号")
    private Integer sort;

    /** 是否启用，0-否，1-是 */
    @Excel(name = "是否启用，0-否，1-是")
    private String enableFlag;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

    /** 创建人 */
    private String createId;

    /** 更新人 */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sort", getSort())
            .append("enableFlag", getEnableFlag())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
