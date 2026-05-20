package com.ruoyi.template.service;

import com.ruoyi.template.domain.TemplateAttachment;

/**
 * 附件配置Service接口
 * 
 * @author wocurr.com
 */
public interface ITemplateAttachmentService {

    /**
     * 查询附件配置
     * @param templateId
     * @return
     */
    public TemplateAttachment getTemplateAttachmentByTemplateId(String templateId);

    /**
     * 新增附件配置
     * 
     * @param templateAttachment 附件配置
     * @return 结果
     */
    public int saveTemplateAttachment(TemplateAttachment templateAttachment);
}
