package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.*;
import com.ruoyi.kbs.domain.qo.KbsDocumentBaseQo;
import com.ruoyi.kbs.domain.qo.KbsDocumentBaseUpdateQo;
import com.ruoyi.kbs.domain.vo.KbsDocumentInfoVo;
import com.ruoyi.kbs.enums.ObjectTypeEnum;
import com.ruoyi.kbs.mapper.KbsDocumentBaseMapper;
import com.ruoyi.kbs.mapper.KbsSourceTargetMapper;
import com.ruoyi.kbs.service.IKbsDocumentBaseService;
import com.ruoyi.kbs.service.IKbsDocumentHistoryService;
import com.ruoyi.kbs.service.IKbsDocumentInfoService;
import com.ruoyi.kbs.service.IKbsRecycleService;
import com.ruoyi.kbs.utils.TransferUtil;
import com.ruoyi.tools.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 知识库文档基本Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentBaseServiceImpl implements IKbsDocumentBaseService {
    @Autowired
    private KbsDocumentBaseMapper kbsDocumentBaseMapper;
    @Autowired
    private IKbsDocumentInfoService kbsDocumentInfoService;
    @Autowired
    private IKbsRecycleService kbsRecycleService;
    @Autowired
    private IKbsDocumentHistoryService kbsDocumentHistoryService;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private RedisCache redisCache;

    /**
     * 文档锁
     */
    private static final String DOCUMENT_TOPIC_LOCK_KEY = "kbs:document:base:lock:";
    /**
     * 文档主题排序缓存
     */
    private static final String DOCUMENT_TOPIC_SORT_CACHE_KEY = "kbs:document:base:topic:sort:";

    /**
     * 查询知识库文档基本
     *
     * @param id 知识库文档基本主键
     * @return 知识库文档基本
     */
    @Override
    public KbsDocumentBase getKbsDocumentBaseById(String id) {
        return kbsDocumentBaseMapper.selectKbsDocumentBaseById(id);
    }

    /**
     * 查询知识库文档基本
     *
     * @param id 知识库文档基本主键
     * @return 知识库文档基本
     */
    @Override
    public KbsDocumentInfoVo getKbsDocumentInfoById(String id) {
        return kbsDocumentBaseMapper.selectKbsDocumentInfoById(id);
    }

    /**
     * 查询知识库文档基本列表
     *
     * @param kbsDocumentBase 知识库文档基本
     * @return 知识库文档基本
     */
    @Override
    public List<KbsDocumentBase> listKbsDocumentBase(KbsDocumentBase kbsDocumentBase) {
        return kbsDocumentBaseMapper.selectKbsDocumentBaseList(kbsDocumentBase);
    }

    /**
     * 新增知识库文档基本
     *
     * @param kbsDocumentInfoVo 知识库文档基本
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveKbsDocumentBase(KbsDocumentInfoVo kbsDocumentInfoVo) {
        if (StringUtils.isBlank(kbsDocumentInfoVo.getTopicId())) {
            throw new BaseException("参数错误！");
        }
        String topicId = kbsDocumentInfoVo.getTopicId();
        String docId = IdUtils.fastSimpleUUID();
        redisLock.doLock(DOCUMENT_TOPIC_LOCK_KEY + topicId, () -> {
            // 获取当前主题的最大sort
            int sort = getSort(topicId);
            KbsDocumentBase kbsDocumentBase = KbsSourceTargetMapper.INSTANCE.convertDocumentInfoVo2DocumentBase(kbsDocumentInfoVo);
            LoginUser loginUser = SecurityUtils.getLoginUser();
            kbsDocumentBase.setId(docId);
            kbsDocumentBase.setSort(sort);
            kbsDocumentBase.setCreateId(SecurityUtils.getUserIdStr());
            kbsDocumentBase.setCreateBy(loginUser.getUser().getNickName());
            kbsDocumentBase.setCreateTime(DateUtils.getNowDate());
            kbsDocumentBaseMapper.insertKbsDocumentBase(kbsDocumentBase);

            KbsDocumentInfo kbsDocumentInfo = KbsSourceTargetMapper.INSTANCE.convertDocumentInfoVo2DocumentInfo(kbsDocumentInfoVo);
            String id = IdUtils.fastSimpleUUID();
            kbsDocumentInfo.setId(id);
            kbsDocumentInfo.setDocId(docId);
            kbsDocumentInfoService.saveKbsDocumentInfo(kbsDocumentInfo);

            // 新增文档历史记录
            KbsDocumentHistory kbsDocumentHistory = bulidKbsDocumentHistory(kbsDocumentInfo);
            if (kbsDocumentHistory != null) {
                kbsDocumentHistoryService.saveKbsDocumentHistory(kbsDocumentHistory);
            }
        });
        return docId;
    }

    /**
     * 修改知识库文档基本
     *
     * @param kbsDocumentInfoVo 知识库文档信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateKbsDocumentBase(KbsDocumentInfoVo kbsDocumentInfoVo) {
        KbsDocumentBase kbsDocumentBase = KbsSourceTargetMapper.INSTANCE.convertDocumentInfoVo2DocumentBase(kbsDocumentInfoVo);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsDocumentBase.setUpdateId(SecurityUtils.getUserIdStr());
        kbsDocumentBase.setUpdateBy(loginUser.getUser().getNickName());
        kbsDocumentBase.setUpdateTime(DateUtils.getNowDate());
        kbsDocumentBaseMapper.updateKbsDocumentBase(kbsDocumentBase);

        kbsDocumentInfoService.deleteKbsDocumentInfoByDocIds(TransferUtil.objectToArray(kbsDocumentBase.getId()));
        KbsDocumentInfo kbsDocumentInfo = KbsSourceTargetMapper.INSTANCE.convertDocumentInfoVo2DocumentInfo(kbsDocumentInfoVo);
        kbsDocumentInfo.setId(IdUtils.fastSimpleUUID());
        kbsDocumentInfo.setDocId(kbsDocumentBase.getId());

        // 新增文档历史记录
        KbsDocumentHistory kbsDocumentHistory = bulidKbsDocumentHistory(kbsDocumentInfo);
        if (kbsDocumentHistory != null) {
            kbsDocumentHistoryService.saveKbsDocumentHistory(kbsDocumentHistory);
        }
        return kbsDocumentInfoService.saveKbsDocumentInfo(kbsDocumentInfo);
    }

    @Override
    public int resetName(KbsDocumentBase kbsDocumentBase) {
        if (StringUtils.isEmpty(kbsDocumentBase.getName()) || StringUtils.isEmpty(kbsDocumentBase.getId())) {
            throw new BaseException("参数错误");
        }
        KbsDocumentBase documentBase = kbsDocumentBaseMapper.selectKbsDocumentBaseById(kbsDocumentBase.getId());
        if (documentBase == null) {
            throw new BaseException("文档不存在");
        }
        KbsDocumentBase updateDocumentBase = new KbsDocumentBase();
        updateDocumentBase.setId(documentBase.getId());
        updateDocumentBase.setName(kbsDocumentBase.getName());
        return kbsDocumentBaseMapper.updateKbsDocumentBase(updateDocumentBase);
    }

    /**
     * 批量删除知识库文档基本
     *
     * @param ids 需要删除的知识库文档基本主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteKbsDocumentBaseByIds(String[] ids) {
        kbsDocumentBaseMapper.deleteKbsDocumentBaseByIds(ids);
        return kbsDocumentInfoService.deleteKbsDocumentInfoByDocIds(ids);
    }

    /**
     * 批量软删除知识库文档基本
     *
     * @param ids 需要删除的知识库文档基本主键集合
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int softDeleteKbsDocumentBaseByIds(List<String> ids) {
        List<KbsDocumentBase> documentBases = kbsDocumentBaseMapper.selectKbsDocumentBaseByIds(ids);
        if (CollectionUtils.isEmpty(documentBases)) {
            return 1;
        }
        List<KbsRecycle> kbsRecycles = buildDocumentRecycle(documentBases);
        updateBatch(documentBases, WhetherStatus.YES.getCode());
        return kbsRecycleService.saveBatch(kbsRecycles);
    }

    /**
     * 查询知识库文档详情列表 （树结构）
     *
     * @param qo 查询条件
     * @return 结果
     */
    @Override
    public List<KbsDocumentModel> listDocumentByTopic(KbsDocumentBaseQo qo) {
        List<KbsDocumentBase> documentBases = kbsDocumentBaseMapper.selectDocumentListByTopicId(qo.getTopicId());
        if (CollectionUtils.isEmpty(documentBases)) {
            return Collections.emptyList();
        }
        List<KbsDocumentModel> documentModels = KbsSourceTargetMapper.INSTANCE.convertDocumentBase2DocumentModel(documentBases);
        return buildDocumentTreeSelect(documentModels);
    }

    /**
     * 根据ID集合查询知识库文档基本
     *
     * @param ids ID集合
     * @return 结果
     */
    @Override
    public List<KbsDocumentBase> listKbsDocumentBaseByIds(List<String> ids) {
        return kbsDocumentBaseMapper.selectKbsDocumentBaseByIds(ids);
    }

    /**
     * 批量更新
     *
     * @param updateDocumentBases 更新集合
     * @param delFlag             删除标识
     * @return 结果
     */
    @Override
    public int updateBatch(List<KbsDocumentBase> updateDocumentBases, String delFlag) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        KbsDocumentBaseUpdateQo updateQo = new KbsDocumentBaseUpdateQo();
        updateQo.setDelFlag(delFlag);
        updateQo.setUpdateId(SecurityUtils.getUserIdStr());
        updateQo.setUpdateBy(loginUser.getUser().getNickName());
        updateQo.setUpdateTime(DateUtils.getNowDate());
        updateQo.setIds(updateDocumentBases.stream().map(KbsDocumentBase::getId).collect(Collectors.toList()));
        return kbsDocumentBaseMapper.batchUpdate(updateQo);
    }

    /**
     * 重排序
     *
     * @param qo 查询条件
     * @return 结果
     */
    @Override
    public int reSort(KbsDocumentBaseQo qo) {
        if (CollectionUtils.isEmpty(qo.getDocuments())) {
            return 0;
        }
        qo.getDocuments().stream().forEach(d -> {
            KbsDocumentBase documentBase = new KbsDocumentBase();
            documentBase.setId(d.getId());
            documentBase.setSort(d.getSort());
            kbsDocumentBaseMapper.updateSort(documentBase);
        });
        return 1;
    }

    /**
     * 根据主题ID统计文档数量
     *
     * @param topicId
     * @return 文档数量
     */
    @Override
    public Long countNumByTopicId(String topicId) {
        return kbsDocumentBaseMapper.countNumByTopicId(topicId);
    }

    /**
     * 构建文档树
     *
     * @param documentBases 文档基本信息列表
     * @return
     */
    private List<KbsDocumentModel> buildDocumentTreeSelect(List<KbsDocumentModel> documentBases) {
        return buildDocumentTree(documentBases);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param documentBases 文档基本信息列表
     * @return 树结构列表
     */
    private List<KbsDocumentModel> buildDocumentTree(List<KbsDocumentModel> documentBases) {
        List<KbsDocumentModel> returnList = new ArrayList<>();
        List<String> tempList = documentBases.stream().map(KbsDocumentModel::getId).collect(Collectors.toList());
        for (KbsDocumentModel document : documentBases) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(document.getParentId())) {
                recursionFn(documentBases, document);
                returnList.add(document);
            }
        }
        return CollectionUtils.isEmpty(returnList) ? documentBases : returnList;
    }

    /**
     * 递归列表
     *
     * @param documentBases 文档基本信息列表
     * @param document      文档基本信息
     */
    private void recursionFn(List<KbsDocumentModel> documentBases, KbsDocumentModel document) {
        // 得到子节点列表
        List<KbsDocumentModel> childList = getChildList(documentBases, document);
        document.setChildren(childList);
        for (KbsDocumentModel child : childList) {
            if (hasChild(documentBases, child)) {
                recursionFn(documentBases, child);
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<KbsDocumentModel> documentBases, KbsDocumentModel document) {
        return CollectionUtils.isNotEmpty(getChildList(documentBases, document));
    }

    /**
     * 得到子节点列表
     */
    private List<KbsDocumentModel> getChildList(List<KbsDocumentModel> documentBases, KbsDocumentModel document) {
        List<KbsDocumentModel> tlist = new ArrayList<>();
        for (KbsDocumentModel d : documentBases) {
            if (StringUtils.isNotNull(d.getParentId()) && d.getParentId().equals(document.getId())) {
                tlist.add(d);
            }
        }
        return tlist;
    }

    /**
     * 获取排序
     * @param topicId
     * @return
     */
    private int getSort(String topicId) {
        String key = DOCUMENT_TOPIC_SORT_CACHE_KEY + topicId;
        Integer sort = redisCache.getCacheObject(key);
        if (sort == null) {
            KbsDocumentBase kbsDocumentBase = kbsDocumentBaseMapper.selectMaxSort(topicId);
            sort = kbsDocumentBase == null ? 1 : kbsDocumentBase.getSort();
        }
        sort++;
        redisCache.setCacheObject(key, sort, 8,  TimeUnit.HOURS);
        return sort;
    }

    /**
     * 构建文档历史记录
     *
     * @param kbsDocumentInfo
     * @return
     */
    private KbsDocumentHistory bulidKbsDocumentHistory(KbsDocumentInfo kbsDocumentInfo) {
        if (StringUtils.isBlank(kbsDocumentInfo.getContent())) {
            return null;
        }
        KbsDocumentInfoVo documentInfoVo = getKbsDocumentInfoById(kbsDocumentInfo.getDocId());
        if (isExistSameDocument(documentInfoVo, kbsDocumentInfo)) {
            return null;
        }
        KbsDocumentHistory kbsDocumentHistory = new KbsDocumentHistory();
        kbsDocumentHistory.setDocId(kbsDocumentInfo.getDocId());
        kbsDocumentHistory.setContent(kbsDocumentInfo.getContent());
        return kbsDocumentHistory;
    }

    /**
     * 是否存在相同的文档内容
     *
     * @param documentInfoVo
     * @param kbsDocumentInfo
     * @return
     */
    private boolean isExistSameDocument(KbsDocumentInfoVo documentInfoVo, KbsDocumentInfo kbsDocumentInfo) {
        return documentInfoVo != null &&
                StringUtils.isNotBlank(documentInfoVo.getContent()) &&
                Md5Utils.hash(kbsDocumentInfo.getContent()).equals(Md5Utils.hash(documentInfoVo.getContent()));
    }

    /**
     * 构建文档回收站信息
     *
     * @param documentBases 文档集合
     * @return
     */
    private List<KbsRecycle> buildDocumentRecycle(List<KbsDocumentBase> documentBases) {
        if (CollectionUtils.isEmpty(documentBases)) {
            return Collections.emptyList();
        }
        List<KbsRecycle> recycles = new ArrayList<>();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        documentBases.forEach(documentBase -> {
            KbsRecycle recycle = new KbsRecycle();
            recycle.setId(IdUtils.fastSimpleUUID());
            recycle.setObjectId(documentBase.getId());
            recycle.setObjectName(documentBase.getName());
            recycle.setObjectType(ObjectTypeEnum.DOCUMENT.getCode());
            recycle.setParentObjectId(documentBase.getParentId());
            recycle.setCreateId(SecurityUtils.getUserIdStr());
            recycle.setCreateBy(loginUser.getUser().getNickName());
            recycle.setCreateTime(DateUtils.getNowDate());
            recycles.add(recycle);
        });
        return recycles;
    }
}
