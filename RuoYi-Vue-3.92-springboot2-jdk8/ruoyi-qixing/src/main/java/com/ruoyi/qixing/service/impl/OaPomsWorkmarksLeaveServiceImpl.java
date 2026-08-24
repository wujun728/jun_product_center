package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaPomsWorkmarksLeaveMapper;
import com.ruoyi.qixing.domain.OaPomsWorkmarksLeave;
import com.ruoyi.qixing.service.IOaPomsWorkmarksLeaveService;

/**
 * 员工请假Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaPomsWorkmarksLeaveServiceImpl implements IOaPomsWorkmarksLeaveService
{
    @Autowired
    private OaPomsWorkmarksLeaveMapper oaPomsWorkmarksLeaveMapper;

    /**
     * 查询员工请假
     *
     * @param id 员工请假主键
     * @return 员工请假
     */
    @Override
    public OaPomsWorkmarksLeave selectOaPomsWorkmarksLeaveById(String id)
    {
        return oaPomsWorkmarksLeaveMapper.selectOaPomsWorkmarksLeaveById(id);
    }

    /**
     * 查询员工请假列表
     *
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 员工请假
     */
    @Override
    public List<OaPomsWorkmarksLeave> selectOaPomsWorkmarksLeaveList(OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        return oaPomsWorkmarksLeaveMapper.selectOaPomsWorkmarksLeaveList(oaPomsWorkmarksLeave);
    }

    /**
     * 新增员工请假
     *
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 结果
     */
    @Override
    public int insertOaPomsWorkmarksLeave(OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {if (oaPomsWorkmarksLeave.getId() == null || oaPomsWorkmarksLeave.getId().length() == 0)
        {
            oaPomsWorkmarksLeave.setId(String.valueOf(IdWorker.getId()));
        }

        oaPomsWorkmarksLeave.setCreateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksLeaveMapper.insertOaPomsWorkmarksLeave(oaPomsWorkmarksLeave);
    }

    /**
     * 修改员工请假
     *
     * @param oaPomsWorkmarksLeave 员工请假
     * @return 结果
     */
    @Override
    public int updateOaPomsWorkmarksLeave(OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        oaPomsWorkmarksLeave.setUpdateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksLeaveMapper.updateOaPomsWorkmarksLeave(oaPomsWorkmarksLeave);
    }

    /**
     * 批量删除员工请假
     *
     * @param ids 需要删除的员工请假主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksLeaveByIds(String[] ids)
    {
        return oaPomsWorkmarksLeaveMapper.deleteOaPomsWorkmarksLeaveByIds(ids);
    }

    /**
     * 删除员工请假信息
     *
     * @param id 员工请假主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksLeaveById(String id)
    {
        return oaPomsWorkmarksLeaveMapper.deleteOaPomsWorkmarksLeaveById(id);
    }
}
