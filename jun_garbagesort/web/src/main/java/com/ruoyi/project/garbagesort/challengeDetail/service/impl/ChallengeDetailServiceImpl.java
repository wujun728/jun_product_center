package com.ruoyi.project.garbagesort.challengeDetail.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.challengeDetail.mapper.ChallengeDetailMapper;
import com.ruoyi.project.garbagesort.challengeDetail.domain.ChallengeDetail;
import com.ruoyi.project.garbagesort.challengeDetail.service.IChallengeDetailService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 挑战明细记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class ChallengeDetailServiceImpl implements IChallengeDetailService 
{
    @Autowired
    private ChallengeDetailMapper challengeDetailMapper;

    /**
     * 查询挑战明细记录
     * 
     * @param id 挑战明细记录ID
     * @return 挑战明细记录
     */
    @Override
    public ChallengeDetail selectChallengeDetailById(Long id)
    {
        return challengeDetailMapper.selectChallengeDetailById(id);
    }

    /**
     * 查询挑战明细记录列表
     * 
     * @param challengeDetail 挑战明细记录
     * @return 挑战明细记录
     */
    @Override
    public List<ChallengeDetail> selectChallengeDetailList(ChallengeDetail challengeDetail)
    {
        return challengeDetailMapper.selectChallengeDetailList(challengeDetail);
    }

    /**
     * 新增挑战明细记录
     * 
     * @param challengeDetail 挑战明细记录
     * @return 结果
     */
    @Override
    public int insertChallengeDetail(ChallengeDetail challengeDetail)
    {
        return challengeDetailMapper.insertChallengeDetail(challengeDetail);
    }

    /**
     * 修改挑战明细记录
     * 
     * @param challengeDetail 挑战明细记录
     * @return 结果
     */
    @Override
    public int updateChallengeDetail(ChallengeDetail challengeDetail)
    {
        return challengeDetailMapper.updateChallengeDetail(challengeDetail);
    }

    /**
     * 删除挑战明细记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChallengeDetailByIds(String ids)
    {
        return challengeDetailMapper.deleteChallengeDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除挑战明细记录信息
     * 
     * @param id 挑战明细记录ID
     * @return 结果
     */
    @Override
    public int deleteChallengeDetailById(Long id)
    {
        return challengeDetailMapper.deleteChallengeDetailById(id);
    }
}
