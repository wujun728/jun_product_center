package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.*;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoQo;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoUpdateQo;
import com.ruoyi.kbs.domain.vo.KbsTopicAllInfoVo;
import com.ruoyi.kbs.domain.vo.KbsTopicInfoVo;
import com.ruoyi.kbs.domain.vo.SysUserVo;
import com.ruoyi.kbs.enums.AuthUserTypeEnum;
import com.ruoyi.kbs.enums.ObjectTypeEnum;
import com.ruoyi.kbs.enums.OperateTypeEnum;
import com.ruoyi.kbs.enums.VisualScopeEnum;
import com.ruoyi.kbs.mapper.KbsSourceTargetMapper;
import com.ruoyi.kbs.mapper.KbsTopicInfoMapper;
import com.ruoyi.kbs.service.*;
import com.ruoyi.kbs.utils.TransferUtil;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 知识库主题Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsTopicInfoServiceImpl implements IKbsTopicInfoService {
    @Autowired
    private KbsTopicInfoMapper kbsTopicInfoMapper;
    @Autowired
    private IKbsTopicAuthUserService kbsUserAuthUserService;
    @Autowired
    private IKbsTopicCategoryService kbsTopicCategoryService;
    @Autowired
    private IKbsRecycleService kbsRecycleService;
    @Autowired
    private IKbsTopicAuthUserService kbsTopicAuthUserService;
    @Autowired
    private IKbsDocumentBaseService kbsDocumentBaseService;
    @Autowired
    private IKbsFavoriteService kbsFavoriteService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询知识库主题
     *
     * @param id 知识库主题主键
     * @return 知识库主题
     */
    @Override
    public KbsTopicInfoVo getKbsTopicInfoVoById(String id) {
        KbsTopicInfo kbsTopicInfo = kbsTopicInfoMapper.selectKbsTopicInfoById(id);
        Assert.notNull(kbsTopicInfo, "主题不存在");
        KbsTopicInfoVo kbsTopicInfoVo = KbsSourceTargetMapper.INSTANCE.convertTopicInfo2TopicInfoVo(kbsTopicInfo);
        if (VisualScopeEnum.OWNER.getCode().equals(kbsTopicInfo.getVisualScope())) {
            return kbsTopicInfoVo;
        }
        List<KbsTopicAuthUser> kbsTopicAuthUsers = kbsUserAuthUserService.listByTopicId(id);
        if (CollectionUtils.isEmpty(kbsTopicAuthUsers)) {
            return kbsTopicInfoVo;
        }
        // 获取主题对应的用户列表映射
        Map<String, SysUserVo> topicSysUserListMap = getTopicSysUserListMap(kbsTopicAuthUsers);
        // 处理可见范围用户权限
        handleVisualScope(kbsTopicInfoVo, kbsTopicAuthUsers, topicSysUserListMap);
        // 处理操作类型用户权限
        handleOperateType(kbsTopicInfoVo, kbsTopicAuthUsers, topicSysUserListMap);
        return kbsTopicInfoVo;
    }

    /**
     * 查询知识库主题列表
     *
     * @param kbsTopicInfo 知识库主题
     * @return 知识库主题
     */
    @Override
    public List<KbsTopicInfoVo> listKbsTopicInfo(KbsTopicInfo kbsTopicInfo) {
        kbsTopicInfo.setDelFlag(WhetherStatus.NO.getCode());
        List<KbsTopicInfo> kbsTopicInfos = kbsTopicInfoMapper.selectKbsTopicInfoList(kbsTopicInfo);
        if (CollectionUtils.isEmpty(kbsTopicInfos)) {
            return Collections.emptyList();
        }
        List<String> categoryIds = kbsTopicInfos.stream()
                .map(KbsTopicInfo::getCategoryId)
                .collect(Collectors.toList());
        List<KbsTopicCategory> kbsTopicCategories = kbsTopicCategoryService.listKbsTopicCategoryByIds(categoryIds);
        Map<String, List<KbsTopicCategory>> categoryListMap = kbsTopicCategories.stream().collect(Collectors.groupingBy(KbsTopicCategory::getId));

        List<KbsTopicInfoVo> kbsTopicInfoVos = new ArrayList<>();
        kbsTopicInfos.forEach(e -> {
            KbsTopicInfoVo kbsTopicInfoVo = KbsSourceTargetMapper.INSTANCE.convertTopicInfo2TopicInfoVo(e);
            if (categoryListMap.containsKey(kbsTopicInfoVo.getCategoryId())) {
                kbsTopicInfoVo.setCategoryName(categoryListMap.get(kbsTopicInfoVo.getCategoryId()).get(0).getName());
            }
            kbsTopicInfoVos.add(kbsTopicInfoVo);
        });
        return kbsTopicInfoVos;
    }

    /**
     * 新增知识库主题
     *
     * @param kbsTopicInfoVo 知识库主题
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveKbsTopicInfo(KbsTopicInfoVo kbsTopicInfoVo) {
        KbsTopicInfo kbsTopicInfo = KbsSourceTargetMapper.INSTANCE.convertTopicInfoVo2TopicInfo(kbsTopicInfoVo);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsTopicInfo.setId(IdUtils.fastSimpleUUID());
        kbsTopicInfo.setCreateId(loginUser.getUserId());
        kbsTopicInfo.setCreateBy(loginUser.getUser().getNickName());
        kbsTopicInfo.setCreateTime(DateUtils.getNowDate());
        if (!StringUtils.equals(kbsTopicInfoVo.getVisualScope(), VisualScopeEnum.OWNER.getCode())) {
            List<KbsTopicAuthUser> scopeAuthUsers = getScopeAuthUsers(kbsTopicInfo.getId(), kbsTopicInfoVo);
            List<KbsTopicAuthUser> operateAuthUsers = getOperateAuthUsers(kbsTopicInfo.getId(), kbsTopicInfoVo);
            scopeAuthUsers.addAll(operateAuthUsers);
            if (CollectionUtils.isNotEmpty(scopeAuthUsers)) {
                kbsUserAuthUserService.saveBatch(scopeAuthUsers);
            }
        }
        return kbsTopicInfoMapper.insertKbsTopicInfo(kbsTopicInfo);
    }

    /**
     * 修改知识库主题
     *
     * @param kbsTopicInfoVo 知识库主题
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateKbsTopicInfoVo(KbsTopicInfoVo kbsTopicInfoVo) {
        KbsTopicInfo kbsTopicInfo = KbsSourceTargetMapper.INSTANCE.convertTopicInfoVo2TopicInfo(kbsTopicInfoVo);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsTopicInfo.setUpdateId(loginUser.getUserId());
        kbsTopicInfo.setUpdateBy(loginUser.getUser().getNickName());
        kbsTopicInfo.setUpdateTime(DateUtils.getNowDate());

        // 处理权限用户
        kbsUserAuthUserService.deleteByTopicIds(TransferUtil.objectToArray(kbsTopicInfo.getId()));
        List<KbsTopicAuthUser> scopeAuthUsers = getScopeAuthUsers(kbsTopicInfo.getId(), kbsTopicInfoVo);
        List<KbsTopicAuthUser> operateAuthUsers = getOperateAuthUsers(kbsTopicInfo.getId(), kbsTopicInfoVo);
        scopeAuthUsers.addAll(operateAuthUsers);
        if (CollectionUtils.isNotEmpty(scopeAuthUsers)) {
            kbsUserAuthUserService.saveBatch(scopeAuthUsers);
        }
        return kbsTopicInfoMapper.updateKbsTopicInfo(kbsTopicInfo);
    }

    /**
     * 批量删除知识库主题
     *
     * @param ids 需要删除的知识库主题主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteKbsTopicInfoByIds(String[] ids) {
        kbsTopicInfoMapper.deleteKbsTopicInfoByIds(ids);
        return kbsUserAuthUserService.deleteByTopicIds(ids);
    }

    /**
     * 软批量删除知识库主题
     *
     * @param ids 需要删除的知识库主题主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int softDeleteKbsTopicInfoByIds(String[] ids) {
        List<KbsTopicInfo> kbsTopicInfos = kbsTopicInfoMapper.selectKbsTopicInfoByIds(TransferUtil.arrayToList(ids));
        if (CollectionUtils.isEmpty(kbsTopicInfos)) {
            throw new BaseException("主题为空，删除失败！");
        }
        updateBatch(kbsTopicInfos, WhetherStatus.NO.getCode());
        return kbsRecycleService.saveBatch(buildTopicInfoRecycle(kbsTopicInfos));
    }

    @Override
    public List<KbsTopicInfo> listKbsTopicInfoByIds(List<String> ids) {
        return kbsTopicInfoMapper.selectKbsTopicInfoByIds(ids);
    }

    /**
     * 批量更新
     *
     * @param updateTopicInfos 更新的主题信息
     * @param delFlag          删除标识
     * @return 结果
     */
    @Override
    public int updateBatch(List<KbsTopicInfo> updateTopicInfos, String delFlag) {
        if (CollectionUtils.isEmpty(updateTopicInfos)) {
            return 0;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        KbsTopicInfoUpdateQo updateQo = new KbsTopicInfoUpdateQo();
        updateQo.setDelFlag(delFlag);
        updateQo.setUpdateId(loginUser.getUserId());
        updateQo.setUpdateBy(loginUser.getUser().getNickName());
        updateQo.setUpdateTime(DateUtils.getNowDate());
        updateQo.setIds(updateTopicInfos.stream().map(KbsTopicInfo::getId).collect(Collectors.toList()));
        return kbsTopicInfoMapper.batchUpdate(updateQo);
    }

    /**
     * 根据主题类别id查询主题信息
     *
     * @param categoryIds 主题类别ID集合
     * @return 结果
     */
    @Override
    public List<KbsTopicInfo> listByCategoryIds(List<String> categoryIds) {
        return kbsTopicInfoMapper.selectListByCategoryIds(categoryIds);
    }

    /**
     * 获取知识库主题列表，按类别进行分组展示
     *
     * @return 结果
     */
    @Override
    public List<KbsTopicModel> listTopicGroupByCategory(KbsTopicInfoQo qo) {
        // 获取当前处理人有权限查看的主题
        List<KbsTopicAuthUser> kbsTopicAuthUsers = kbsTopicAuthUserService.listKbsTopicAuthUserByUserId(SecurityUtils.getUserId());
        Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMap = getkbsTopicAuthUserMap(kbsTopicAuthUsers);

        List<KbsTopicInfoVo> kbsTopicInfoVos = kbsTopicInfoMapper.selectTopicListGroupByCategory(qo);
        // 过滤没有权限可见的主题
        kbsTopicInfoVos.removeIf(kbsTopicInfoVo -> isTopicInvisible(kbsTopicInfoVo, kbsTopicAuthUserMap));
        // 按类别分组
        Map<String, List<KbsTopicInfoVo>> kbsTopicInfoVoMap = kbsTopicInfoVos.stream()
                .collect(Collectors.groupingBy(KbsTopicInfoVo::getCategoryId));
        List<KbsTopicModel> kbsTopicModels = new ArrayList<>();
        for (Map.Entry<String, List<KbsTopicInfoVo>> entry : kbsTopicInfoVoMap.entrySet()) {
            KbsTopicModel kbsTopicModel = new KbsTopicModel();
            kbsTopicModel.setCategoryId(entry.getKey());
            KbsTopicInfoVo kbsTopicInfoVo = entry.getValue().get(0);
            kbsTopicModel.setCategoryName(kbsTopicInfoVo.getCategoryName());
            kbsTopicModel.setCreateId(kbsTopicInfoVo.getCreateId());
            kbsTopicModel.setTopicInfos(setManageFlag(entry.getValue(), kbsTopicAuthUserMap));
            kbsTopicModels.add(kbsTopicModel);
        }
        return kbsTopicModels;
    }

    /**
     * 获取主题对应的用户
     *
     * @param authUsers
     * @param topicSysUserMap
     * @return
     */
    private List<SysUserVo> getTopicSysUsers(List<KbsTopicAuthUser> authUsers, Map<String, SysUserVo> topicSysUserMap) {
        return authUsers.stream()
                .map(e -> {
                    SysUserVo sysUserVo = topicSysUserMap.get(e.getUserId());
                    sysUserVo.setSort(e.getSort());
                    return sysUserVo;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 获取查看权限用户
     *
     * @param kbsTopicInfoVo 主题
     */
    private List<KbsTopicAuthUser> getScopeAuthUsers(String topicId, KbsTopicInfoVo kbsTopicInfoVo) {
        List<KbsTopicAuthUser> kbsTopicAuthUsers = new ArrayList<>();
        if (CollectionUtils.isEmpty(kbsTopicInfoVo.getVisualScopeAuthUsers()) || !StringUtils.equals(VisualScopeEnum.PART.getCode(), kbsTopicInfoVo.getVisualScope())) {
            return kbsTopicAuthUsers;
        }
        Map<String, KbsTopicAuthUser> kbsTopicAuthUserMap = new HashMap<>();
        kbsTopicInfoVo.getVisualScopeAuthUsers().forEach(e -> {
            e.setId(IdUtils.fastSimpleUUID());
            e.setTopicId(topicId);
            kbsTopicAuthUsers.add(e);
            kbsTopicAuthUserMap.put(e.getUserId(), e);
        });
        return kbsTopicAuthUsers;
    }

    /**
     * 获取操作权限用户
     *
     * @param topicId
     * @param kbsTopicInfoVo
     * @return
     */
    private List<KbsTopicAuthUser> getOperateAuthUsers(String topicId, KbsTopicInfoVo kbsTopicInfoVo) {
        List<KbsTopicAuthUser> kbsTopicAuthUsers = new ArrayList<>();
        if (CollectionUtils.isEmpty(kbsTopicInfoVo.getOperateTypeAuthUsers()) || StringUtils.equals(VisualScopeEnum.OWNER.getCode(), kbsTopicInfoVo.getVisualScope())) {
            return kbsTopicAuthUsers;
        }
        if (StringUtils.equals(VisualScopeEnum.ALL.getCode(), kbsTopicInfoVo.getVisualScope())) {
            // 全部可见且操作权限为只读时，不需要设置权限
            if (StringUtils.equals(OperateTypeEnum.READ.getCode(), kbsTopicInfoVo.getOperateType())) {
                return kbsTopicAuthUsers;
            }
            kbsTopicInfoVo.getOperateTypeAuthUsers().forEach(e -> {
                e.setId(IdUtils.fastSimpleUUID());
                e.setTopicId(topicId);
                kbsTopicAuthUsers.add(e);
            });
            return kbsTopicAuthUsers;
        }
        // 部分可见时，需要取交集
        Map<String, KbsTopicAuthUser> kbsTopicAuthUserMap = kbsTopicInfoVo.getVisualScopeAuthUsers()
                .stream()
                .collect(Collectors.toMap(KbsTopicAuthUser::getUserId, e -> e));
        kbsTopicInfoVo.getOperateTypeAuthUsers().forEach(e -> {
            e.setId(IdUtils.fastSimpleUUID());
            e.setTopicId(topicId);
            if (kbsTopicAuthUserMap.containsKey(e.getUserId())) {
                kbsTopicAuthUsers.add(e);
            }
        });
        return kbsTopicAuthUsers;
    }

    /**
     * 构建主题回收信息
     *
     * @param kbsTopicInfos 主题信息列表
     */
    private List<KbsRecycle> buildTopicInfoRecycle(List<KbsTopicInfo> kbsTopicInfos) {
        List<KbsRecycle> recycles = new ArrayList<>();
        kbsTopicInfos.forEach(kbsTopicInfo -> {
            KbsRecycle recycle = new KbsRecycle();
            recycle.setId(IdUtils.fastSimpleUUID());
            recycle.setObjectId(kbsTopicInfo.getId());
            recycle.setObjectName(kbsTopicInfo.getName());
            recycle.setObjectType(ObjectTypeEnum.TOPIC.getCode());
            recycle.setParentObjectId(kbsTopicInfo.getCategoryId());
            LoginUser loginUser = SecurityUtils.getLoginUser();
            recycle.setCreateId(loginUser.getUserId());
            recycle.setCreateBy(loginUser.getUser().getNickName());
            recycle.setCreateTime(DateUtils.getNowDate());
            recycles.add(recycle);
        });
        return recycles;
    }

    /**
     * 设置主题管理标识
     *
     * @param kbsTopicInfoVos     主题信息
     * @param kbsTopicAuthUserMap 主题权限信息
     * @return
     */
    private List<KbsTopicInfoVo> setManageFlag(List<KbsTopicInfoVo> kbsTopicInfoVos, Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMap) {
        for (KbsTopicInfoVo kbsTopicInfoVo : kbsTopicInfoVos) {
            kbsTopicInfoVo.setManageFlag(getManageFlag(kbsTopicInfoVo, kbsTopicAuthUserMap));
        }
        return kbsTopicInfoVos;
    }

    /**
     * 根据主键查询所有信息
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public KbsTopicAllInfoVo getAllTopicInfo(String id) {
        KbsTopicInfo kbsTopicInfo = kbsTopicInfoMapper.selectKbsTopicInfoById(id);
        if (kbsTopicInfo == null) {
            throw new BaseException("主题不存在！");
        }
        KbsTopicAllInfoVo kbsTopicAllInfoVo = KbsSourceTargetMapper.INSTANCE.convertTopicInfo2TopicAllInfoVo(kbsTopicInfo);
        kbsTopicAllInfoVo.setDocNums(kbsDocumentBaseService.countNumByTopicId(id));
        kbsTopicAllInfoVo.setFavoriteFlag(Objects.nonNull(kbsFavoriteService.getKbsFavoriteByUserId(id)));
        KbsTopicAuthUser kbsTopicAuthUser = kbsTopicAuthUserService.getMyTopicAuthUser(kbsTopicInfo.getId());
        Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMap = new HashMap<>();
        Map<String, List<KbsTopicAuthUser>> kbsTopicAuthUserMapByType = new HashMap<>();
        kbsTopicAuthUserMapByType.put(kbsTopicInfo.getVisualScope(),
                Objects.nonNull(kbsTopicAuthUser) ? Collections.singletonList(kbsTopicAuthUser) : Collections.emptyList());
        kbsTopicAuthUserMap.put(kbsTopicInfo.getId(), kbsTopicAuthUserMapByType);
        kbsTopicAllInfoVo.setManageFlag(getManageFlag(kbsTopicInfo, kbsTopicAuthUserMap));
        return kbsTopicAllInfoVo;
    }

    /**
     * 获取主题管理标识
     *
     * @param kbsTopicInfo
     * @param kbsTopicAuthUserMap
     * @return
     */
    private boolean getManageFlag(KbsTopicInfo kbsTopicInfo, Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMap) {
        if (kbsTopicInfo.getCreateId().equals(SecurityUtils.getUserId())) {
            return true;
        }
        Map<String, List<KbsTopicAuthUser>> kbsTopicAuthUserMapByType = kbsTopicAuthUserMap.get(kbsTopicInfo.getId());
        if (MapUtils.isEmpty(kbsTopicAuthUserMapByType)) {
            return false;
        }
        return CollectionUtils.isNotEmpty(kbsTopicAuthUserMapByType.get(OperateTypeEnum.EDIT.getCode()));
    }

    /**
     * 主题是否不可见
     *
     * @param kbsTopicInfoVo      主题信息
     * @param kbsTopicAuthUserMap 主题权限信息
     * @return
     */
    private boolean isTopicInvisible(KbsTopicInfoVo kbsTopicInfoVo, Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMap) {
        // 创建人
        if (kbsTopicInfoVo.getCreateId().equals(SecurityUtils.getUserId())) {
            return false;
        }
        // 可见范围是全部可见/部分可见
        if (!VisualScopeEnum.PART.getCode().equals(kbsTopicInfoVo.getVisualScope())) {
            return false;
        }
        // 拥有主题可见权限
        return !kbsTopicAuthUserMap.containsKey(kbsTopicInfoVo.getId());
    }

    /**
     * 获取主题权限信息
     *
     * @param kbsTopicAuthUsers 主题权限信息
     * @return Map<String, Map < String, List < KbsTopicAuthUser>>> Map<键：主题ID， Map<键：权限类型，值：主题权限信息>>
     */
    private Map<String, Map<String, List<KbsTopicAuthUser>>> getkbsTopicAuthUserMap(List<KbsTopicAuthUser> kbsTopicAuthUsers) {
        // 按主题ID进行分组
        Map<String, List<KbsTopicAuthUser>> kbsTopicAuthUserMap = kbsTopicAuthUsers.stream()
                .collect(Collectors.groupingBy(KbsTopicAuthUser::getTopicId));
        Map<String, Map<String, List<KbsTopicAuthUser>>> kbsTopicAuthUserMapByTopicId = new HashMap<>();
        // 按权限类型分组
        kbsTopicAuthUserMap.forEach((key, value) -> {
            Map<String, List<KbsTopicAuthUser>> kbsTopicAuthUserMapByType = value.stream()
                    .collect(Collectors.groupingBy(KbsTopicAuthUser::getType));
            kbsTopicAuthUserMapByTopicId.put(key, kbsTopicAuthUserMapByType);
        });
        return kbsTopicAuthUserMapByTopicId;
    }

    /**
     * 获取主题对应的用户列表映射
     *
     * @param kbsTopicAuthUsers 主题权限用户列表
     * @return
     */
    private Map<String, SysUserVo> getTopicSysUserListMap(List<KbsTopicAuthUser> kbsTopicAuthUsers) {
        // 去重
        Set<String> userIds = kbsTopicAuthUsers.stream().map(KbsTopicAuthUser::getUserId).collect(Collectors.toSet());
        List<SysUser> topicSysUsers = sysUserService.selectDetailByUserIds(new ArrayList<>(userIds));

        return topicSysUsers.stream()
                .collect(Collectors.toMap(SysUser::getUserId, KbsSourceTargetMapper.INSTANCE::convertSysUser2SysUserVo));
    }

    /**
     * 处理可见范围用户权限
     *
     * @param kbsTopicInfoVo      知识库主题信息
     * @param kbsTopicAuthUsers   主题权限用户列表
     * @param topicSysUserListMap 主题对应的用户列表映射
     */
    private void handleVisualScope(KbsTopicInfoVo kbsTopicInfoVo, List<KbsTopicAuthUser> kbsTopicAuthUsers, Map<String, SysUserVo> topicSysUserListMap) {
        List<KbsTopicAuthUser> visualScopeAuthUsers = kbsTopicAuthUsers.stream()
                .filter(e -> AuthUserTypeEnum.VISUAL_SCOPE.getCode().equals(e.getType()))
                .collect(Collectors.toList());
        kbsTopicInfoVo.setVisualScopeAuthUsers(visualScopeAuthUsers);
        kbsTopicInfoVo.setVisualScopeSysUsers(getTopicSysUsers(visualScopeAuthUsers, topicSysUserListMap));
    }

    /**
     * 处理操作类型用户权限
     *
     * @param kbsTopicInfoVo
     * @param kbsTopicAuthUsers
     * @param topicSysUserListMap
     */
    private void handleOperateType(KbsTopicInfoVo kbsTopicInfoVo, List<KbsTopicAuthUser> kbsTopicAuthUsers, Map<String, SysUserVo> topicSysUserListMap) {
        List<KbsTopicAuthUser> operateTypeAuthUsers = kbsTopicAuthUsers.stream()
                .filter(e -> AuthUserTypeEnum.OPERATE_TYPE.getCode().equals(e.getType()))
                .collect(Collectors.toList());
        kbsTopicInfoVo.setOperateTypeAuthUsers(operateTypeAuthUsers);
        kbsTopicInfoVo.setOperateTypeSysUsers((getTopicSysUsers(operateTypeAuthUsers, topicSysUserListMap)));
    }
}
