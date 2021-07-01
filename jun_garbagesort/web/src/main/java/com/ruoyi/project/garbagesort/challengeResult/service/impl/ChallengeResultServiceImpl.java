package com.ruoyi.project.garbagesort.challengeResult.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.challengeResult.mapper.ChallengeResultMapper;
import com.ruoyi.project.garbagesort.challengeResult.domain.ChallengeResult;
import com.ruoyi.project.garbagesort.challengeResult.service.IChallengeResultService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 挑战结果+详情记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class ChallengeResultServiceImpl implements IChallengeResultService 
{
    @Autowired
    private ChallengeResultMapper challengeResultMapper;

    /**
     * 查询挑战结果+详情记录
     * 
     * @param id 挑战结果+详情记录ID
     * @return 挑战结果+详情记录
     */
    @Override
    public ChallengeResult selectChallengeResultById(Long id)
    {
        return challengeResultMapper.selectChallengeResultById(id);
    }

    /**
     * 查询挑战结果+详情记录列表
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 挑战结果+详情记录
     */
    @Override
    public List<ChallengeResult> selectChallengeResultList(ChallengeResult challengeResult)
    {
        return challengeResultMapper.selectChallengeResultList(challengeResult);
    }

    /**
     * 新增挑战结果+详情记录
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 结果
     */
    @Override
    public int insertChallengeResult(ChallengeResult challengeResult)
    {
        return challengeResultMapper.insertChallengeResult(challengeResult);
    }

    /**
     * 修改挑战结果+详情记录
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 结果
     */
    @Override
    public int updateChallengeResult(ChallengeResult challengeResult)
    {
        return challengeResultMapper.updateChallengeResult(challengeResult);
    }

    /**
     * 删除挑战结果+详情记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChallengeResultByIds(String ids)
    {
        return challengeResultMapper.deleteChallengeResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除挑战结果+详情记录信息
     * 
     * @param id 挑战结果+详情记录ID
     * @return 结果
     */
    @Override
    public int deleteChallengeResultById(Long id)
    {
        return challengeResultMapper.deleteChallengeResultById(id);
    }
}
