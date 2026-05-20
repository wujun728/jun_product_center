package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsFavorite;
import com.ruoyi.kbs.domain.vo.KbsFavoriteGroupVo;

import java.util.List;

/**
 * 知识库收藏Service接口
 *
 * @author wocurr.com
 */
public interface IKbsFavoriteService {

    /**
     * 查询知识库收藏列表
     *
     * @param kbsFavorite 知识库收藏
     * @return 知识库收藏集合
     */
    public List<KbsFavoriteGroupVo> listKbsFavorite(KbsFavorite kbsFavorite);

    /**
     * 新增知识库收藏
     *
     * @param kbsFavorite 知识库收藏
     * @return 结果
     */
    public int saveKbsFavorite(KbsFavorite kbsFavorite);

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
     * @param docId 文档ID
     * @return 结果
     */
    KbsFavorite getKbsFavoriteByUserId(String docId);

    /**
     * 取消收藏
     *
     * @param docId 文档ID
     * @return 结果
     */
    int cancelFavoriteByDocUser(String docId);

    /**
     * 批量删除收藏
     * @param objectIds
     * @return
     */
    int deleteKbsFavoriteIds(List<String> objectIds);
}
