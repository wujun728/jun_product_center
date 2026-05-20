package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentBase;
import com.ruoyi.kbs.domain.KbsDocumentModel;
import com.ruoyi.kbs.domain.qo.KbsDocumentBaseQo;
import com.ruoyi.kbs.domain.vo.KbsDocumentInfoVo;

import java.util.List;

/**
 * 知识库文档基本Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsDocumentBaseService {
    /**
     * 查询知识库文档基本
     * 
     * @param id 知识库文档基本主键
     * @return 知识库文档基本
     */
    public KbsDocumentBase getKbsDocumentBaseById(String id);

    /**
     * 查询知识库文档详情
     *
     * @param id 知识库文档基本主键
     * @return 知识库文档基本
     */
    public KbsDocumentInfoVo getKbsDocumentInfoById(String id);

    /**
     * 查询知识库文档基本列表
     * 
     * @param kbsDocumentBase 知识库文档基本
     * @return 知识库文档基本集合
     */
    public List<KbsDocumentBase> listKbsDocumentBase(KbsDocumentBase kbsDocumentBase);

    /**
     * 新增知识库文档基本
     * 
     * @param kbsDocumentInfoVo 知识库文档信息
     * @return 结果
     */
    public String saveKbsDocumentBase(KbsDocumentInfoVo kbsDocumentInfoVo);

    /**
     * 修改知识库文档基本
     * 
     * @param kbsDocumentInfoVo 知识库文档基本
     * @return 结果
     */
    public int updateKbsDocumentBase(KbsDocumentInfoVo kbsDocumentInfoVo);

    /**
     * 更新文档名
     * @param kbsDocumentBase
     * @return
     */
    public int resetName(KbsDocumentBase kbsDocumentBase);

    /**
     * 批量删除知识库文档基本
     * 
     * @param ids 需要删除的知识库文档基本主键集合
     * @return 结果
     */
    public int deleteKbsDocumentBaseByIds(String[] ids);

    /**
     * 批量软删除知识库文档基本
     *
     * @param ids 需要删除的知识库文档基本主键集合
     * @return 结果
     */
    public int softDeleteKbsDocumentBaseByIds(List<String> ids);

    /**
     *  查询知识库文档详情列表
     *
     * @param qo 查询条件
     * @return 结果
     */
    List<KbsDocumentModel> listDocumentByTopic(KbsDocumentBaseQo qo);

    /**
     * 根据ID查询知识库文档基本
     *
     * @param ids ID集合
     * @return 结果
     */
    List<KbsDocumentBase> listKbsDocumentBaseByIds(List<String> ids);

    /**
     * 批量更新
     *
     * @param updateDocumentBases 更新集合
     * @param delFlag 删除标识
     * @return 结果
     */
    int updateBatch(List<KbsDocumentBase> updateDocumentBases, String delFlag);


    /**
     * 重排序
     * @param qo
     * @return
     */
    int reSort(KbsDocumentBaseQo qo);


    /**
     * 根据主题ID统计文档数量
     *
     * @param topicId
     * @return 文档数量
     */
    Long countNumByTopicId(String topicId);
}
