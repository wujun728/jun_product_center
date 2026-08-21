package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentInfo;
import com.ruoyi.kbs.domain.vo.KbsDocumentStatVo;

/**
 * 知识库文档详情Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsDocumentInfoService {
    /**
     * 查询知识库文档详情
     * 
     * @param id 知识库文档详情主键
     * @return 知识库文档详情
     */
    public KbsDocumentInfo getKbsDocumentInfoById(String id);

    /**
     * 新增知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    public int saveKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo);

    /**
     * 修改知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    public int updateKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo);

    /**
     * 根据文档ID批量删除知识库文档详情信息
     *
     * @param docIds 文档ID集合
     * @return 结果
     */
    int deleteKbsDocumentInfoByDocIds(String[] docIds);

    /**
     * 获取文档统计数量
     *
     * @param id 文档ID
     * @return 结果
     */
    KbsDocumentStatVo getDocumentStatNum(String id);
}
