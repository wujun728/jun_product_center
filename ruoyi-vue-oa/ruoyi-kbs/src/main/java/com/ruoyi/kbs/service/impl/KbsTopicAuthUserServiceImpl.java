package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsTopicAuthUser;
import com.ruoyi.kbs.domain.qo.KbsTopicAuthUserQo;
import com.ruoyi.kbs.domain.qo.KbsTopicAuthUserUpdateQo;
import com.ruoyi.kbs.mapper.KbsTopicAuthUserMapper;
import com.ruoyi.kbs.service.IKbsTopicAuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 知识库主题权限用户Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsTopicAuthUserServiceImpl implements IKbsTopicAuthUserService {
    @Autowired
    private KbsTopicAuthUserMapper kbsTopicAuthUserMapper;

    /**
     * 批量插入知识库主题权限用户信息
     *
     * @param authUsers 权限用户信息列表
     */
    @Override
    public void saveBatch(List<KbsTopicAuthUser> authUsers) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        for (KbsTopicAuthUser authUser : authUsers) {
            authUser.setId(IdUtils.fastSimpleUUID());
            authUser.setCreateId(loginUser.getUserId());
            authUser.setCreateTime(DateUtils.getNowDate());
        }
        kbsTopicAuthUserMapper.batchInsert(authUsers);
    }

    /**
     * 根据主题id批量删除知识库主题权限用户信息
     *
     * @param topicIds 主题ID集合
     */
    @Override
    public int deleteByTopicIds(String[] topicIds) {
        KbsTopicAuthUserUpdateQo qo = new KbsTopicAuthUserUpdateQo();
        qo.setTopicIds(topicIds);
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserId());
        qo.setUpdateTime(DateUtils.getNowDate());
        return kbsTopicAuthUserMapper.updateDelFlagByTopicIds(qo);
    }

    /**
     * 根据主题id查询知识库主题权限用户信息
     *
     * @param topicId 主题ID
     * @return 结果
     */
    @Override
    public List<KbsTopicAuthUser> listByTopicId(String topicId) {
        return kbsTopicAuthUserMapper.selectListByTopicId(topicId);
    }

    /**
     * 根据用户ID查询知识库主题权限用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<KbsTopicAuthUser> listKbsTopicAuthUserByUserId(String userId) {
        return kbsTopicAuthUserMapper.selectKbsTopicAuthUserByUserId(userId);
    }

    /**
     * 根据主题ID查询权限用户信息
     *
     * @param topicId
     * @return 结果
     */
    @Override
    public KbsTopicAuthUser getMyTopicAuthUser(String topicId) {
        KbsTopicAuthUserQo qo = new KbsTopicAuthUserQo();
        qo.setTopicId(topicId);
        qo.setUserId(SecurityUtils.getUserId());
        List<KbsTopicAuthUser> kbsTopicAuthUsers = kbsTopicAuthUserMapper.selectMyTopicAuthUser(qo);
        if (CollectionUtils.isEmpty(kbsTopicAuthUsers)) {
            return null;
        }
        return kbsTopicAuthUsers.get(0);
    }
}
