package com.ruoyi.template.service;

import java.util.List;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.module.TemplateDTO;
import com.ruoyi.template.module.TemplateModel;
import com.ruoyi.template.module.TemplateOption;

/**
 * 模板配置Service接口
 * 
 * @author wocurr.com
 */
public interface ITemplateService {
    /**
     * 查询模板配置
     * 
     * @param id 模板配置主键
     * @return 模板配置
     */
    public Template getTemplateById(String id);

    /**
     * 查询模板配置
     * @param id
     * @return
     */
    public TemplateDTO getTemplateDTOById(String id); //

    /**
     * 查询模板配置列表
     * 
     * @param template 模板配置
     * @return 模板配置集合
     */
    public List<Template> listTemplate(Template template);

    /**
     * 新增模板配置
     * 
     * @param template 模板配置
     * @return 结果
     */
    public int saveTemplate(TemplateDTO template);

    /**
     * 修改模板配置
     * 
     * @param template 模板配置
     * @return 结果
     */
    public int updateTemplate(TemplateDTO template);

    /**
     * 批量删除模板配置
     * 
     * @param ids 需要删除的模板配置主键集合
     * @return 结果
     */
    public int deleteTemplateByIds(String[] ids);

    /**
     * 更新模板配置状态
     *
     * @param template
     * @return
     */
    int changeEnableFlag(Template template);

    /**
     * 查询新启流程模板列表
     *
     * @return
     */
    List<TemplateModel> listNewStartTemplate();

    /**
     * 查询下拉模板列表
     *
     * @return
     */
    List<TemplateOption> getSelectTemplateList();
}
