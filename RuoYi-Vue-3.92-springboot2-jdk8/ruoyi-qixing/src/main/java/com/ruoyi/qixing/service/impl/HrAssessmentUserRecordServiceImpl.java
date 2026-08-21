package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrAssessmentUserRecordMapper;
import com.ruoyi.qixing.domain.HrAssessmentUserRecord;
import com.ruoyi.qixing.service.IHrAssessmentUserRecordService;

/**
 * 考核记录Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrAssessmentUserRecordServiceImpl implements IHrAssessmentUserRecordService
{
    @Autowired
    private HrAssessmentUserRecordMapper hrAssessmentUserRecordMapper;

    /**
     * 查询考核记录
     *
     * @param id 考核记录主键
     * @return 考核记录
     */
    @Override
    public HrAssessmentUserRecord selectHrAssessmentUserRecordById(String id)
    {
        return hrAssessmentUserRecordMapper.selectHrAssessmentUserRecordById(id);
    }

    /**
     * 查询考核记录列表
     *
     * @param hrAssessmentUserRecord 考核记录
     * @return 考核记录
     */
    @Override
    public List<HrAssessmentUserRecord> selectHrAssessmentUserRecordList(HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        return hrAssessmentUserRecordMapper.selectHrAssessmentUserRecordList(hrAssessmentUserRecord);
    }

    /**
     * 新增考核记录
     *
     * @param hrAssessmentUserRecord 考核记录
     * @return 结果
     */
    @Override
    public int insertHrAssessmentUserRecord(HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        hrAssessmentUserRecord.setCreateTime(DateUtils.getNowDate());
        return hrAssessmentUserRecordMapper.insertHrAssessmentUserRecord(hrAssessmentUserRecord);
    }

    /**
     * 修改考核记录
     *
     * @param hrAssessmentUserRecord 考核记录
     * @return 结果
     */
    @Override
    public int updateHrAssessmentUserRecord(HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        hrAssessmentUserRecord.setUpdateTime(DateUtils.getNowDate());
        return hrAssessmentUserRecordMapper.updateHrAssessmentUserRecord(hrAssessmentUserRecord);
    }

    /**
     * 批量删除考核记录
     *
     * @param ids 需要删除的考核记录主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentUserRecordByIds(String[] ids)
    {
        return hrAssessmentUserRecordMapper.deleteHrAssessmentUserRecordByIds(ids);
    }

    /**
     * 删除考核记录信息
     *
     * @param id 考核记录主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentUserRecordById(String id)
    {
        return hrAssessmentUserRecordMapper.deleteHrAssessmentUserRecordById(id);
    }
}
