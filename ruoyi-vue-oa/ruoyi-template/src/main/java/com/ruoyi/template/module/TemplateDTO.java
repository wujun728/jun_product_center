package com.ruoyi.template.module;

import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateAttachment;
import com.ruoyi.template.domain.TemplateDynamicForm;
import lombok.Data;


/**
 * @Author wocurr.com
 */
@Data
public class TemplateDTO extends Template {

    /**
     * 附件信息
     */
    private TemplateAttachment attachment;

    /**
     * 动态表单信息
     */
    private TemplateDynamicForm dynamicForm;

    /**
     * 正文信息
     */
    private TemplateMainTextDTO mainText;

    /**
     * 消息通知
     */
    private TemplateMessageNoticeDTO messageNotice;
}
