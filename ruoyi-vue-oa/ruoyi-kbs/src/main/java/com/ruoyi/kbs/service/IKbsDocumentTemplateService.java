package com.ruoyi.kbs.service;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentTemplate;

/**
 * 知识库文档模板Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsDocumentTemplateService {
    /**
     * 查询知识库文档模板
     * 
     * @param id 知识库文档模板主键
     * @return 知识库文档模板
     */
    public KbsDocumentTemplate getKbsDocumentTemplateById(String id);

    /**
     * 查询知识库文档模板列表
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 知识库文档模板集合
     */
    public List<KbsDocumentTemplate> listKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 新增知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    public int saveKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 修改知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    public int updateKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 批量删除知识库文档模板
     * 
     * @param ids 需要删除的知识库文档模板主键集合
     * @return 结果
     */
    public int deleteKbsDocumentTemplateByIds(String[] ids);
}
