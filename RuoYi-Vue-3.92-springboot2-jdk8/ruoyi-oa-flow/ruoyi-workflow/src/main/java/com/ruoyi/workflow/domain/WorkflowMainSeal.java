package com.ruoyi.workflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 正文印章对象 t_workflow_main_seal
 * 
 * @author wocurr.com
 * @date 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowMainSeal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 印章文件ID */
    private String fileId;

    /** 印章名 */
    @Excel(name = "印章名")
    private String sealName;

    /** 印章类型，0-公章，1-合同章，2-发票章 */
    @Excel(name = "印章类型，0-公章，1-合同章，2-发票章")
    private String type;

    /** 样式 */
    @Excel(name = "样式")
    private String style;

    /** 环绕文字 */
    @Excel(name = "环绕文字")
    private String surroundWord;

    /** 中心文字 */
    private String centreWord;

    /** 下弦文字 */
    private String underWord;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 尺寸（宽） */
    private Integer width;

    /** 尺寸（高） */
    private Integer height;

    /** 状态，0-停用，1-启用 */
    @Excel(name = "状态，0-停用，1-启用")
    private String enableFlag;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

    /** 创建人 */
    private String createId;

    /** 修改人 */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileId", getFileId())
            .append("sealName", getSealName())
            .append("type", getType())
            .append("style", getStyle())
            .append("surroundWord", getSurroundWord())
            .append("centreWord", getCentreWord())
            .append("underWord", getUnderWord())
            .append("color", getColor())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("status", getEnableFlag())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .toString();
    }
}
