package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserBecomeMemberMapper;
import com.ruoyi.qixing.domain.HrUserBecomeMember;
import com.ruoyi.qixing.service.IHrUserBecomeMemberService;

/**
 * 转正Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserBecomeMemberServiceImpl implements IHrUserBecomeMemberService
{
    @Autowired
    private HrUserBecomeMemberMapper hrUserBecomeMemberMapper;

    /**
     * 查询转正
     *
     * @param id 转正主键
     * @return 转正
     */
    @Override
    public HrUserBecomeMember selectHrUserBecomeMemberById(String id)
    {
        return hrUserBecomeMemberMapper.selectHrUserBecomeMemberById(id);
    }

    /**
     * 查询转正列表
     *
     * @param hrUserBecomeMember 转正
     * @return 转正
     */
    @Override
    public List<HrUserBecomeMember> selectHrUserBecomeMemberList(HrUserBecomeMember hrUserBecomeMember)
    {
        return hrUserBecomeMemberMapper.selectHrUserBecomeMemberList(hrUserBecomeMember);
    }

    /**
     * 新增转正
     *
     * @param hrUserBecomeMember 转正
     * @return 结果
     */
    @Override
    public int insertHrUserBecomeMember(HrUserBecomeMember hrUserBecomeMember)
    {if (hrUserBecomeMember.getId() == null || hrUserBecomeMember.getId().length() == 0)
        {
            hrUserBecomeMember.setId(String.valueOf(IdWorker.getId()));
        }

        hrUserBecomeMember.setCreateTime(DateUtils.getNowDate());
        return hrUserBecomeMemberMapper.insertHrUserBecomeMember(hrUserBecomeMember);
    }

    /**
     * 修改转正
     *
     * @param hrUserBecomeMember 转正
     * @return 结果
     */
    @Override
    public int updateHrUserBecomeMember(HrUserBecomeMember hrUserBecomeMember)
    {
        hrUserBecomeMember.setUpdateTime(DateUtils.getNowDate());
        return hrUserBecomeMemberMapper.updateHrUserBecomeMember(hrUserBecomeMember);
    }

    /**
     * 批量删除转正
     *
     * @param ids 需要删除的转正主键
     * @return 结果
     */
    @Override
    public int deleteHrUserBecomeMemberByIds(String[] ids)
    {
        return hrUserBecomeMemberMapper.deleteHrUserBecomeMemberByIds(ids);
    }

    /**
     * 删除转正信息
     *
     * @param id 转正主键
     * @return 结果
     */
    @Override
    public int deleteHrUserBecomeMemberById(String id)
    {
        return hrUserBecomeMemberMapper.deleteHrUserBecomeMemberById(id);
    }
}
