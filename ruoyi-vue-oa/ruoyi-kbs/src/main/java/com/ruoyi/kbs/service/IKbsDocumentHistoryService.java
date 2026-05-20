package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentHistory;

import java.util.List;

/**
 * 知识库文档历史记录Service接口
 *
 * @author wocurr.com
 */
public interface IKbsDocumentHistoryService {
    /**
     * 查询知识库文档历史记录
     *
     * @param id 知识库文档历史记录主键
     * @return 知识库文档历史记录
     */
    public KbsDocumentHistory getKbsDocumentHistoryById(String id);

    /**
     * 查询知识库文档历史记录列表
     *
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 知识库文档历史记录集合
     */
    public List<KbsDocumentHistory> listKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory);

    /**
     * 新增知识库文档历史记录
     *
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 结果
     */
    public int saveKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory);

    /**
     * 批量删除知识库文档历史记录
     *
     * @param ids 需要删除的知识库文档历史记录主键集合
     * @return 结果
     */
    public int deleteKbsDocumentHistoryByIds(String[] ids);

    /**
     * 恢复文档历史记录
     *
     * @param id 文档历史记录ID
     * @return 结果
     */
    int recoverHistory(String id);
}
