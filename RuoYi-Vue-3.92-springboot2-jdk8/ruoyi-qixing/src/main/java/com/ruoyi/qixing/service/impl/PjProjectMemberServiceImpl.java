package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectMemberMapper;
import com.ruoyi.qixing.domain.PjProjectMember;
import com.ruoyi.qixing.service.IPjProjectMemberService;

/**
 * 项目成员与结算Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectMemberServiceImpl implements IPjProjectMemberService
{
    @Autowired
    private PjProjectMemberMapper pjProjectMemberMapper;

    /**
     * 查询项目成员与结算
     *
     * @param id 项目成员与结算主键
     * @return 项目成员与结算
     */
    @Override
    public PjProjectMember selectPjProjectMemberById(String id)
    {
        return pjProjectMemberMapper.selectPjProjectMemberById(id);
    }

    /**
     * 查询项目成员与结算列表
     *
     * @param pjProjectMember 项目成员与结算
     * @return 项目成员与结算
     */
    @Override
    public List<PjProjectMember> selectPjProjectMemberList(PjProjectMember pjProjectMember)
    {
        return pjProjectMemberMapper.selectPjProjectMemberList(pjProjectMember);
    }

    /**
     * 新增项目成员与结算
     *
     * @param pjProjectMember 项目成员与结算
     * @return 结果
     */
    @Override
    public int insertPjProjectMember(PjProjectMember pjProjectMember)
    {
        pjProjectMember.setCreateTime(DateUtils.getNowDate());
        return pjProjectMemberMapper.insertPjProjectMember(pjProjectMember);
    }

    /**
     * 修改项目成员与结算
     *
     * @param pjProjectMember 项目成员与结算
     * @return 结果
     */
    @Override
    public int updatePjProjectMember(PjProjectMember pjProjectMember)
    {
        pjProjectMember.setUpdateTime(DateUtils.getNowDate());
        return pjProjectMemberMapper.updatePjProjectMember(pjProjectMember);
    }

    /**
     * 批量删除项目成员与结算
     *
     * @param ids 需要删除的项目成员与结算主键
     * @return 结果
     */
    @Override
    public int deletePjProjectMemberByIds(String[] ids)
    {
        return pjProjectMemberMapper.deletePjProjectMemberByIds(ids);
    }

    /**
     * 删除项目成员与结算信息
     *
     * @param id 项目成员与结算主键
     * @return 结果
     */
    @Override
    public int deletePjProjectMemberById(String id)
    {
        return pjProjectMemberMapper.deletePjProjectMemberById(id);
    }
}
