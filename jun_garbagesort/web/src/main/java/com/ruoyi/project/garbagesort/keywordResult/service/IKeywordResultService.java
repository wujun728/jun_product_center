package com.ruoyi.project.garbagesort.keywordResult.service;

import java.util.List;
import com.ruoyi.project.garbagesort.keywordResult.domain.KeywordResult;

/**
 * 关键词+结果记录Service接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface IKeywordResultService 
{
    /**
     * 查询关键词+结果记录
     * 
     * @param id 关键词+结果记录ID
     * @return 关键词+结果记录
     */
    public KeywordResult selectKeywordResultById(Long id);

    /**
     * 查询关键词+结果记录列表
     * 
     * @param keywordResult 关键词+结果记录
     * @return 关键词+结果记录集合
     */
    public List<KeywordResult> selectKeywordResultList(KeywordResult keywordResult);

    /**
     * 新增关键词+结果记录
     * 
     * @param keywordResult 关键词+结果记录
     * @return 结果
     */
    public int insertKeywordResult(KeywordResult keywordResult);

    /**
     * 修改关键词+结果记录
     * 
     * @param keywordResult 关键词+结果记录
     * @return 结果
     */
    public int updateKeywordResult(KeywordResult keywordResult);

    /**
     * 批量删除关键词+结果记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKeywordResultByIds(String ids);

    /**
     * 删除关键词+结果记录信息
     * 
     * @param id 关键词+结果记录ID
     * @return 结果
     */
    public int deleteKeywordResultById(Long id);
}
