package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserResumeMapper;
import com.ruoyi.qixing.domain.HrUserResume;
import com.ruoyi.qixing.service.IHrUserResumeService;

/**
 * 面试候选人Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserResumeServiceImpl implements IHrUserResumeService
{
    @Autowired
    private HrUserResumeMapper hrUserResumeMapper;

    /**
     * 查询面试候选人
     *
     * @param id 面试候选人主键
     * @return 面试候选人
     */
    @Override
    public HrUserResume selectHrUserResumeById(String id)
    {
        return hrUserResumeMapper.selectHrUserResumeById(id);
    }

    /**
     * 查询面试候选人列表
     *
     * @param hrUserResume 面试候选人
     * @return 面试候选人
     */
    @Override
    public List<HrUserResume> selectHrUserResumeList(HrUserResume hrUserResume)
    {
        return hrUserResumeMapper.selectHrUserResumeList(hrUserResume);
    }

    /**
     * 新增面试候选人
     *
     * @param hrUserResume 面试候选人
     * @return 结果
     */
    @Override
    public int insertHrUserResume(HrUserResume hrUserResume)
    {if (hrUserResume.getId() == null || hrUserResume.getId().length() == 0)
        {
            hrUserResume.setId(String.valueOf(IdWorker.getId()));
        }

        hrUserResume.setCreateTime(DateUtils.getNowDate());
        return hrUserResumeMapper.insertHrUserResume(hrUserResume);
    }

    /**
     * 修改面试候选人
     *
     * @param hrUserResume 面试候选人
     * @return 结果
     */
    @Override
    public int updateHrUserResume(HrUserResume hrUserResume)
    {
        hrUserResume.setUpdateTime(DateUtils.getNowDate());
        return hrUserResumeMapper.updateHrUserResume(hrUserResume);
    }

    /**
     * 批量删除面试候选人
     *
     * @param ids 需要删除的面试候选人主键
     * @return 结果
     */
    @Override
    public int deleteHrUserResumeByIds(String[] ids)
    {
        return hrUserResumeMapper.deleteHrUserResumeByIds(ids);
    }

    /**
     * 删除面试候选人信息
     *
     * @param id 面试候选人主键
     * @return 结果
     */
    @Override
    public int deleteHrUserResumeById(String id)
    {
        return hrUserResumeMapper.deleteHrUserResumeById(id);
    }
}
