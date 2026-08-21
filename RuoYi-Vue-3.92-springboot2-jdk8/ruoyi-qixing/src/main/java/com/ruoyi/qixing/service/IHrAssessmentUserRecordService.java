package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrAssessmentUserRecord;

/**
 * 考核记录Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrAssessmentUserRecordService 
{
    /**
     * 查询考核记录
     * 
     * @param id 考核记录主键
     * @return 考核记录
     */
    public HrAssessmentUserRecord selectHrAssessmentUserRecordById(String id);

    /**
     * 查询考核记录列表
     * 
     * @param hrAssessmentUserRecord 考核记录
     * @return 考核记录集合
     */
    public List<HrAssessmentUserRecord> selectHrAssessmentUserRecordList(HrAssessmentUserRecord hrAssessmentUserRecord);

    /**
     * 新增考核记录
     * 
     * @param hrAssessmentUserRecord 考核记录
     * @return 结果
     */
    public int insertHrAssessmentUserRecord(HrAssessmentUserRecord hrAssessmentUserRecord);

    /**
     * 修改考核记录
     * 
     * @param hrAssessmentUserRecord 考核记录
     * @return 结果
     */
    public int updateHrAssessmentUserRecord(HrAssessmentUserRecord hrAssessmentUserRecord);

    /**
     * 批量删除考核记录
     * 
     * @param ids 需要删除的考核记录主键集合
     * @return 结果
     */
    public int deleteHrAssessmentUserRecordByIds(String[] ids);

    /**
     * 删除考核记录信息
     * 
     * @param id 考核记录主键
     * @return 结果
     */
    public int deleteHrAssessmentUserRecordById(String id);
}
