package com.ruoyi.template.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 动态单对象 t_template_dynamic_form
 * 
 * @author wucorr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateDynamicForm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 表单主键 */
    private String id;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String name;

    /** 表单内容 */
    @Excel(name = "表单内容")
    private String content;

    /** 启动标识，0-停用，1-启动 */
    private String enableFlag;

    /** 删除标识，0-未删除，1-已删除 */
    private String delFlag;

    /** 创建人员 */
    @Excel(name = "创建人员")
    private String createId;

    /** 更新人员 */
    @Excel(name = "更新人员")
    private String updateId;

    /** 业务表单key */
    private String formKey;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("content", getContent())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("createId", getCreateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
