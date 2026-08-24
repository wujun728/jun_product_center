package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrAssessmentUserRecordDetailMapper;
import com.ruoyi.qixing.domain.HrAssessmentUserRecordDetail;
import com.ruoyi.qixing.service.IHrAssessmentUserRecordDetailService;

/**
 * 考核记录明细Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrAssessmentUserRecordDetailServiceImpl implements IHrAssessmentUserRecordDetailService
{
    @Autowired
    private HrAssessmentUserRecordDetailMapper hrAssessmentUserRecordDetailMapper;

    /**
     * 查询考核记录明细
     *
     * @param id 考核记录明细主键
     * @return 考核记录明细
     */
    @Override
    public HrAssessmentUserRecordDetail selectHrAssessmentUserRecordDetailById(String id)
    {
        return hrAssessmentUserRecordDetailMapper.selectHrAssessmentUserRecordDetailById(id);
    }

    /**
     * 查询考核记录明细列表
     *
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 考核记录明细
     */
    @Override
    public List<HrAssessmentUserRecordDetail> selectHrAssessmentUserRecordDetailList(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        return hrAssessmentUserRecordDetailMapper.selectHrAssessmentUserRecordDetailList(hrAssessmentUserRecordDetail);
    }

    /**
     * 新增考核记录明细
     *
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 结果
     */
    @Override
    public int insertHrAssessmentUserRecordDetail(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {if (hrAssessmentUserRecordDetail.getId() == null || hrAssessmentUserRecordDetail.getId().length() == 0)
        {
            hrAssessmentUserRecordDetail.setId(String.valueOf(IdWorker.getId()));
        }

        hrAssessmentUserRecordDetail.setCreateTime(DateUtils.getNowDate());
        return hrAssessmentUserRecordDetailMapper.insertHrAssessmentUserRecordDetail(hrAssessmentUserRecordDetail);
    }

    /**
     * 修改考核记录明细
     *
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 结果
     */
    @Override
    public int updateHrAssessmentUserRecordDetail(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        hrAssessmentUserRecordDetail.setUpdateTime(DateUtils.getNowDate());
        return hrAssessmentUserRecordDetailMapper.updateHrAssessmentUserRecordDetail(hrAssessmentUserRecordDetail);
    }

    /**
     * 批量删除考核记录明细
     *
     * @param ids 需要删除的考核记录明细主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentUserRecordDetailByIds(String[] ids)
    {
        return hrAssessmentUserRecordDetailMapper.deleteHrAssessmentUserRecordDetailByIds(ids);
    }

    /**
     * 删除考核记录明细信息
     *
     * @param id 考核记录明细主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentUserRecordDetailById(String id)
    {
        return hrAssessmentUserRecordDetailMapper.deleteHrAssessmentUserRecordDetailById(id);
    }
}
