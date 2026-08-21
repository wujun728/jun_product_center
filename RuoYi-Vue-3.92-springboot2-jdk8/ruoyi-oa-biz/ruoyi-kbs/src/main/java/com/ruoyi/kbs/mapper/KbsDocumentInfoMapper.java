package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentInfo;

/**
 * 知识库文档详情Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsDocumentInfoMapper {
    /**
     * 查询知识库文档详情
     * 
     * @param id 知识库文档详情主键
     * @return 知识库文档详情
     */
    public KbsDocumentInfo selectKbsDocumentInfoById(String id);

    /**
     * 查询知识库文档详情列表
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 知识库文档详情集合
     */
    public List<KbsDocumentInfo> selectKbsDocumentInfoList(KbsDocumentInfo kbsDocumentInfo);

    /**
     * 新增知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    public int insertKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo);

    /**
     * 修改知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    public int updateKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo);

    /**
     * 删除知识库文档详情
     * 
     * @param id 知识库文档详情主键
     * @return 结果
     */
    public int deleteKbsDocumentInfoById(String id);

    /**
     * 批量删除知识库文档详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentInfoByIds(String[] ids);

    /**
     * 根据文档ID批量删除知识库文档详情信息
     *
     * @param docIds 文档ID集合
     * @return 结果
     */
    int deleteKbsDocumentInfoByDocIds(String[] docIds);
}
