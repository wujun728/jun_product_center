package com.ruoyi.project.garbagesort.keywordResult.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.keywordResult.mapper.KeywordResultMapper;
import com.ruoyi.project.garbagesort.keywordResult.domain.KeywordResult;
import com.ruoyi.project.garbagesort.keywordResult.service.IKeywordResultService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 关键词+结果记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class KeywordResultServiceImpl implements IKeywordResultService 
{
    @Autowired
    private KeywordResultMapper keywordResultMapper;

    /**
     * 查询关键词+结果记录
     * 
     * @param id 关键词+结果记录ID
     * @return 关键词+结果记录
     */
    @Override
    public KeywordResult selectKeywordResultById(Long id)
    {
        return keywordResultMapper.selectKeywordResultById(id);
    }

    /**
     * 查询关键词+结果记录列表
     * 
     * @param keywordResult 关键词+结果记录
     * @return 关键词+结果记录
     */
    @Override
    public List<KeywordResult> selectKeywordResultList(KeywordResult keywordResult)
    {
        return keywordResultMapper.selectKeywordResultList(keywordResult);
    }

    /**
     * 新增关键词+结果记录
     * 
     * @param keywordResult 关键词+结果记录
     * @return 结果
     */
    @Override
    public int insertKeywordResult(KeywordResult keywordResult)
    {
        return keywordResultMapper.insertKeywordResult(keywordResult);
    }

    /**
     * 修改关键词+结果记录
     * 
     * @param keywordResult 关键词+结果记录
     * @return 结果
     */
    @Override
    public int updateKeywordResult(KeywordResult keywordResult)
    {
        return keywordResultMapper.updateKeywordResult(keywordResult);
    }

    /**
     * 删除关键词+结果记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKeywordResultByIds(String ids)
    {
        return keywordResultMapper.deleteKeywordResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关键词+结果记录信息
     * 
     * @param id 关键词+结果记录ID
     * @return 结果
     */
    @Override
    public int deleteKeywordResultById(Long id)
    {
        return keywordResultMapper.deleteKeywordResultById(id);
    }
}
