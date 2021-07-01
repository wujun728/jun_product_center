package com.ruoyi.project.garbagesort.keywordSearchNum.service;

import java.util.List;
import com.ruoyi.project.garbagesort.keywordSearchNum.domain.KeywordSearchNum;

/**
 * 搜索关键词+次数记录Service接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface IKeywordSearchNumService 
{
    /**
     * 查询搜索关键词+次数记录
     * 
     * @param id 搜索关键词+次数记录ID
     * @return 搜索关键词+次数记录
     */
    public KeywordSearchNum selectKeywordSearchNumById(Long id);

    /**
     * 查询搜索关键词+次数记录列表
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 搜索关键词+次数记录集合
     */
    public List<KeywordSearchNum> selectKeywordSearchNumList(KeywordSearchNum keywordSearchNum);

    /**
     * 新增搜索关键词+次数记录
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 结果
     */
    public int insertKeywordSearchNum(KeywordSearchNum keywordSearchNum);

    /**
     * 修改搜索关键词+次数记录
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 结果
     */
    public int updateKeywordSearchNum(KeywordSearchNum keywordSearchNum);

    /**
     * 批量删除搜索关键词+次数记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKeywordSearchNumByIds(String ids);

    /**
     * 删除搜索关键词+次数记录信息
     * 
     * @param id 搜索关键词+次数记录ID
     * @return 结果
     */
    public int deleteKeywordSearchNumById(Long id);
}
