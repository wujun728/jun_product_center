package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaLearnInfoMapper;
import com.ruoyi.qixing.domain.OaLearnInfo;
import com.ruoyi.qixing.service.IOaLearnInfoService;

/**
 * 培训学习Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaLearnInfoServiceImpl implements IOaLearnInfoService
{
    @Autowired
    private OaLearnInfoMapper oaLearnInfoMapper;

    /**
     * 查询培训学习
     *
     * @param id 培训学习主键
     * @return 培训学习
     */
    @Override
    public OaLearnInfo selectOaLearnInfoById(String id)
    {
        return oaLearnInfoMapper.selectOaLearnInfoById(id);
    }

    /**
     * 查询培训学习列表
     *
     * @param oaLearnInfo 培训学习
     * @return 培训学习
     */
    @Override
    public List<OaLearnInfo> selectOaLearnInfoList(OaLearnInfo oaLearnInfo)
    {
        return oaLearnInfoMapper.selectOaLearnInfoList(oaLearnInfo);
    }

    /**
     * 新增培训学习
     *
     * @param oaLearnInfo 培训学习
     * @return 结果
     */
    @Override
    public int insertOaLearnInfo(OaLearnInfo oaLearnInfo)
    {
        oaLearnInfo.setCreateTime(DateUtils.getNowDate());
        return oaLearnInfoMapper.insertOaLearnInfo(oaLearnInfo);
    }

    /**
     * 修改培训学习
     *
     * @param oaLearnInfo 培训学习
     * @return 结果
     */
    @Override
    public int updateOaLearnInfo(OaLearnInfo oaLearnInfo)
    {
        oaLearnInfo.setUpdateTime(DateUtils.getNowDate());
        return oaLearnInfoMapper.updateOaLearnInfo(oaLearnInfo);
    }

    /**
     * 批量删除培训学习
     *
     * @param ids 需要删除的培训学习主键
     * @return 结果
     */
    @Override
    public int deleteOaLearnInfoByIds(String[] ids)
    {
        return oaLearnInfoMapper.deleteOaLearnInfoByIds(ids);
    }

    /**
     * 删除培训学习信息
     *
     * @param id 培训学习主键
     * @return 结果
     */
    @Override
    public int deleteOaLearnInfoById(String id)
    {
        return oaLearnInfoMapper.deleteOaLearnInfoById(id);
    }
}
