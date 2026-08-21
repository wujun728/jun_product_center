package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrAssessmentTemplateDetailMapper;
import com.ruoyi.qixing.domain.HrAssessmentTemplateDetail;
import com.ruoyi.qixing.service.IHrAssessmentTemplateDetailService;

/**
 * 考核模板明细Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrAssessmentTemplateDetailServiceImpl implements IHrAssessmentTemplateDetailService
{
    @Autowired
    private HrAssessmentTemplateDetailMapper hrAssessmentTemplateDetailMapper;

    /**
     * 查询考核模板明细
     *
     * @param id 考核模板明细主键
     * @return 考核模板明细
     */
    @Override
    public HrAssessmentTemplateDetail selectHrAssessmentTemplateDetailById(String id)
    {
        return hrAssessmentTemplateDetailMapper.selectHrAssessmentTemplateDetailById(id);
    }

    /**
     * 查询考核模板明细列表
     *
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 考核模板明细
     */
    @Override
    public List<HrAssessmentTemplateDetail> selectHrAssessmentTemplateDetailList(HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        return hrAssessmentTemplateDetailMapper.selectHrAssessmentTemplateDetailList(hrAssessmentTemplateDetail);
    }

    /**
     * 新增考核模板明细
     *
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 结果
     */
    @Override
    public int insertHrAssessmentTemplateDetail(HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        hrAssessmentTemplateDetail.setCreateTime(DateUtils.getNowDate());
        return hrAssessmentTemplateDetailMapper.insertHrAssessmentTemplateDetail(hrAssessmentTemplateDetail);
    }

    /**
     * 修改考核模板明细
     *
     * @param hrAssessmentTemplateDetail 考核模板明细
     * @return 结果
     */
    @Override
    public int updateHrAssessmentTemplateDetail(HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        hrAssessmentTemplateDetail.setUpdateTime(DateUtils.getNowDate());
        return hrAssessmentTemplateDetailMapper.updateHrAssessmentTemplateDetail(hrAssessmentTemplateDetail);
    }

    /**
     * 批量删除考核模板明细
     *
     * @param ids 需要删除的考核模板明细主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentTemplateDetailByIds(String[] ids)
    {
        return hrAssessmentTemplateDetailMapper.deleteHrAssessmentTemplateDetailByIds(ids);
    }

    /**
     * 删除考核模板明细信息
     *
     * @param id 考核模板明细主键
     * @return 结果
     */
    @Override
    public int deleteHrAssessmentTemplateDetailById(String id)
    {
        return hrAssessmentTemplateDetailMapper.deleteHrAssessmentTemplateDetailById(id);
    }
}
