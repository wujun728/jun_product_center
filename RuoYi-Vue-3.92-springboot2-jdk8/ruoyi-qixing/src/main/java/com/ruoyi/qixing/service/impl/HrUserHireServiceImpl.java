package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserHireMapper;
import com.ruoyi.qixing.domain.HrUserHire;
import com.ruoyi.qixing.service.IHrUserHireService;

/**
 * 录用审批Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserHireServiceImpl implements IHrUserHireService
{
    @Autowired
    private HrUserHireMapper hrUserHireMapper;

    /**
     * 查询录用审批
     *
     * @param id 录用审批主键
     * @return 录用审批
     */
    @Override
    public HrUserHire selectHrUserHireById(String id)
    {
        return hrUserHireMapper.selectHrUserHireById(id);
    }

    /**
     * 查询录用审批列表
     *
     * @param hrUserHire 录用审批
     * @return 录用审批
     */
    @Override
    public List<HrUserHire> selectHrUserHireList(HrUserHire hrUserHire)
    {
        return hrUserHireMapper.selectHrUserHireList(hrUserHire);
    }

    /**
     * 新增录用审批
     *
     * @param hrUserHire 录用审批
     * @return 结果
     */
    @Override
    public int insertHrUserHire(HrUserHire hrUserHire)
    {
        hrUserHire.setCreateTime(DateUtils.getNowDate());
        return hrUserHireMapper.insertHrUserHire(hrUserHire);
    }

    /**
     * 修改录用审批
     *
     * @param hrUserHire 录用审批
     * @return 结果
     */
    @Override
    public int updateHrUserHire(HrUserHire hrUserHire)
    {
        hrUserHire.setUpdateTime(DateUtils.getNowDate());
        return hrUserHireMapper.updateHrUserHire(hrUserHire);
    }

    /**
     * 批量删除录用审批
     *
     * @param ids 需要删除的录用审批主键
     * @return 结果
     */
    @Override
    public int deleteHrUserHireByIds(String[] ids)
    {
        return hrUserHireMapper.deleteHrUserHireByIds(ids);
    }

    /**
     * 删除录用审批信息
     *
     * @param id 录用审批主键
     * @return 结果
     */
    @Override
    public int deleteHrUserHireById(String id)
    {
        return hrUserHireMapper.deleteHrUserHireById(id);
    }
}
