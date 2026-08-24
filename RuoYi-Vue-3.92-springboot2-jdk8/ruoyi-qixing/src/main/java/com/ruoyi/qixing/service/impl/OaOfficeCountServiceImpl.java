package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaOfficeCountMapper;
import com.ruoyi.qixing.domain.OaOfficeCount;
import com.ruoyi.qixing.service.IOaOfficeCountService;

/**
 * 办公用品申领申购Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaOfficeCountServiceImpl implements IOaOfficeCountService
{
    @Autowired
    private OaOfficeCountMapper oaOfficeCountMapper;

    /**
     * 查询办公用品申领申购
     *
     * @param id 办公用品申领申购主键
     * @return 办公用品申领申购
     */
    @Override
    public OaOfficeCount selectOaOfficeCountById(String id)
    {
        return oaOfficeCountMapper.selectOaOfficeCountById(id);
    }

    /**
     * 查询办公用品申领申购列表
     *
     * @param oaOfficeCount 办公用品申领申购
     * @return 办公用品申领申购
     */
    @Override
    public List<OaOfficeCount> selectOaOfficeCountList(OaOfficeCount oaOfficeCount)
    {
        return oaOfficeCountMapper.selectOaOfficeCountList(oaOfficeCount);
    }

    /**
     * 新增办公用品申领申购
     *
     * @param oaOfficeCount 办公用品申领申购
     * @return 结果
     */
    @Override
    public int insertOaOfficeCount(OaOfficeCount oaOfficeCount)
    {if (oaOfficeCount.getId() == null || oaOfficeCount.getId().length() == 0)
        {
            oaOfficeCount.setId(String.valueOf(IdWorker.getId()));
        }

        oaOfficeCount.setCreateTime(DateUtils.getNowDate());
        return oaOfficeCountMapper.insertOaOfficeCount(oaOfficeCount);
    }

    /**
     * 修改办公用品申领申购
     *
     * @param oaOfficeCount 办公用品申领申购
     * @return 结果
     */
    @Override
    public int updateOaOfficeCount(OaOfficeCount oaOfficeCount)
    {
        oaOfficeCount.setUpdateTime(DateUtils.getNowDate());
        return oaOfficeCountMapper.updateOaOfficeCount(oaOfficeCount);
    }

    /**
     * 批量删除办公用品申领申购
     *
     * @param ids 需要删除的办公用品申领申购主键
     * @return 结果
     */
    @Override
    public int deleteOaOfficeCountByIds(String[] ids)
    {
        return oaOfficeCountMapper.deleteOaOfficeCountByIds(ids);
    }

    /**
     * 删除办公用品申领申购信息
     *
     * @param id 办公用品申领申购主键
     * @return 结果
     */
    @Override
    public int deleteOaOfficeCountById(String id)
    {
        return oaOfficeCountMapper.deleteOaOfficeCountById(id);
    }
}
