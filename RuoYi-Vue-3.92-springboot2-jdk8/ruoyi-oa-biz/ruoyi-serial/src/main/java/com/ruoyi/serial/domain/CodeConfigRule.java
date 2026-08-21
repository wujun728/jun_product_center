package com.ruoyi.serial.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 编号配置规则对象 t_code_config_rule
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeConfigRule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 关联编号配置表ID
     */
    private String configId;

    /**
     * 类型，0-固定值，1-日期，2-流水号
     */
    private String ruleType;

    /**
     * 值
     */
    private String ruleValue;

    /**
     * 是否补0
     */
    private String padZero;

    /**
     * 流水号重置方式，0-不重置，1-按日，2-按周，3-按月，4-按年
     */
    private String seqResetType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("configId", getConfigId())
                .append("ruleType", getRuleType())
                .append("ruleValue", getRuleValue())
                .append("padZero", getPadZero())
                .append("seqResetType", getSeqResetType())
                .append("sort", getSort())
                .append("delFlag", getDelFlag())
                .append("createTime", getCreateTime())
                .toString();
    }
}
