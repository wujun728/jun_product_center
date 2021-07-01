package com.ruoyi.project.garbagesort.challengeResult.service;

import java.util.List;
import com.ruoyi.project.garbagesort.challengeResult.domain.ChallengeResult;

/**
 * 挑战结果+详情记录Service接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface IChallengeResultService 
{
    /**
     * 查询挑战结果+详情记录
     * 
     * @param id 挑战结果+详情记录ID
     * @return 挑战结果+详情记录
     */
    public ChallengeResult selectChallengeResultById(Long id);

    /**
     * 查询挑战结果+详情记录列表
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 挑战结果+详情记录集合
     */
    public List<ChallengeResult> selectChallengeResultList(ChallengeResult challengeResult);

    /**
     * 新增挑战结果+详情记录
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 结果
     */
    public int insertChallengeResult(ChallengeResult challengeResult);

    /**
     * 修改挑战结果+详情记录
     * 
     * @param challengeResult 挑战结果+详情记录
     * @return 结果
     */
    public int updateChallengeResult(ChallengeResult challengeResult);

    /**
     * 批量删除挑战结果+详情记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChallengeResultByIds(String ids);

    /**
     * 删除挑战结果+详情记录信息
     * 
     * @param id 挑战结果+详情记录ID
     * @return 结果
     */
    public int deleteChallengeResultById(Long id);
}
