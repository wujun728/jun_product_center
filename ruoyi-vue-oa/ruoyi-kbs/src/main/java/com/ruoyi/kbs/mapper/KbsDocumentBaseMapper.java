package com.ruoyi.kbs.mapper;

import com.ruoyi.kbs.domain.KbsDocumentBase;
import com.ruoyi.kbs.domain.qo.KbsDocumentBaseUpdateQo;
import com.ruoyi.kbs.domain.vo.KbsDocumentInfoVo;

import java.util.List;

/**
 * 知识库文档基本Mapper接口
 *
 * @author wocurr.com
 */
public interface KbsDocumentBaseMapper {
    /**
     * 查询知识库文档基本
     *
     * @param id 知识库文档基本主键
     * @return 知识库文档基本
     */
    public KbsDocumentBase selectKbsDocumentBaseById(String id);

    /**
     * 查询最大排序
     * @param topicId
     * @return
     */
    public KbsDocumentBase selectMaxSort(String topicId);

    /**
     * 查询知识库文档基本列表
     *
     * @param kbsDocumentBase 知识库文档基本
     * @return 知识库文档基本集合
     */
    public List<KbsDocumentBase> selectKbsDocumentBaseList(KbsDocumentBase kbsDocumentBase);

    /**
     * 新增知识库文档基本
     *
     * @param kbsDocumentBase 知识库文档基本
     * @return 结果
     */
    public int insertKbsDocumentBase(KbsDocumentBase kbsDocumentBase);

    /**
     * 修改知识库文档基本
     *
     * @param kbsDocumentBase 知识库文档基本
     * @return 结果
     */
    public int updateKbsDocumentBase(KbsDocumentBase kbsDocumentBase);

    /**
     * 删除知识库文档基本
     *
     * @param id 知识库文档基本主键
     * @return 结果
     */
    public int deleteKbsDocumentBaseById(String id);

    /**
     * 批量删除知识库文档基本
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentBaseByIds(String[] ids);

    /**
     * 根据主题ID查询知识库文档基本信息
     *
     * @param topicId 主题ID
     * @return 结果
     */
    List<KbsDocumentBase> selectDocumentListByTopicId(String topicId);

    /**
     * 查询知识库文档详情
     *
     * @param id
     * @return
     */
    KbsDocumentInfoVo selectKbsDocumentInfoById(String id);

    /**
     * 批量查询知识库文档基本信息
     *
     * @param ids 需要查询的数据主键集合
     */
    List<KbsDocumentBase> selectKbsDocumentBaseByIds(List<String> ids);

    /**
     * 批量更新
     *
     * @param updateQo 更新参数
     */
    int batchUpdate(KbsDocumentBaseUpdateQo updateQo);

    /**
     * 根据文档创建者ID查询知识库文档基本
     *
     * @param documentCreateIds 文档创建者ID集合
     * @return 结果
     */
    List<KbsDocumentBase> selectKbsDocumentBaseByCreateIds(List<String> documentCreateIds);

    /**
     * 更新排序
     * @return
     */
    int updateSort(KbsDocumentBase kbsDocumentBase);

    /**
     * 根据主题ID统计文档数量
     *
     * @param topicId
     * @return 文档数量
     */
    Long countNumByTopicId(String topicId);}
