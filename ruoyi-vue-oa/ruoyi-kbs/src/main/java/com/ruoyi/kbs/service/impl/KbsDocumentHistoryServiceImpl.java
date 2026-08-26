package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsDocumentHistory;
import com.ruoyi.kbs.mapper.KbsDocumentHistoryMapper;
import com.ruoyi.kbs.service.IKbsDocumentHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 知识库文档历史记录Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentHistoryServiceImpl implements IKbsDocumentHistoryService {
    @Autowired
    private KbsDocumentHistoryMapper kbsDocumentHistoryMapper;

    /**
     * 查询知识库文档历史记录
     * 
     * @param id 知识库文档历史记录主键
     * @return 知识库文档历史记录
     */
    @Override
    public KbsDocumentHistory getKbsDocumentHistoryById(String id) {
        return kbsDocumentHistoryMapper.selectKbsDocumentHistoryById(id);
    }

    /**
     * 查询知识库文档历史记录列表
     * 
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 知识库文档历史记录
     */
    @Override
    public List<KbsDocumentHistory> listKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory) {
        return kbsDocumentHistoryMapper.selectKbsDocumentHistoryList(kbsDocumentHistory);
    }

    /**
     * 新增知识库文档历史记录（后期可以通过异步方式新增历史记录，提高性能）
     * 
     * @param kbsDocumentHistory 知识库文档历史记录
     * @return 结果
     */
    @Override
    public int saveKbsDocumentHistory(KbsDocumentHistory kbsDocumentHistory) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 获取最大版本号
        Long maxVersion = kbsDocumentHistoryMapper.statDocumentHistoryMaxVersion(kbsDocumentHistory.getDocId());
        kbsDocumentHistory.setId(IdUtils.fastSimpleUUID());
        // 默认自动发布
        kbsDocumentHistory.setPublishFlag(WhetherStatus.YES.getCode());
        // 版本号
        kbsDocumentHistory.setVersion(maxVersion == null ? 1 : ++maxVersion);
        kbsDocumentHistory.setCreateId(loginUser.getUserId());
        kbsDocumentHistory.setCreateBy(loginUser.getUser().getNickName());
        kbsDocumentHistory.setCreateTime(DateUtils.getNowDate());
        return kbsDocumentHistoryMapper.insertKbsDocumentHistory(kbsDocumentHistory);
    }

    /**
     * 批量删除知识库文档历史记录
     * 
     * @param ids 需要删除的知识库文档历史记录主键
     * @return 结果
     */
    @Override
    public int deleteKbsDocumentHistoryByIds(String[] ids) {
        return kbsDocumentHistoryMapper.deleteKbsDocumentHistoryByIds(ids);
    }

    /**
     * 恢复文档历史记录
     *
     * @param id 文档历史记录ID
     * @return 结果
     */
    @Override
    public int recoverHistory(String id) {
        KbsDocumentHistory kbsDocumentHistory = kbsDocumentHistoryMapper.selectKbsDocumentHistoryById(id);
        if (kbsDocumentHistory == null || StringUtils.isBlank(kbsDocumentHistory.getContent())) {
            throw new BaseException("文档历史记录为空！");
        }
        return kbsDocumentHistoryMapper.recoverDocumentHistory(kbsDocumentHistory);
    }
}
