package com.ruoyi.template.service;

import com.ruoyi.template.domain.TemplateMainText;

/**
 * 正文配置Service接口
 * 
 * @author wocurr.com
 */
public interface ITemplateMainTextService {

    /**
     * 根据模板ID查询正文配置
     * @param templateId
     * @return
     */
    public TemplateMainText getByTemplateId(String templateId);

    /**
     * 新增正文配置
     * 
     * @param templateMainText 正文配置
     * @return 结果
     */
    public int saveTemplateMainText(TemplateMainText templateMainText);

}
