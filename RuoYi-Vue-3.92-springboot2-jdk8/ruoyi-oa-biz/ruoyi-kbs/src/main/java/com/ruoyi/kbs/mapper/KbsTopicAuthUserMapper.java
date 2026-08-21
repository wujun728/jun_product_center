package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsTopicAuthUser;
import com.ruoyi.kbs.domain.qo.KbsTopicAuthUserQo;
import com.ruoyi.kbs.domain.qo.KbsTopicAuthUserUpdateQo;

/**
 * 知识库主题权限用户Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsTopicAuthUserMapper {
    /**
     * 查询知识库主题权限用户
     * 
     * @param id 知识库主题权限用户主键
     * @return 知识库主题权限用户
     */
    public KbsTopicAuthUser selectKbsTopicAuthUserById(String id);

    /**
     * 查询知识库主题权限用户列表
     * 
     * @param kbsTopicAuthUser 知识库主题权限用户
     * @return 知识库主题权限用户集合
     */
    public List<KbsTopicAuthUser> selectKbsTopicAuthUserList(KbsTopicAuthUser kbsTopicAuthUser);

    /**
     * 新增知识库主题权限用户
     * 
     * @param kbsTopicAuthUser 知识库主题权限用户
     * @return 结果
     */
    public int insertKbsTopicAuthUser(KbsTopicAuthUser kbsTopicAuthUser);

    /**
     * 修改知识库主题权限用户
     * 
     * @param kbsTopicAuthUser 知识库主题权限用户
     * @return 结果
     */
    public int updateKbsTopicAuthUser(KbsTopicAuthUser kbsTopicAuthUser);

    /**
     * 批量插入知识库主题权限用户信息
     *
     * @param authUsers 权限用户信息列表
     */
    void batchInsert(List<KbsTopicAuthUser> authUsers);

    /**
     * 根据主题id查询知识库主题权限用户信息
     *
     * @param topicId 主题ID
     * @return 结果
     */
    List<KbsTopicAuthUser> selectListByTopicId(String topicId);

    /**
     *  根据用户ID查询知识库主题权限用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<KbsTopicAuthUser> selectKbsTopicAuthUserByUserId(String userId);

    /**
     * 查询我的知识库主题权限用户信息
     *
     * @param qo 查询参数
     * @return 列表
     */
    List<KbsTopicAuthUser> selectMyTopicAuthUser(KbsTopicAuthUserQo qo);

    /**
     * 批量更新删除标识
     *
     * @param qo 更新对象
     * @return 结果
     */
    int updateDelFlagByTopicIds(KbsTopicAuthUserUpdateQo qo);
}
