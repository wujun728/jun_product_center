package com.ruoyi.project.garbagesort.keywordSearchNum.mapper;

import java.util.List;
import com.ruoyi.project.garbagesort.keywordSearchNum.domain.KeywordSearchNum;

/**
 * 搜索关键词+次数记录Mapper接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface KeywordSearchNumMapper 
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
     * 删除搜索关键词+次数记录
     * 
     * @param id 搜索关键词+次数记录ID
     * @return 结果
     */
    public int deleteKeywordSearchNumById(Long id);

    /**
     * 批量删除搜索关键词+次数记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKeywordSearchNumByIds(String[] ids);
}
