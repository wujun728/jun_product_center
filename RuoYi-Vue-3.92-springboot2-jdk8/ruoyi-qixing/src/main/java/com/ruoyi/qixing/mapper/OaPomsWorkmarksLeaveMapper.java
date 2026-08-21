package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksLeave;

/**
 * 员工请假Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaPomsWorkmarksLeaveMapper 
{
    /**
     * 查询员工请假
     * 
     * @param id 员工请假主键
     * @return 员工请假
     */
    public OaPomsWorkmarksLeave selectOaPomsWorkmarksLeaveById(String id);

    /**
     * 查询员工请假列表
     * 
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 员工请假集合
     */
    public List<OaPomsWorkmarksLeave> selectOaPomsWorkmarksLeaveList(OaPomsWorkmarksLeave oaPomsWorkmarksLeave);

    /**
     * 新增员工请假
     * 
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 结果
     */
    public int insertOaPomsWorkmarksLeave(OaPomsWorkmarksLeave oaPomsWorkmarksLeave);

    /**
     * 修改员工请假
     * 
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 结果
     */
    public int updateOaPomsWorkmarksLeave(OaPomsWorkmarksLeave oaPomsWorkmarksLeave);

    /**
     * 删除员工请假
     * 
     * @param id 员工请假主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksLeaveById(String id);

    /**
     * 批量删除员工请假
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksLeaveByIds(String[] ids);
}
