package com.ruoyi.project.garbagesort.challengeDetail.service;

import java.util.List;
import com.ruoyi.project.garbagesort.challengeDetail.domain.ChallengeDetail;

/**
 * 挑战明细记录Service接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface IChallengeDetailService 
{
    /**
     * 查询挑战明细记录
     * 
     * @param id 挑战明细记录ID
     * @return 挑战明细记录
     */
    public ChallengeDetail selectChallengeDetailById(Long id);

    /**
     * 查询挑战明细记录列表
     * 
     * @param challengeDetail 挑战明细记录
     * @return 挑战明细记录集合
     */
    public List<ChallengeDetail> selectChallengeDetailList(ChallengeDetail challengeDetail);

    /**
     * 新增挑战明细记录
     * 
     * @param challengeDetail 挑战明细记录
     * @return 结果
     */
    public int insertChallengeDetail(ChallengeDetail challengeDetail);

    /**
     * 修改挑战明细记录
     * 
     * @param challengeDetail 挑战明细记录
     * @return 结果
     */
    public int updateChallengeDetail(ChallengeDetail challengeDetail);

    /**
     * 批量删除挑战明细记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChallengeDetailByIds(String ids);

    /**
     * 删除挑战明细记录信息
     * 
     * @param id 挑战明细记录ID
     * @return 结果
     */
    public int deleteChallengeDetailById(Long id);
}
