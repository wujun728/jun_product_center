package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsTopicCategory;
import com.ruoyi.kbs.domain.KbsTopicCategoryOption;

import java.util.List;

/**
 * 知识库主题类别Service接口
 * 
 * @author wocurr.com
 */
public interface IKbsTopicCategoryService {
    /**
     * 查询知识库主题类别
     * 
     * @param id 知识库主题类别主键
     * @return 知识库主题类别
     */
    public KbsTopicCategory getKbsTopicCategoryById(String id);

    /**
     * 查询知识库主题类别列表
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 知识库主题类别集合
     */
    public List<KbsTopicCategory> listKbsTopicCategory(KbsTopicCategory kbsTopicCategory);

    /**
     * 新增知识库主题类别
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    public int saveKbsTopicCategory(KbsTopicCategory kbsTopicCategory);

    /**
     * 修改知识库主题类别
     * 
     * @param kbsTopicCategory 知识库主题类别
     * @return 结果
     */
    public int updateKbsTopicCategory(KbsTopicCategory kbsTopicCategory);

    /**
     * 批量删除知识库主题类别
     * 
     * @param ids 需要删除的知识库主题类别主键集合
     * @return 结果
     */
    public int deleteKbsTopicCategoryByIds(String[] ids);

    /**
     * 获取主题类别下拉列表
     *
     * @return 结果
     */
    List<KbsTopicCategoryOption> getCategorySelectList();

    /**
     * 根据ID列表查询知识库主题类别
     *
     * @param ids 知识库主题类别ID列表
     * @return
     */
    List<KbsTopicCategory> listKbsTopicCategoryByIds(List<String> ids);

    /**
     * 批量更新
     *
     * @param kbsTopicCategories
     * @return 结果
     */
    int updateBatch(List<KbsTopicCategory> kbsTopicCategories);
}
