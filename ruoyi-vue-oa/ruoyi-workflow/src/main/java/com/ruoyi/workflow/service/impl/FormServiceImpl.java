package com.ruoyi.workflow.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.service.ITemplateDynamicFormService;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.workflow.domain.Form;
import com.ruoyi.workflow.mapper.FormMapper;
import com.ruoyi.workflow.service.IFormService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 流程表单Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class FormServiceImpl implements IFormService {
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private ITemplateDynamicFormService dynamicFormService;

    /**
     * 查询流程表单
     *
     * @param id 流程表单主键
     * @return 流程表单
     */
    @Override
    public Form getFormById(String id) {
        return formMapper.selectFormById(id);
    }


    /**
     * 新增流程表单
     *
     * @param form 流程表单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveForm(Form form) {
        String id = IdUtils.fastSimpleUUID();
        form.setId(id);
        form.setCreateId(SecurityUtils.getUserId());
        form.setCreateTime(DateUtils.getNowDate());
        formMapper.insertForm(form);
        return id;
    }

    /**
     * 修改流程表单
     *
     * @param form 流程表单
     * @return 结果
     */
    @Override
    public int updateForm(Form form) {
        form.setUpdateId(SecurityUtils.getLoginUser().getUserId());
        form.setUpdateTime(DateUtils.getNowDate());
        return formMapper.updateForm(form);
    }

    /**
     * 流程初始化表单
     *
     * @param templateId 模板ID
     * @return JSONObject 表单JSON对象
     */
    @Override
    public String dynamicFormData(String templateId) {
        // 第一次申请获取初始化表单
        if (StringUtils.isNotBlank(templateId)) {
            Template template = templateService.getTemplateById(templateId);
            if (Objects.isNull(template)) {
                throw new RuntimeException("模板为空!");
            }
            TemplateDynamicForm dynamicForm = dynamicFormService.getTemplateDynamicFormById(template.getFormId());
            return dynamicForm.getContent();
        } else {
            throw new RuntimeException("参数错误!");
        }
    }
}
