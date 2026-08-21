package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaNotesInfoMapper;
import com.ruoyi.qixing.domain.OaNotesInfo;
import com.ruoyi.qixing.service.IOaNotesInfoService;

/**
 * 公告通知Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaNotesInfoServiceImpl implements IOaNotesInfoService
{
    @Autowired
    private OaNotesInfoMapper oaNotesInfoMapper;

    /**
     * 查询公告通知
     *
     * @param id 公告通知主键
     * @return 公告通知
     */
    @Override
    public OaNotesInfo selectOaNotesInfoById(String id)
    {
        return oaNotesInfoMapper.selectOaNotesInfoById(id);
    }

    /**
     * 查询公告通知列表
     *
     * @param oaNotesInfo 公告通知
     * @return 公告通知
     */
    @Override
    public List<OaNotesInfo> selectOaNotesInfoList(OaNotesInfo oaNotesInfo)
    {
        return oaNotesInfoMapper.selectOaNotesInfoList(oaNotesInfo);
    }

    /**
     * 新增公告通知
     *
     * @param oaNotesInfo 公告通知
     * @return 结果
     */
    @Override
    public int insertOaNotesInfo(OaNotesInfo oaNotesInfo)
    {
        oaNotesInfo.setCreateTime(DateUtils.getNowDate());
        return oaNotesInfoMapper.insertOaNotesInfo(oaNotesInfo);
    }

    /**
     * 修改公告通知
     *
     * @param oaNotesInfo 公告通知
     * @return 结果
     */
    @Override
    public int updateOaNotesInfo(OaNotesInfo oaNotesInfo)
    {
        oaNotesInfo.setUpdateTime(DateUtils.getNowDate());
        return oaNotesInfoMapper.updateOaNotesInfo(oaNotesInfo);
    }

    /**
     * 批量删除公告通知
     *
     * @param ids 需要删除的公告通知主键
     * @return 结果
     */
    @Override
    public int deleteOaNotesInfoByIds(String[] ids)
    {
        return oaNotesInfoMapper.deleteOaNotesInfoByIds(ids);
    }

    /**
     * 删除公告通知信息
     *
     * @param id 公告通知主键
     * @return 结果
     */
    @Override
    public int deleteOaNotesInfoById(String id)
    {
        return oaNotesInfoMapper.deleteOaNotesInfoById(id);
    }
}
