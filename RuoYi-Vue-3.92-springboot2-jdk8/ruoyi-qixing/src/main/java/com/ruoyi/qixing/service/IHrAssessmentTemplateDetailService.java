package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrAssessmentTemplateDetail;

/**
 * 考核模板明细Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrAssessmentTemplateDetailService 
{
    /**
     * 查询考核模板明细
     * 
     * @param id 考核模板明细主键
     * @return 考核模板明细
     */
    public HrAssessmentTemplateDetail selectHrAssessmentTemplateDetailById(String id);

    /**
     * 查询考核模板明细列表
     * 
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 考核模板明细集合
     */
    public List<HrAssessmentTemplateDetail> selectHrAssessmentTemplateDetailList(HrAssessmentTemplateDetail hrAssessmentTemplateDetail);

    /**
     * 新增考核模板明细
     * 
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 结果
     */
    public int insertHrAssessmentTemplateDetail(HrAssessmentTemplateDetail hrAssessmentTemplateDetail);

    /**
     * 修改考核模板明细
     * 
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 结果
     */
    public int updateHrAssessmentTemplateDetail(HrAssessmentTemplateDetail hrAssessmentTemplateDetail);

    /**
     * 批量删除考核模板明细
     * 
     * @param ids 需要删除的考核模板明细主键集合
     * @return 结果
     */
    public int deleteHrAssessmentTemplateDetailByIds(String[] ids);

    /**
     * 删除考核模板明细信息
     * 
     * @param id 考核模板明细主键
     * @return 结果
     */
    public int deleteHrAssessmentTemplateDetailById(String id);
}
