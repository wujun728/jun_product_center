package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.HrAssessmentUserRecordDetail;

/**
 * 考核记录明细Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface HrAssessmentUserRecordDetailMapper 
{
    /**
     * 查询考核记录明细
     * 
     * @param id 考核记录明细主键
     * @return 考核记录明细
     */
    public HrAssessmentUserRecordDetail selectHrAssessmentUserRecordDetailById(String id);

    /**
     * 查询考核记录明细列表
     * 
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 考核记录明细集合
     */
    public List<HrAssessmentUserRecordDetail> selectHrAssessmentUserRecordDetailList(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail);

    /**
     * 新增考核记录明细
     * 
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 结果
     */
    public int insertHrAssessmentUserRecordDetail(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail);

    /**
     * 修改考核记录明细
     * 
     * @param hrAssessmentUserRecordDetail 考核记录明细
     * @return 结果
     */
    public int updateHrAssessmentUserRecordDetail(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail);

    /**
     * 删除考核记录明细
     * 
     * @param id 考核记录明细主键
     * @return 结果
     */
    public int deleteHrAssessmentUserRecordDetailById(String id);

    /**
     * 批量删除考核记录明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrAssessmentUserRecordDetailByIds(String[] ids);
}
