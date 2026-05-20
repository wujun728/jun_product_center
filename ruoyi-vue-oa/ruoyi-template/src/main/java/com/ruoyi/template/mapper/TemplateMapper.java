package com.ruoyi.template.mapper;

import java.util.List;
import com.ruoyi.template.domain.Template;

/**
 * 模板配置Mapper接口
 * 
 * @author wocurr.com
 */
public interface TemplateMapper {
    /**
     * 查询模板配置
     * 
     * @param id 模板配置主键
     * @return 模板配置
     */
    public Template selectTemplateById(String id);

    /**
     * 查询模板配置列表
     * 
     * @param template 模板配置
     * @return 模板配置集合
     */
    public List<Template> selectTemplateList(Template template);

    /**
     * 新增模板配置
     * 
     * @param template 模板配置
     * @return 结果
     */
    public int insertTemplate(Template template);

    /**
     * 修改模板配置
     * 
     * @param template 模板配置
     * @return 结果
     */
    public int updateTemplate(Template template);

    /**
     * 删除模板配置
     * 
     * @param id 模板配置主键
     * @return 结果
     */
    public int deleteTemplateById(String id);

    /**
     * 批量删除模板配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateByIds(String[] ids);

    /**
     * 更新模板配置状态
     *
     * @param template 模板配置
     * @return 结果
     */
    int changeEnableFlag(Template template);


    /**
     * 查询新启流程模板列表
     *
     * @param template 模板配置
     * @return 模板列表
     */
    List<Template> selectNewStartTemplateList(Template template);
}
