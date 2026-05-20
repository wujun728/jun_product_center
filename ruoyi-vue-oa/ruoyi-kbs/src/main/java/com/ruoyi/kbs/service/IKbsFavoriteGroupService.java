package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsFavoriteGroup;
import com.ruoyi.kbs.domain.KbsFavoriteGroupOption;

import java.util.List;

/**
 * 知识库收藏组Service接口
 *
 * @author wocurr.com
 */
public interface IKbsFavoriteGroupService {
    /**
     * 查询知识库收藏组
     *
     * @param id 知识库收藏组主键
     * @return 知识库收藏组
     */
    public KbsFavoriteGroup getKbsFavoriteGroupById(String id);

    /**
     * 查询知识库收藏组列表
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 知识库收藏组集合
     */
    public List<KbsFavoriteGroup> listKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 新增知识库收藏组
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    public int saveKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 修改知识库收藏组
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    public int updateKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 批量删除知识库收藏组
     *
     * @param ids 需要删除的知识库收藏组主键集合
     * @return 结果
     */
    public int deleteKbsFavoriteGroupByIds(String[] ids);

    /**
     * 获取收藏组下拉列表
     *
     * @return 结果
     */
    List<KbsFavoriteGroupOption> getGroupSelectList();

    /**
     * 根据收藏组ID集合查询收藏组信息
     *
     * @param groupIds 收藏组ID集合
     * @return 结果
     */
    List<KbsFavoriteGroup> listKbsFavoriteGroupByIds(List<String> groupIds);
}
