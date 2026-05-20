package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsFavorite;
import com.ruoyi.kbs.domain.qo.KbsFavoriteQo;

/**
 * 知识库收藏Mapper接口
 * 
 * @author wocurr.com
 */
public interface KbsFavoriteMapper {
    /**
     * 查询知识库收藏
     * 
     * @param id 知识库收藏主键
     * @return 知识库收藏
     */
    public KbsFavorite selectKbsFavoriteById(String id);

    /**
     * 查询知识库收藏列表
     * 
     * @param kbsFavorite 知识库收藏
     * @return 知识库收藏集合
     */
    public List<KbsFavorite> selectKbsFavoriteList(KbsFavorite kbsFavorite);

    /**
     * 新增知识库收藏
     * 
     * @param kbsFavorite 知识库收藏
     * @return 结果
     */
    public int insertKbsFavorite(KbsFavorite kbsFavorite);

    /**
     * 修改知识库收藏
     * 
     * @param kbsFavorite 知识库收藏
     * @return 结果
     */
    public int updateKbsFavorite(KbsFavorite kbsFavorite);

    /**
     * 删除知识库收藏
     * 
     * @param id 知识库收藏主键
     * @return 结果
     */
    public int deleteKbsFavoriteById(String id);

    /**
     * 批量删除知识库收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsFavoriteByIds(String[] ids);

    /**
     * 根据组ID批量删除知识收藏
     *
     * @param ids 组ID集合
     * @return 结果
     */
    int deleteKbsFavoriteByGroupIds(String[] ids);

    /**
     * 根据文档ID统计收藏次数
     *
     * @param docId 文档ID
     * @return 结果
     */
    Long statDocumentFavoriteNum(String docId);

    /**
     * 根据用户ID查询收藏
     *
     * @param qo 查询对象
     * @return
     */
    List<KbsFavorite> selectKbsFavoriteByUserId(KbsFavoriteQo qo);

    /**
     * 根据文档ID和用户ID取消收藏
     *
     * @param qo 查询对象
     * @return
     */
    int deleteFavoriteByDocUser(KbsFavoriteQo qo);

    /**
     * 根据文档ID批量删除
     * @param list
     * @return
     */
    int deleteKbsFavoriteObjectIds(List<String> list);
}
