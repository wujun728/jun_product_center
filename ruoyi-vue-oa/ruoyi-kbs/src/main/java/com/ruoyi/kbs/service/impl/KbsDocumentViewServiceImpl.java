package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsDocumentView;
import com.ruoyi.kbs.mapper.KbsDocumentViewMapper;
import com.ruoyi.kbs.service.IKbsDocumentViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 知识库文档浏览Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentViewServiceImpl implements IKbsDocumentViewService {
    @Autowired
    private KbsDocumentViewMapper kbsDocumentViewMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 浏览文档
     */
    private static final String VIEW_KEY = "kbs:document:view:";

    /**
     * 新增知识库文档浏览
     * 
     * @param kbsDocumentView 知识库文档浏览
     * @return 结果
     */
    @Override
    public int saveKbsDocumentView(KbsDocumentView kbsDocumentView) {
        String cacheKey = VIEW_KEY + kbsDocumentView.getDocId() + Constants.COLON + SecurityUtils.getUserId();
        if (redisCache.hasKey(cacheKey)) {
            return 1;
        }
        kbsDocumentView.setId(IdUtils.fastSimpleUUID());
        kbsDocumentView.setCreateId(SecurityUtils.getUserId());
        kbsDocumentView.setCreateTime(DateUtils.getNowDate());
        kbsDocumentViewMapper.insertKbsDocumentView(kbsDocumentView);
        // 缓存数据有效期1天
        redisCache.setCacheObject(cacheKey, 1000 * 60 * 60 * 24L);
        return 1;
    }

    /**
     * 统计文档浏览数量
     *
     * @param docId 文档ID
     * @return 数量
     */
    @Override
    public Long statDocumentViewNum(String docId) {
        return kbsDocumentViewMapper.statDocumentViewNum(docId);
    }
}
