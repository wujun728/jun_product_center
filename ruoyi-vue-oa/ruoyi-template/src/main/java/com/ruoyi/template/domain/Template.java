package com.ruoyi.template.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板配置对象 t_template
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Template extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 模板名称
     */
    @Excel(name = "模板名称")
    private String name;

    /**
     * 模板类型
     */
    @Excel(name = "模板类型")
    private String type;

    /**
     * 模板类型名称
     */
    private String typeName;

    /**
     * 流程定义key
     */
    @Excel(name = "流程定义key")
    private String defKey;

    /**
     * 表单配置ID
     */
    @Excel(name = "表单配置ID")
    private String formId;

    /**
     * 业务表单key
     */
    private String formKey;

    /**
     * 表单类型，1-动态表单，2-业务表单
     */
    @Excel(name = "模板类型，1-动态表单，2-业务表单")
    private String formType;

    /**
     * 表单编码，动态表单默认是dynamic，业务表单用户自定义
     */
    private String formCode;

    /**
     * 是否需要正文，0-否，1-时
     */
    private String mainTextFlag;

    /**
     * 是否有附件，0-否，1-是
     */
    private String attachFlag;

    /**
     * 是否启用，0-否，1-是
     */
    @Excel(name = "是否启用，0-否，1-是")
    private String enableFlag;

    /**
     * 是否需要消息通知，0-否，1-时
     */
    private String messageNoticeFlag;

    /**
     * 发布状态，0-未发布，1-已发布，2-已下架
     */
    @Excel(name = "发布状态，0-未发布，1-已发布，2-已下架")
    private String delFlag;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("defKey", getDefKey())
                .append("formId", getFormId())
                .append("formType", getFormType())
                .append("formCode", getFormCode())
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
