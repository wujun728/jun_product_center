package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrAssessmentTemplate;

/**
 * 考核模板Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrAssessmentTemplateService 
{
    /**
     * 查询考核模板
     * 
     * @param id 考核模板主键
     * @return 考核模板
     */
    public HrAssessmentTemplate selectHrAssessmentTemplateById(String id);

    /**
     * 查询考核模板列表
     * 
     * @param hrAssessmentTemplate 考核模板
     * @return 考核模板集合
     */
    public List<HrAssessmentTemplate> selectHrAssessmentTemplateList(HrAssessmentTemplate hrAssessmentTemplate);

    /**
     * 新增考核模板
     * 
     * @param hrAssessmentTemplate 考核模板
     * @return 结果
     */
    public int insertHrAssessmentTemplate(HrAssessmentTemplate hrAssessmentTemplate);

    /**
     * 修改考核模板
     * 
     * @param hrAssessmentTemplate 考核模板
     * @return 结果
     */
    public int updateHrAssessmentTemplate(HrAssessmentTemplate hrAssessmentTemplate);

    /**
     * 批量删除考核模板
     * 
     * @param ids 需要删除的考核模板主键集合
     * @return 结果
     */
    public int deleteHrAssessmentTemplateByIds(String[] ids);

    /**
     * 删除考核模板信息
     * 
     * @param id 考核模板主键
     * @return 结果
     */
    public int deleteHrAssessmentTemplateById(String id);
}
