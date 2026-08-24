package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaPomsWorkmarksWorktimesMapper;
import com.ruoyi.qixing.domain.OaPomsWorkmarksWorktimes;
import com.ruoyi.qixing.service.IOaPomsWorkmarksWorktimesService;

/**
 * 考勤记录Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaPomsWorkmarksWorktimesServiceImpl implements IOaPomsWorkmarksWorktimesService
{
    @Autowired
    private OaPomsWorkmarksWorktimesMapper oaPomsWorkmarksWorktimesMapper;

    /**
     * 查询考勤记录
     *
     * @param id 考勤记录主键
     * @return 考勤记录
     */
    @Override
    public OaPomsWorkmarksWorktimes selectOaPomsWorkmarksWorktimesById(String id)
    {
        return oaPomsWorkmarksWorktimesMapper.selectOaPomsWorkmarksWorktimesById(id);
    }

    /**
     * 查询考勤记录列表
     *
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 考勤记录
     */
    @Override
    public List<OaPomsWorkmarksWorktimes> selectOaPomsWorkmarksWorktimesList(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        return oaPomsWorkmarksWorktimesMapper.selectOaPomsWorkmarksWorktimesList(oaPomsWorkmarksWorktimes);
    }

    /**
     * 新增考勤记录
     *
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 结果
     */
    @Override
    public int insertOaPomsWorkmarksWorktimes(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {if (oaPomsWorkmarksWorktimes.getId() == null || oaPomsWorkmarksWorktimes.getId().length() == 0)
        {
            oaPomsWorkmarksWorktimes.setId(String.valueOf(IdWorker.getId()));
        }

        oaPomsWorkmarksWorktimes.setCreateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksWorktimesMapper.insertOaPomsWorkmarksWorktimes(oaPomsWorkmarksWorktimes);
    }

    /**
     * 修改考勤记录
     *
     * @param oaPomsWorkmarksWorktimes 考勤记录
     * @return 结果
     */
    @Override
    public int updateOaPomsWorkmarksWorktimes(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        oaPomsWorkmarksWorktimes.setUpdateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksWorktimesMapper.updateOaPomsWorkmarksWorktimes(oaPomsWorkmarksWorktimes);
    }

    /**
     * 批量删除考勤记录
     *
     * @param ids 需要删除的考勤记录主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksWorktimesByIds(String[] ids)
    {
        return oaPomsWorkmarksWorktimesMapper.deleteOaPomsWorkmarksWorktimesByIds(ids);
    }

    /**
     * 删除考勤记录信息
     *
     * @param id 考勤记录主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksWorktimesById(String id)
    {
        return oaPomsWorkmarksWorktimesMapper.deleteOaPomsWorkmarksWorktimesById(id);
    }
}
