package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentHistory;

/**
 * 知识库文档历史记录Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsDocumentHistoryMapper {
    /**
     * 查询知识库文档历史记录
     * 
     * @param id 知识库文档历史记录主键
     * @return 知识库文档历史记录
     */
    public KbsDocumentHistory selectKbsDocumentHistoryById(String id);

    /**
     * 查询知识库文档历史记录列表
     * 
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 知识库文档历史记录集合
     */
    public List<KbsDocumentHistory> selectKbsDocumentHistoryList(KbsDocumentHistory kbsDocumentHistory);

    /**
     * 新增知识库文档历史记录
     * 
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 结果
     */
    public int insertKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory);

    /**
     * 修改知识库文档历史记录
     * 
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 结果
     */
    public int updateKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory);

    /**
     * 删除知识库文档历史记录
     * 
     * @param id 知识库文档历史记录主键
     * @return 结果
     */
    public int deleteKbsDocumentHistoryById(String id);

    /**
     * 批量删除知识库文档历史记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentHistoryByIds(String[] ids);

    /**
     * 统计文档历史版本最大版本号
     *
     * @param docId 文档ID
     * @return 最大版本号
     */
    Long statDocumentHistoryMaxVersion(String docId);

    /**
     * 恢复文档历史记录
     *
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return
     */
    int recoverDocumentHistory(KbsDocumentHistory kbsDocumentHistory);
}
