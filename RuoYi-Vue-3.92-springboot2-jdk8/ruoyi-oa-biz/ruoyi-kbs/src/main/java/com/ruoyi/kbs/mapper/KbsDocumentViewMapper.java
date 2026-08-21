package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentView;

/**
 * 知识库文档浏览Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsDocumentViewMapper {
    /**
     * 查询知识库文档浏览
     * 
     * @param id 知识库文档浏览主键
     * @return 知识库文档浏览
     */
    public KbsDocumentView selectKbsDocumentViewById(String id);

    /**
     * 查询知识库文档浏览列表
     * 
     * @param kbsDocumentView 知识库文档浏览
     * @return 知识库文档浏览集合
     */
    public List<KbsDocumentView> selectKbsDocumentViewList(KbsDocumentView kbsDocumentView);

    /**
     * 新增知识库文档浏览
     * 
     * @param kbsDocumentView 知识库文档浏览
     * @return 结果
     */
    public int insertKbsDocumentView(KbsDocumentView kbsDocumentView);

    /**
     * 修改知识库文档浏览
     * 
     * @param kbsDocumentView 知识库文档浏览
     * @return 结果
     */
    public int updateKbsDocumentView(KbsDocumentView kbsDocumentView);

    /**
     * 删除知识库文档浏览
     * 
     * @param id 知识库文档浏览主键
     * @return 结果
     */
    public int deleteKbsDocumentViewById(String id);

    /**
     * 批量删除知识库文档浏览
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentViewByIds(String[] ids);

    /**
     * 统计文档浏览数量
     *
     * @param docId 文档ID
     * @return 浏览数量
     */
    Long statDocumentViewNum(String docId);
}
