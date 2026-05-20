package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsTopicCategory;
import com.ruoyi.kbs.domain.KbsTopicCategoryOption;
import com.ruoyi.kbs.domain.KbsTopicInfo;
import com.ruoyi.kbs.domain.qo.KbsTopicCategoryUpdateQo;
import com.ruoyi.kbs.mapper.KbsTopicCategoryMapper;
import com.ruoyi.kbs.service.IKbsTopicCategoryService;
import com.ruoyi.kbs.service.IKbsTopicInfoService;
import com.ruoyi.kbs.utils.TransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 知识库主题类别Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsTopicCategoryServiceImpl implements IKbsTopicCategoryService {
    @Autowired
    private KbsTopicCategoryMapper kbsTopicCategoryMapper;
    @Autowired
    private IKbsTopicInfoService kbsTopicInfoService;

    /**
     * 查询知识库主题类别
     *
     * @param id 知识库主题类别主键
     * @return 知识库主题类别
     */
    @Override
    public KbsTopicCategory getKbsTopicCategoryById(String id) {
        return kbsTopicCategoryMapper.selectKbsTopicCategoryById(id);
    }

    /**
     * 查询知识库主题类别列表
     *
     * @param kbsTopicCategory 知识库主题类别
     * @return 知识库主题类别
     */
    @Override
    public List<KbsTopicCategory> listKbsTopicCategory(KbsTopicCategory kbsTopicCategory) {
        return kbsTopicCategoryMapper.selectKbsTopicCategoryList(kbsTopicCategory);
    }

    /**
     * 新增知识库主题类别
     *
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    @Override
    public int saveKbsTopicCategory(KbsTopicCategory kbsTopicCategory) {
        kbsTopicCategory.setId(IdUtils.fastSimpleUUID());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsTopicCategory.setCreateId(loginUser.getUserId());
        kbsTopicCategory.setCreateBy(loginUser.getUser().getNickName());
        kbsTopicCategory.setCreateTime(DateUtils.getNowDate());
        return kbsTopicCategoryMapper.insertKbsTopicCategory(kbsTopicCategory);
    }

    /**
     * 修改知识库主题类别
     *
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    @Override
    public int updateKbsTopicCategory(KbsTopicCategory kbsTopicCategory) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsTopicCategory.setUpdateId(loginUser.getUserId());
        kbsTopicCategory.setUpdateBy(loginUser.getUser().getNickName());
        kbsTopicCategory.setUpdateTime(DateUtils.getNowDate());
        return kbsTopicCategoryMapper.updateKbsTopicCategory(kbsTopicCategory);
    }

    /**
     * 批量删除知识库主题类别
     *
     * @param ids 需要删除的知识库主题类别主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteKbsTopicCategoryByIds(String[] ids) {
        try {
            Map<String, List<KbsTopicInfo>> topicInfoListMap = getTopicInfoListMap(ids);

            // 使用可变列表
            List<String> categoryIdList = new ArrayList<>(Arrays.asList(ids));
            List<String> updateCategoryIds = getUpdateCategoryIds(categoryIdList, topicInfoListMap);

            // 如果没有存在主題的类别ID，则直接删除
            if (CollectionUtils.isEmpty(updateCategoryIds)) {
                return kbsTopicCategoryMapper.deleteKbsTopicCategoryByIds(TransferUtil.listToArray(categoryIdList));
            }
            // 部分没有存在主題的类别ID，直接删除
            if (CollectionUtils.isNotEmpty(categoryIdList)) {
                kbsTopicCategoryMapper.deleteKbsTopicCategoryByIds(TransferUtil.listToArray(categoryIdList));
            }
            // 批量更新存在主题的类别为删除状态
            List<KbsTopicCategory> kbsTopicCategories = kbsTopicCategoryMapper.selectKbsTopicCategoryByIds(updateCategoryIds);
            return updateBatch(kbsTopicCategories);
        } catch (Exception e) {
            log.error("批量删除知识库主题类别失败，ids: {}", Arrays.toString(ids), e);
            throw e;
        }
    }

    /**
     * 获取主题类别下拉列表
     *
     * @return 结果
     */
    @Override
    public List<KbsTopicCategoryOption> getCategorySelectList() {
        return kbsTopicCategoryMapper.selectCategorySelectList(SecurityUtils.getUserId());
    }

    /**
     * 根据ID列表查询知识库主题类别
     *
     * @param ids 知识库主题类别ID列表
     * @return
     */
    @Override
    public List<KbsTopicCategory> listKbsTopicCategoryByIds(List<String> ids) {
        return kbsTopicCategoryMapper.selectKbsTopicCategoryByIds(ids);
    }

    /**
     * 批量更新
     *
     * @param kbsTopicCategories
     * @return 结果
     */
    @Override
    public int updateBatch(List<KbsTopicCategory> kbsTopicCategories) {
        if (CollectionUtils.isEmpty(kbsTopicCategories)) {
            return 0;
        }
        KbsTopicCategoryUpdateQo updateQo = new KbsTopicCategoryUpdateQo();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        updateQo.setDelFlag(WhetherStatus.YES.getCode());
        updateQo.setUpdateId(loginUser.getUserId());
        updateQo.setUpdateBy(loginUser.getUser().getNickName());
        updateQo.setUpdateTime(DateUtils.getNowDate());
        updateQo.setIds(kbsTopicCategories.stream().map(KbsTopicCategory::getId).collect(Collectors.toList()));
        return kbsTopicCategoryMapper.batchUpdate(updateQo);
    }

    /**
     * 获取主题类别下的主题信息列表
     *
     * @param ids 知识库主题类别ID列表
     * @return
     */
    private Map<String, List<KbsTopicInfo>> getTopicInfoListMap(String[] ids) {
        List<KbsTopicInfo> kbsTopicInfos = kbsTopicInfoService.listByCategoryIds(TransferUtil.arrayToList(ids));
        if (CollectionUtils.isEmpty(kbsTopicInfos)) {
            kbsTopicCategoryMapper.deleteKbsTopicCategoryByIds(ids);
        }
        return kbsTopicInfos.stream()
                .filter(info -> info.getCategoryId() != null)
                .collect(Collectors.groupingBy(KbsTopicInfo::getCategoryId));
    }

    /**
     * 获取需要更新的主题类别ID列表
     *
     * @param categoryIdList
     * @param topicInfoListMap
     * @return
     */
    private List<String> getUpdateCategoryIds(List<String> categoryIdList, Map<String, List<KbsTopicInfo>> topicInfoListMap) {
        if (CollectionUtils.isEmpty(categoryIdList)) {
            return Collections.emptyList();
        }

        List<String> updateCategoryIds = new ArrayList<>(categoryIdList.size());
        Iterator<String> iterator = categoryIdList.iterator();

        while (iterator.hasNext()) {
            String categoryId = iterator.next();
            List<KbsTopicInfo> topicInfos = topicInfoListMap.get(categoryId);
            if (CollectionUtils.isNotEmpty(topicInfos)) {
                updateCategoryIds.add(categoryId);
                iterator.remove();
            }
        }
        return updateCategoryIds;
    }
}
