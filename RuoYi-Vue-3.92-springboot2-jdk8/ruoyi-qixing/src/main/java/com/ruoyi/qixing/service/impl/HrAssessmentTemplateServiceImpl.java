package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrAssessmentTemplateMapper;
import com.ruoyi.qixing.domain.HrAssessmentTemplate;
import com.ruoyi.qixing.service.IHrAssessmentTemplateService;

/**
 * 考核模板Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrAssessmentTemplateServiceImpl implements IHrAssessmentTemplateService
{
    @Autowired
    private HrAssessmentTemplateMapper hrAssessmentTemplateMapper;

    /**
     * 查询考核模板
     *
     * @param id 考核模板主键
     * @return 考核模板
     */
    @Override
    public HrAssessmentTemplate selectHrAssessmentTemplateById(String id)
    {
        return hrAssessmentTemplateMapper.selectHrAssessmentTemplateById(id);
    }

    /**
     * 查询考核模板列表
     *
     * @param hrAssessmentTemplate 考核模板
     * @return 考核模板
     */
    @Override
    public List<HrAssessmentTemplate> selectHrAssessmentTemplateList(HrAssessmentTemplate hrAssessmentTemplate)
    {
        return hrAssessmentTemplateMapper.selectHrAssessmentTemplateList(hrAssessmentTemplate);
    }

    /**
     * 新增考核模板
     *
     * @param hrAssessmentTemplate 考核模板
     * @return 结果
     */
    @Override
    public int insertHrAssessmentTemplate(HrAssessmentTemplate hrAssessmentTemplate)
    {if (hrAssessmentTemplate.getId() == null || hrAssessmentTemplate.getId().length() == 0)
        {
            hrAssessmentTemplate.setId(String.valueOf(IdWorker.getId()));
        }

        hrAssessmentTemplate.setCreateTime(DateUtils.getNowDate());
        return hrAssessmentTemplateMapper.insertHrAssessmentTemplate(hrAssessmentTemplate);
    }

    /**
     * 修改考核模板
     *
     * @param hrAssessmentTemplate 考核模板
     * @return 结果
     */
    @Override
    public int updateHrAssessmentTemplate(HrAssessmentTemplate hrAssessmentTemplate)
    {
        hrAssessmentTemplate.setUpdateTime(DateUtils.getNowDate());
        return hrAssessmentTemplateMapper.updateHrAssessmentTemplate(hrAssessmentTemplate);
    }

    /**
     * 批量删除考核模板
     *
     * @param ids 需要删除的考核模板主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentTemplateByIds(String[] ids)
    {
        return hrAssessmentTemplateMapper.deleteHrAssessmentTemplateByIds(ids);
    }

    /**
     * 删除考核模板信息
     *
     * @param id 考核模板主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentTemplateById(String id)
    {
        return hrAssessmentTemplateMapper.deleteHrAssessmentTemplateById(id);
    }
}
