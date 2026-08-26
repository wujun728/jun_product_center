package com.ruoyi.workflow.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.template.domain.Template;
import com.ruoyi.workflow.domain.ReceTemplate;
import com.ruoyi.workflow.mapper.ReceTemplateMapper;
import com.ruoyi.workflow.service.IReceTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 最新使用模板Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class ReceTemplateServiceImpl implements IReceTemplateService {
    @Autowired
    private ReceTemplateMapper receTemplateMapper;

    /**
     * 新增最新使用模板
     *
     * @param receTemplate 最新使用模板
     * @return 结果
     */
    @Override
    public int saveReceTemplate(ReceTemplate receTemplate) {
        receTemplate.setId(IdUtils.fastSimpleUUID());
        receTemplate.setUserId(SecurityUtils.getUserId());
        receTemplate.setCreateTime(DateUtils.getNowDate());
        return receTemplateMapper.insertReceTemplate(receTemplate);
    }

    /**
     * 查询最近使用模板列表
     *
     * @return 最近使用模板列表
     */
    @Override
    public List<Template> listReceTemplate() {
        String userId = SecurityUtils.getUserId();
        return receTemplateMapper.selectReceTemplateListByUserId(userId);
    }
}
