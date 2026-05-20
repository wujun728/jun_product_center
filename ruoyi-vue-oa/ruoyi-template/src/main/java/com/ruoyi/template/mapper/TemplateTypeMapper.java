package com.ruoyi.template.mapper;

import java.util.List;
import com.ruoyi.template.domain.TemplateType;

/**
 * 模板分类Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-08-07
 */
public interface TemplateTypeMapper {
    /**
     * 查询模板分类
     * 
     * @param id 模板分类主键
     * @return 模板分类
     */
    public TemplateType selectTemplateTypeById(String id);

    /**
     * 查询模板分类列表
     * 
     * @param templateType 模板分类
     * @return 模板分类集合
     */
    public List<TemplateType> selectTemplateTypeList(TemplateType templateType);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public List<TemplateType> selectTemplateTypeByName(String name);

    /**
     * 批量查询
     * @param list
     * @return
     */
    public List<TemplateType> selectTemplateTypeByIds(List<String> list);

    /**
     * 查询所有可用的模板分类
     * @return
     */
    public List<TemplateType> listAllEnabledTemplateType();

    /**
     * 新增模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    public int insertTemplateType(TemplateType templateType);

    /**
     * 修改模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    public int updateTemplateType(TemplateType templateType);

    /**
     * 删除模板分类
     * 
     * @param id 模板分类主键
     * @return 结果
     */
    public int deleteTemplateTypeById(String id);

    /**
     * 批量删除模板分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateTypeByIds(String[] ids);

    /**
     * 改变启用状态
     * @param templateType
     * @return
     */
    public int changeEnableFlag(TemplateType templateType);
}
