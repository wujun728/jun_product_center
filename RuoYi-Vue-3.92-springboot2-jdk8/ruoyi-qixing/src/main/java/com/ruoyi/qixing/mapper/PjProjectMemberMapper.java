package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectMember;

/**
 * 项目成员与结算Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjProjectMemberMapper 
{
    /**
     * 查询项目成员与结算
     * 
     * @param id 项目成员与结算主键
     * @return 项目成员与结算
     */
    public PjProjectMember selectPjProjectMemberById(String id);

    /**
     * 查询项目成员与结算列表
     * 
     * @param pjProjectMember 项目成员与结算
     * @return 项目成员与结算集合
     */
    public List<PjProjectMember> selectPjProjectMemberList(PjProjectMember pjProjectMember);

    /**
     * 新增项目成员与结算
     * 
     * @param pjProjectMember 项目成员与结算
     * @return 结果
     */
    public int insertPjProjectMember(PjProjectMember pjProjectMember);

    /**
     * 修改项目成员与结算
     * 
     * @param pjProjectMember 项目成员与结算
     * @return 结果
     */
    public int updatePjProjectMember(PjProjectMember pjProjectMember);

    /**
     * 删除项目成员与结算
     * 
     * @param id 项目成员与结算主键
     * @return 结果
     */
    public int deletePjProjectMemberById(String id);

    /**
     * 批量删除项目成员与结算
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjProjectMemberByIds(String[] ids);
}
