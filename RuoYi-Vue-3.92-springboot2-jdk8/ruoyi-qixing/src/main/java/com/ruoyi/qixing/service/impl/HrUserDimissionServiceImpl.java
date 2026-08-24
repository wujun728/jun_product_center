package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserDimissionMapper;
import com.ruoyi.qixing.domain.HrUserDimission;
import com.ruoyi.qixing.service.IHrUserDimissionService;

/**
 * 离职Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserDimissionServiceImpl implements IHrUserDimissionService
{
    @Autowired
    private HrUserDimissionMapper hrUserDimissionMapper;

    /**
     * 查询离职
     *
     * @param id 离职主键
     * @return 离职
     */
    @Override
    public HrUserDimission selectHrUserDimissionById(String id)
    {
        return hrUserDimissionMapper.selectHrUserDimissionById(id);
    }

    /**
     * 查询离职列表
     *
     * @param hrUserDimission 离职
     * @return 离职
     */
    @Override
    public List<HrUserDimission> selectHrUserDimissionList(HrUserDimission hrUserDimission)
    {
        return hrUserDimissionMapper.selectHrUserDimissionList(hrUserDimission);
    }

    /**
     * 新增离职
     *
     * @param hrUserDimission 离职
     * @return 结果
     */
    @Override
    public int insertHrUserDimission(HrUserDimission hrUserDimission)
    {if (hrUserDimission.getId() == null || hrUserDimission.getId().length() == 0)
        {
            hrUserDimission.setId(String.valueOf(IdWorker.getId()));
        }

        hrUserDimission.setCreateTime(DateUtils.getNowDate());
        return hrUserDimissionMapper.insertHrUserDimission(hrUserDimission);
    }

    /**
     * 修改离职
     *
     * @param hrUserDimission 离职
     * @return 结果
     */
    @Override
    public int updateHrUserDimission(HrUserDimission hrUserDimission)
    {
        hrUserDimission.setUpdateTime(DateUtils.getNowDate());
        return hrUserDimissionMapper.updateHrUserDimission(hrUserDimission);
    }

    /**
     * 批量删除离职
     *
     * @param ids 需要删除的离职主键
     * @return 结果
     */
    @Override
    public int deleteHrUserDimissionByIds(String[] ids)
    {
        return hrUserDimissionMapper.deleteHrUserDimissionByIds(ids);
    }

    /**
     * 删除离职信息
     *
     * @param id 离职主键
     * @return 结果
     */
    @Override
    public int deleteHrUserDimissionById(String id)
    {
        return hrUserDimissionMapper.deleteHrUserDimissionById(id);
    }
}
