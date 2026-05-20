package com.ruoyi.template.mapper;

import java.util.List;
import com.ruoyi.template.domain.TemplateAttachment;

/**
 * 附件配置Mapper接口
 * 
 * @author wocurr.com
 */
public interface TemplateAttachmentMapper {
    /**
     * 查询附件配置
     * 
     * @param id 附件配置主键
     * @return 附件配置
     */
    public TemplateAttachment selectTemplateAttachmentById(String id);

    /**
     * 根据模板id查询
     * @param templateId
     * @return
     */
    public TemplateAttachment selectTemplateAttachmentByTemplateId(String templateId);

    /**
     * 查询附件配置列表
     * 
     * @param templateAttachment 附件配置
     * @return 附件配置集合
     */
    public List<TemplateAttachment> selectTemplateAttachmentList(TemplateAttachment templateAttachment);

    /**
     * 新增附件配置
     * 
     * @param templateAttachment 附件配置
     * @return 结果
     */
    public int insertTemplateAttachment(TemplateAttachment templateAttachment);

    /**
     * 修改附件配置
     * 
     * @param templateAttachment 附件配置
     * @return 结果
     */
    public int updateTemplateAttachment(TemplateAttachment templateAttachment);

    /**
     * 删除附件配置
     * 
     * @param id 附件配置主键
     * @return 结果
     */
    public int deleteTemplateAttachmentById(String id);

    /**
     * 批量删除附件配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateAttachmentByIds(String[] ids);

    /**
     * 根据模板id删除
     *
     * @param templateId 模板ID
     */
    public void deleteAttachmentByTemplateId(String templateId);

    /**
     * 根据模板id批量删除
     *
     * @param templateIds 模板ID集合
     */
    void deleteAttachmentByTemplateIds(String[] templateIds);

}
