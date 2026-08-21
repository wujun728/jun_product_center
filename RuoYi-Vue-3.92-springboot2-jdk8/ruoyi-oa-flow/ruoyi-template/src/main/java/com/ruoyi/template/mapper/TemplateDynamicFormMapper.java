package com.ruoyi.template.mapper;

import java.util.List;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.module.FormOption;

/**
 * 动态单Mapper接口
 * 
 * @author wucorr.com
 */
public interface TemplateDynamicFormMapper {
    /**
     * 查询动态单
     * 
     * @param id 动态单主键
     * @return 动态单
     */
    public TemplateDynamicForm selectTemplateDynamicFormById(String id);

    /**
     * 查询动态单列表
     * 
     * @param templateDynamicForm 动态单
     * @return 动态单集合
     */
    public List<TemplateDynamicForm> selectTemplateDynamicFormList(TemplateDynamicForm templateDynamicForm);

    /**
     * 新增动态单
     * 
     * @param templateDynamicForm 动态单
     * @return 结果
     */
    public int insertTemplateDynamicForm(TemplateDynamicForm templateDynamicForm);

    /**
     * 修改动态单
     * 
     * @param templateDynamicForm 动态单
     * @return 结果
     */
    public int updateTemplateDynamicForm(TemplateDynamicForm templateDynamicForm);

    /**
     * 删除动态单
     * 
     * @param id 动态单主键
     * @return 结果
     */
    public int deleteTemplateDynamicFormById(String id);

    /**
     * 批量删除动态单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateDynamicFormByIds(String[] ids);

    /**
     * 查询动态表单列表
     *
     * @return
     */
    List<FormOption> selectFormOptionList();
}
