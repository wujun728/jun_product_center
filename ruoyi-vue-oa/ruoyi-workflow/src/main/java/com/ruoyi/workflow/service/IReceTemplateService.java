package com.ruoyi.workflow.service;

import com.ruoyi.template.domain.Template;
import com.ruoyi.workflow.domain.ReceTemplate;

import java.util.List;

/**
 * 最新使用模板Service接口
 * 
 * @author wocurr.com
 */
public interface IReceTemplateService {

    /**
     * 新增最新使用模板
     *
     * @param receTemplate 最新使用模板
     * @return 结果
     */
    public int saveReceTemplate(ReceTemplate receTemplate);

    /**
     * 查询最近使用模板列表
     *
     * @return
     */
    List<Template> listReceTemplate();
}
