package com.ruoyi.template.service;

import com.ruoyi.template.domain.TemplateMessageNotice;

/**
 * 消息通知模板Service接口
 * 
 * @author wocurr.com
 */
public interface ITemplateMessageNoticeService {
    /**
     * 新增消息通知模板
     * 
     * @param templateMessageNotice 消息通知模板
     * @return 结果
     */
    public int saveTemplateMessageNotice(TemplateMessageNotice templateMessageNotice);

    /**
     * 根据模板id查询消息通知模板
     *
     * @param templateId
     * @return 结果
     */
    TemplateMessageNotice getByTemplateId(String templateId);
}
