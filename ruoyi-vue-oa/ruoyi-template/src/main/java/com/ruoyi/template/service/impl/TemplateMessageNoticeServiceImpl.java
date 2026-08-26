package com.ruoyi.template.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.template.domain.TemplateMessageNotice;
import com.ruoyi.template.mapper.TemplateMessageNoticeMapper;
import com.ruoyi.template.service.ITemplateMessageNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息通知模板Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class TemplateMessageNoticeServiceImpl implements ITemplateMessageNoticeService {
    @Autowired
    private TemplateMessageNoticeMapper templateMessageNoticeMapper;

    /**
     * 新增消息通知模板
     * 
     * @param templateMessageNotice 消息通知模板
     * @return 结果
     */
    @Override
    public int saveTemplateMessageNotice(TemplateMessageNotice templateMessageNotice) {
        templateMessageNotice.setCreateTime(DateUtils.getNowDate());
        return templateMessageNoticeMapper.insertTemplateMessageNotice(templateMessageNotice);
    }

    /**
     * 根据模板id查询消息通知模板
     *
     * @param templateId
     * @return 结果
     */
    @Override
    public TemplateMessageNotice getByTemplateId(String templateId) {
        return templateMessageNoticeMapper.selectByTemplateId(templateId);
    }
}
