package com.ruoyi.workflow.mapper;

import com.ruoyi.template.domain.Template;
import com.ruoyi.workflow.domain.ReceTemplate;

import java.util.List;

/**
 * 最新使用模板Mapper接口
 * 
 * @author wocurr.com
 */
public interface ReceTemplateMapper {
    /**
     * 查询最新使用模板
     * 
     * @param id 最新使用模板主键
     * @return 最新使用模板
     */
    public ReceTemplate selectReceTemplateById(String id);

    /**
     * 查询最新使用模板列表
     * 
     * @param receTemplate 最新使用模板
     * @return 最新使用模板集合
     */
    public List<ReceTemplate> selectReceTemplateList(ReceTemplate receTemplate);

    /**
     * 新增最新使用模板
     * 
     * @param receTemplate 最新使用模板
     * @return 结果
     */
    public int insertReceTemplate(ReceTemplate receTemplate);

    /**
     * 修改最新使用模板
     * 
     * @param receTemplate 最新使用模板
     * @return 结果
     */
    public int updateReceTemplate(ReceTemplate receTemplate);

    /**
     * 删除最新使用模板
     * 
     * @param id 最新使用模板主键
     * @return 结果
     */
    public int deleteReceTemplateById(String id);

    /**
     * 批量删除最新使用模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceTemplateByIds(String[] ids);

    /**
     * 根据使用人ID查询最近使用模板列表
     *
     * @param userId
     * @return
     */
    List<Template> selectReceTemplateListByUserId(String userId);
}
