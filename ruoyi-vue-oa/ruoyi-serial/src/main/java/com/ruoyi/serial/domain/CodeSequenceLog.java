package com.ruoyi.serial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编号生成日志对象 t_code_sequence_log
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeSequenceLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /**
     * 编号名称
     */
    private String title;

    /** 生成的编号 */
    @Excel(name = "生成的编号")
    private String code;

    /** 编号对应的序号 */
    @Excel(name = "编号对应的序号")
    private Integer codeSeq;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("code", getCode())
                .append("codeSeq", getCodeSeq())
                .append("createTime", getCreateTime())
                .toString();
    }
}
