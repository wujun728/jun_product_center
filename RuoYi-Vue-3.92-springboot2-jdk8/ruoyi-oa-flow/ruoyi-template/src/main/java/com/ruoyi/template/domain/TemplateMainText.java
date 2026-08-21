package com.ruoyi.template.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 正文配置对象 t_template_main_text
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateMainText extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** 启用方式，0-上传，1-书签替换 */
    @Excel(name = "启用方式，0-上传，1-书签替换")
    private String type;

    /** type为1时的模板文件id */
    @Excel(name = "type为1时的模板文件id")
    private String fileId;

    /** 限制大小 */
    @Excel(name = "限制大小")
    private Integer limitSize;

    /** 限制类型 */
    @Excel(name = "限制类型")
    private String limitType;

    /** 提示内容 */
    @Excel(name = "提示内容")
    private String tipContent;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("type", getType())
            .append("fileId", getFileId())
            .append("limitSize", getLimitSize())
            .append("limitType", getLimitType())
            .append("tipContent", getTipContent())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
