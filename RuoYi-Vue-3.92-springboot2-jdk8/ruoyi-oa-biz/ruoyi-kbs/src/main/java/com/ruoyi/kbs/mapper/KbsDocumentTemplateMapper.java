package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentTemplate;
import com.ruoyi.kbs.domain.qo.KbsDocumentTemplateUpdateQo;
import com.ruoyi.kbs.domain.qo.KbsFavoriteGroupUpdateQo;

/**
 * 知识库文档模板Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsDocumentTemplateMapper {
    /**
     * 查询知识库文档模板
     * 
     * @param id 知识库文档模板主键
     * @return 知识库文档模板
     */
    public KbsDocumentTemplate selectKbsDocumentTemplateById(String id);

    /**
     * 查询知识库文档模板列表
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 知识库文档模板集合
     */
    public List<KbsDocumentTemplate> selectKbsDocumentTemplateList(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 新增知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    public int insertKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 修改知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    public int updateKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate);

    /**
     * 批量更新删除标识
     *
     * @param qo 更新对象
     * @return 结果
     */
    int updateDelFlagByIds(KbsDocumentTemplateUpdateQo qo);
}
