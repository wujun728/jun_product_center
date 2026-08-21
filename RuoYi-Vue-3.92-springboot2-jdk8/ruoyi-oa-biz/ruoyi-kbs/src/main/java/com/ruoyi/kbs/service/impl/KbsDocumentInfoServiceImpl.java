package com.ruoyi.kbs.service.impl;

import com.ruoyi.kbs.domain.KbsDocumentInfo;
import com.ruoyi.kbs.domain.vo.KbsDocumentStatVo;
import com.ruoyi.kbs.mapper.KbsDocumentInfoMapper;
import com.ruoyi.kbs.service.IKbsDocumentCommentService;
import com.ruoyi.kbs.service.IKbsDocumentInfoService;
import com.ruoyi.kbs.service.IKbsDocumentViewService;
import com.ruoyi.kbs.service.IKbsFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 知识库文档详情Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentInfoServiceImpl implements IKbsDocumentInfoService {
    @Autowired
    private KbsDocumentInfoMapper kbsDocumentInfoMapper;
    @Autowired
    private IKbsDocumentViewService kbsDocumentsViewService;
    @Autowired
    private IKbsDocumentCommentService kbsDocumentCommentsService;
    @Autowired
    private IKbsFavoriteService kbsFavoriteService;

    /**
     * 查询知识库文档详情
     * 
     * @param id 知识库文档详情主键
     * @return 知识库文档详情
     */
    @Override
    public KbsDocumentInfo getKbsDocumentInfoById(String id) {
        return kbsDocumentInfoMapper.selectKbsDocumentInfoById(id);
    }

    /**
     * 新增知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    @Override
    public int saveKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo) {
        return kbsDocumentInfoMapper.insertKbsDocumentInfo(kbsDocumentInfo);
    }

    /**
     * 修改知识库文档详情
     * 
     * @param kbsDocumentInfo 知识库文档详情
     * @return 结果
     */
    @Override
    public int updateKbsDocumentInfo(KbsDocumentInfo kbsDocumentInfo) {
        return kbsDocumentInfoMapper.updateKbsDocumentInfo(kbsDocumentInfo);
    }

    /**
     * 根据文档ID批量删除知识库文档详情信息
     *
     * @param docIds 文档ID集合
     * @return 结果
     */
    @Override
    public int deleteKbsDocumentInfoByDocIds(String[] docIds) {
        return kbsDocumentInfoMapper.deleteKbsDocumentInfoByDocIds(docIds);
    }

    /**
     * 获取文档统计数量
     *
     * @param id 文档ID
     * @return 结果
     */
    @Override
    public KbsDocumentStatVo getDocumentStatNum(String id) {
        return KbsDocumentStatVo.builder()
                .viewNum(kbsDocumentsViewService.statDocumentViewNum(id))
                .favoriteNum(kbsFavoriteService.statDocumentFavoriteNum(id))
                .commentNum(kbsDocumentCommentsService.statDocumentCommentNum(id))
                .isFavorite(Objects.nonNull(kbsFavoriteService.getKbsFavoriteByUserId(id)))
                .build();
    }
}
