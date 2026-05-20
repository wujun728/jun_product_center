package com.ruoyi.workflow.service;

import com.ruoyi.workflow.domain.Form;

/**
 * 流程表单Service接口
 * 
 * @author wocurr.com
 */
public interface IFormService {
    /**
     * 查询流程表单
     * 
     * @param id 流程表单主键
     * @return 流程表单
     */
    public Form getFormById(String id);

    /**
     * 新增流程表单
     * 
     * @param form 流程表单
     * @return 结果
     */
    public String saveForm(Form form);

    /**
     * 修改流程表单
     * 
     * @param form 流程表单
     * @return 结果
     */
    public int updateForm(Form form);

    /**
     * 查询动态表单
     *
     * @param templateId 流程模板ID
     * @return String
     */
    String dynamicFormData(String templateId);
}
