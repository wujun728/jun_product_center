package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksWorktimes;

/**
 * 考勤记录Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IOaPomsWorkmarksWorktimesService 
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
     * 批量删除考勤记录
     * 
     * @param ids 需要删除的考勤记录主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksWorktimesByIds(String[] ids);

    /**
     * 删除考勤记录信息
     * 
     * @param id 考勤记录主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksWorktimesById(String id);
}
