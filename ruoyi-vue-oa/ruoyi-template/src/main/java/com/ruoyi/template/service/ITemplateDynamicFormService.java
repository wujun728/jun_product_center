package com.ruoyi.template.service;

import java.util.List;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.module.FormOption;

/**
 * 动态单Service接口
 * 
 * @author wucorr.com
 */
public interface ITemplateDynamicFormService {
    /**
     * 查询动态单
     * 
     * @param id 动态单主键
     * @return 动态单
     */
    public TemplateDynamicForm getTemplateDynamicFormById(String id);

    /**
     * 查询动态单列表
     * 
     * @param templateDynamicForm 动态单
     * @return 动态单集合
     */
    public List<TemplateDynamicForm> listTemplateDynamicForm(TemplateDynamicForm templateDynamicForm);

    /**
     * 新增动态单
     * 
     * @param templateDynamicForm 动态单
     * @return 结果
     */
    public int saveTemplateDynamicForm(TemplateDynamicForm templateDynamicForm);

    /**
     * 修改动态单
     * 
     * @param templateDynamicForm 动态单
     * @return 结果
     */
    public int updateTemplateDynamicForm(TemplateDynamicForm templateDynamicForm);

    /**
     * 批量删除动态单
     * 
     * @param ids 需要删除的动态单主键集合
     * @return 结果
     */
    public int deleteTemplateDynamicFormByIds(String[] ids);

    /**
     * 获取可关联动态表单定义列表
     *
     * @return List<FormOption>
     */
    public List<FormOption> getOptionSelect();
}
