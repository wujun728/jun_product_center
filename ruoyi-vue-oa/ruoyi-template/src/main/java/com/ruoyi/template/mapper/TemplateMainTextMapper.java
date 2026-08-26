package com.ruoyi.template.mapper;

import java.util.List;
import com.ruoyi.template.domain.TemplateMainText;

/**
 * 正文配置Mapper接口
 * 
 * @author wocurr.com
 */
public interface TemplateMainTextMapper {
    /**
     * 查询正文配置
     * 
     * @param id 正文配置主键
     * @return 正文配置
     */
    public TemplateMainText selectTemplateMainTextById(String id);

    /**
     * 根据模板ID查询正文配置
     * @param templateId
     * @return
     */
    public TemplateMainText selectByTemplateId(String templateId);

    /**
     * 查询正文配置列表
     * 
     * @param templateMainText 正文配置
     * @return 正文配置集合
     */
    public List<TemplateMainText> selectTemplateMainTextList(TemplateMainText templateMainText);

    /**
     * 新增正文配置
     * 
     * @param templateMainText 正文配置
     * @return 结果
     */
    public int insertTemplateMainText(TemplateMainText templateMainText);

    /**
     * 修改正文配置
     * 
     * @param templateMainText 正文配置
     * @return 结果
     */
    public int updateTemplateMainText(TemplateMainText templateMainText);

    /**
     * 删除正文配置
     * 
     * @param id 正文配置主键
     * @return 结果
     */
    public int deleteTemplateMainTextById(String id);

    /**
     * 批量删除正文配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateMainTextByIds(String[] ids);
}
