package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 消息通知模板对象 t_template_message_notice
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateMessageNotice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 消息类型，1-短信 2-邮件 3-其他
     */
    private String type;

    /**
     * 消息模板
     */
    private String msgTemplate;

    /**
     * 流程模板ID
     */
    private String templateId;

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
                .append("type", getType())
                .append("msgTemplate", getMsgTemplate())
                .append("templateId", getTemplateId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
