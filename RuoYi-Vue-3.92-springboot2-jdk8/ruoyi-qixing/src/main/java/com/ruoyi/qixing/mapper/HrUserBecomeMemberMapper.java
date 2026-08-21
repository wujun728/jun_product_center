package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserBecomeMember;

/**
 * 转正Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface HrUserBecomeMemberMapper 
{
    /**
     * 查询转正
     * 
     * @param id 转正主键
     * @return 转正
     */
    public HrUserBecomeMember selectHrUserBecomeMemberById(String id);

    /**
     * 查询转正列表
     * 
     * @param hrUserBecomeMember 转正
     * @return 转正集合
     */
    public List<HrUserBecomeMember> selectHrUserBecomeMemberList(HrUserBecomeMember hrUserBecomeMember);

    /**
     * 新增转正
     * 
     * @param hrUserBecomeMember 转正
     * @return 结果
     */
    public int insertHrUserBecomeMember(HrUserBecomeMember hrUserBecomeMember);

    /**
     * 修改转正
     * 
     * @param hrUserBecomeMember 转正
     * @return 结果
     */
    public int updateHrUserBecomeMember(HrUserBecomeMember hrUserBecomeMember);

    /**
     * 删除转正
     * 
     * @param id 转正主键
     * @return 结果
     */
    public int deleteHrUserBecomeMemberById(String id);

    /**
     * 批量删除转正
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrUserBecomeMemberByIds(String[] ids);
}
