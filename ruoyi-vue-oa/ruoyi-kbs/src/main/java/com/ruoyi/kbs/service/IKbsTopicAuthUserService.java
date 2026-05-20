package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsTopicAuthUser;

import java.util.List;

/**
 * 知识库主题权限用户Service接口
 *
 * @author wocurr.com
 */
public interface IKbsTopicAuthUserService {

    /**
     * 批量插入知识库主题权限用户信息
     *
     * @param authUsers 权限用户信息列表
     */
    void saveBatch(List<KbsTopicAuthUser> authUsers);

    /**
     * 根据主题id批量删除知识库主题权限用户信息
     *
     * @param topicIds 主题ID集合
     */
    int deleteByTopicIds(String[] topicIds);

    /**
     * 根据主题id查询知识库主题权限用户信息
     *
     * @param topicId 主题ID
     * @return 结果
     */
    List<KbsTopicAuthUser> listByTopicId(String topicId);

    /**
     * 根据用户ID查询知识库主题权限用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<KbsTopicAuthUser> listKbsTopicAuthUserByUserId(String userId);

    /**
     * 根据主题ID查询权限用户信息
     *
     * @param topicId
     * @return 结果
     */
    KbsTopicAuthUser getMyTopicAuthUser(String topicId);
}
