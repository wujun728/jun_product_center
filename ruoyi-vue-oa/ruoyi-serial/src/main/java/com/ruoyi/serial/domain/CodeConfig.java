package com.ruoyi.serial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编号配置对象 t_code_config
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 当前业务类型的最新流水号 */
    @Excel(name = "当前业务类型的最新流水号")
    private Integer currentSeq;

    /** 启用状态，0-否，1-是 */
    @Excel(name = "启用状态，0-否，1-是")
    private String enableFlag;

    /** 删除状态，0-否，1-是 */
    private String delFlag;

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
            .append("title", getTitle())
            .append("currentSeq", getCurrentSeq())
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
