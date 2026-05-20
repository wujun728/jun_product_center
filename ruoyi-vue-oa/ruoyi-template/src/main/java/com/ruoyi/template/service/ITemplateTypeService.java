package com.ruoyi.template.service;

import java.util.List;
import com.ruoyi.template.domain.TemplateType;

/**
 * 模板分类Service接口
 * 
 * @author wocurr.com
 * @date 2025-08-07
 */
public interface ITemplateTypeService {
    /**
     * 查询模板分类
     * 
     * @param id 模板分类主键
     * @return 模板分类
     */
    public TemplateType getTemplateTypeById(String id);

    /**
     * 查询模板分类列表
     * 
     * @param templateType 模板分类
     * @return 模板分类集合
     */
    public List<TemplateType> listTemplateType(TemplateType templateType);

    /**
     * 根据id列表查询
     * @return
     */
    public List<TemplateType> listTemplateType(List<String> ids);

    /**
     * 查询所有启用的模板
     * @return
     */
    public List<TemplateType> listAllEnabledTemplateType();

    /**
     * 新增模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    public int saveTemplateType(TemplateType templateType);

    /**
     * 修改模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    public int updateTemplateType(TemplateType templateType);

    /**
     * 批量删除模板分类
     * 
     * @param ids 需要删除的模板分类主键集合
     * @return 结果
     */
    public int deleteTemplateTypeByIds(String[] ids);

    /**
     * 删除模板分类信息
     * 
     * @param id 模板分类主键
     * @return 结果
     */
    public int deleteTemplateTypeById(String id);

    /**
     * 修改启用状态
     * @param templateType
     * @return
     */
    public int changeEnableFlag(TemplateType templateType);
}
