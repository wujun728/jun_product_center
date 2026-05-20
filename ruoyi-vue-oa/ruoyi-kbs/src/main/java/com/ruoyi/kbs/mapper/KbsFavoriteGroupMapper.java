package com.ruoyi.kbs.mapper;

import java.util.List;

import com.ruoyi.kbs.domain.KbsFavoriteGroupOption;
import com.ruoyi.kbs.domain.KbsFavoriteGroup;
import com.ruoyi.kbs.domain.qo.KbsFavoriteGroupUpdateQo;

/**
 * 知识库收藏组Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsFavoriteGroupMapper {
    /**
     * 查询知识库收藏组
     * 
     * @param id 知识库收藏组主键
     * @return 知识库收藏组
     */
    public KbsFavoriteGroup selectKbsFavoriteGroupById(String id);

    /**
     * 查询知识库收藏组列表
     * 
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 知识库收藏组集合
     */
    public List<KbsFavoriteGroup> selectKbsFavoriteGroupList(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 新增知识库收藏组
     * 
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    public int insertKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 修改知识库收藏组
     * 
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    public int updateKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup);

    /**
     * 获取收藏组下拉列表
     *
     * @return 结果
     */
    List<KbsFavoriteGroupOption> selectGroupSelectList(String userId);

    /**
     * 根据收藏组ID集合查询收藏组信息
     *
     * @param groupIds 收藏组ID集合
     * @return 结果
     */
    List<KbsFavoriteGroup> selectKbsFavoriteGroupByIds(List<String> groupIds);

    /**
     * 批量更新删除标识
     *
     * @param qo 更新对象
     * @return 结果
     */
    int updateDelFlagByIds(KbsFavoriteGroupUpdateQo qo);
}
