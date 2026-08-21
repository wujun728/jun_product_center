package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksWorktimes;

/**
 * 考勤记录Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaPomsWorkmarksWorktimesMapper 
{
    /**
     * 查询考勤记录
     * 
     * @param id 考勤记录主键
     * @return 考勤记录
     */
    public OaPomsWorkmarksWorktimes selectOaPomsWorkmarksWorktimesById(String id);

    /**
     * 查询考勤记录列表
     * 
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 考勤记录集合
     */
    public List<OaPomsWorkmarksWorktimes> selectOaPomsWorkmarksWorktimesList(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes);

    /**
     * 新增考勤记录
     * 
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 结果
     */
    public int insertOaPomsWorkmarksWorktimes(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes);

    /**
     * 修改考勤记录
     * 
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 结果
     */
    public int updateOaPomsWorkmarksWorktimes(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes);

    /**
     * 删除考勤记录
     * 
     * @param id 考勤记录主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksWorktimesById(String id);

    /**
     * 批量删除考勤记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksWorktimesByIds(String[] ids);
}
