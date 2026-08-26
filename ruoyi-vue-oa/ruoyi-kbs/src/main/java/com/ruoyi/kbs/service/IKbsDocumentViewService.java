package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentView;

/**
 * 知识库文档浏览Service接口
 *
 * @author wocurr.com
 */
public interface IKbsDocumentViewService {
    /**
     * 新增知识库文档浏览
     *
     * @param kbsDocumentView 知识库文档浏览
     * @return 结果
     */
    public int saveKbsDocumentView(KbsDocumentView kbsDocumentView);
    /**
     * 统计文档浏览数量
     *
     * @param docId 文档ID
     * @return 数量
     */
    Long statDocumentViewNum(String docId);
}
