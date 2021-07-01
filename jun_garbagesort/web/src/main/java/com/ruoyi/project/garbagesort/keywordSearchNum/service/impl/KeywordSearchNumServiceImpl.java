package com.ruoyi.project.garbagesort.keywordSearchNum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.keywordSearchNum.mapper.KeywordSearchNumMapper;
import com.ruoyi.project.garbagesort.keywordSearchNum.domain.KeywordSearchNum;
import com.ruoyi.project.garbagesort.keywordSearchNum.service.IKeywordSearchNumService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 搜索关键词+次数记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class KeywordSearchNumServiceImpl implements IKeywordSearchNumService 
{
    @Autowired
    private KeywordSearchNumMapper keywordSearchNumMapper;

    /**
     * 查询搜索关键词+次数记录
     * 
     * @param id 搜索关键词+次数记录ID
     * @return 搜索关键词+次数记录
     */
    @Override
    public KeywordSearchNum selectKeywordSearchNumById(Long id)
    {
        return keywordSearchNumMapper.selectKeywordSearchNumById(id);
    }

    /**
     * 查询搜索关键词+次数记录列表
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 搜索关键词+次数记录
     */
    @Override
    public List<KeywordSearchNum> selectKeywordSearchNumList(KeywordSearchNum keywordSearchNum)
    {
        return keywordSearchNumMapper.selectKeywordSearchNumList(keywordSearchNum);
    }

    /**
     * 新增搜索关键词+次数记录
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 结果
     */
    @Override
    public int insertKeywordSearchNum(KeywordSearchNum keywordSearchNum)
    {
        return keywordSearchNumMapper.insertKeywordSearchNum(keywordSearchNum);
    }

    /**
     * 修改搜索关键词+次数记录
     * 
     * @param keywordSearchNum 搜索关键词+次数记录
     * @return 结果
     */
    @Override
    public int updateKeywordSearchNum(KeywordSearchNum keywordSearchNum)
    {
        return keywordSearchNumMapper.updateKeywordSearchNum(keywordSearchNum);
    }

    /**
     * 删除搜索关键词+次数记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKeywordSearchNumByIds(String ids)
    {
        return keywordSearchNumMapper.deleteKeywordSearchNumByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除搜索关键词+次数记录信息
     * 
     * @param id 搜索关键词+次数记录ID
     * @return 结果
     */
    @Override
    public int deleteKeywordSearchNumById(Long id)
    {
        return keywordSearchNumMapper.deleteKeywordSearchNumById(id);
    }
}
