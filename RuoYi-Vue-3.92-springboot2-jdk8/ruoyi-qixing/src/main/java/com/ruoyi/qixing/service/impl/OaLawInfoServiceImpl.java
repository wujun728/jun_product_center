package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaLawInfoMapper;
import com.ruoyi.qixing.domain.OaLawInfo;
import com.ruoyi.qixing.service.IOaLawInfoService;

/**
 * 政策法规Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaLawInfoServiceImpl implements IOaLawInfoService
{
    @Autowired
    private OaLawInfoMapper oaLawInfoMapper;

    /**
     * 查询政策法规
     *
     * @param id 政策法规主键
     * @return 政策法规
     */
    @Override
    public OaLawInfo selectOaLawInfoById(String id)
    {
        return oaLawInfoMapper.selectOaLawInfoById(id);
    }

    /**
     * 查询政策法规列表
     *
     * @param oaLawInfo 政策法规
     * @return 政策法规
     */
    @Override
    public List<OaLawInfo> selectOaLawInfoList(OaLawInfo oaLawInfo)
    {
        return oaLawInfoMapper.selectOaLawInfoList(oaLawInfo);
    }

    /**
     * 新增政策法规
     *
     * @param oaLawInfo 政策法规
     * @return 结果
     */
    @Override
    public int insertOaLawInfo(OaLawInfo oaLawInfo)
    {
        oaLawInfo.setCreateTime(DateUtils.getNowDate());
        return oaLawInfoMapper.insertOaLawInfo(oaLawInfo);
    }

    /**
     * 修改政策法规
     *
     * @param oaLawInfo 政策法规
     * @return 结果
     */
    @Override
    public int updateOaLawInfo(OaLawInfo oaLawInfo)
    {
        oaLawInfo.setUpdateTime(DateUtils.getNowDate());
        return oaLawInfoMapper.updateOaLawInfo(oaLawInfo);
    }

    /**
     * 批量删除政策法规
     *
     * @param ids 需要删除的政策法规主键
     * @return 结果
     */
    @Override
    public int deleteOaLawInfoByIds(String[] ids)
    {
        return oaLawInfoMapper.deleteOaLawInfoByIds(ids);
    }

    /**
     * 删除政策法规信息
     *
     * @param id 政策法规主键
     * @return 结果
     */
    @Override
    public int deleteOaLawInfoById(String id)
    {
        return oaLawInfoMapper.deleteOaLawInfoById(id);
    }
}
