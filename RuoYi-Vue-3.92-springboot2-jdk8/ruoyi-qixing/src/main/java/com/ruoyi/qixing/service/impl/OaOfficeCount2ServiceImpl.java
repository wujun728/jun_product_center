package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaOfficeCount2Mapper;
import com.ruoyi.qixing.domain.OaOfficeCount2;
import com.ruoyi.qixing.service.IOaOfficeCount2Service;

/**
 * 办公用品申领申购Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaOfficeCount2ServiceImpl implements IOaOfficeCount2Service
{
    @Autowired
    private OaOfficeCount2Mapper oaOfficeCount2Mapper;

    /**
     * 查询办公用品申领申购
     *
     * @param id 办公用品申领申购主键
     * @return 办公用品申领申购
     */
    @Override
    public OaOfficeCount2 selectOaOfficeCount2ById(String id)
    {
        return oaOfficeCount2Mapper.selectOaOfficeCount2ById(id);
    }

    /**
     * 查询办公用品申领申购列表
     *
     * @param oaOfficeCount2 办公用品申领申购
     * @return 办公用品申领申购
     */
    @Override
    public List<OaOfficeCount2> selectOaOfficeCount2List(OaOfficeCount2 oaOfficeCount2)
    {
        return oaOfficeCount2Mapper.selectOaOfficeCount2List(oaOfficeCount2);
    }

    /**
     * 新增办公用品申领申购
     *
     * @param oaOfficeCount2 办公用品申领申购
     * @return 结果
     */
    @Override
    public int insertOaOfficeCount2(OaOfficeCount2 oaOfficeCount2)
    {
        oaOfficeCount2.setCreateTime(DateUtils.getNowDate());
        return oaOfficeCount2Mapper.insertOaOfficeCount2(oaOfficeCount2);
    }

    /**
     * 修改办公用品申领申购
     *
     * @param oaOfficeCount2 办公用品申领申购
     * @return 结果
     */
    @Override
    public int updateOaOfficeCount2(OaOfficeCount2 oaOfficeCount2)
    {
        oaOfficeCount2.setUpdateTime(DateUtils.getNowDate());
        return oaOfficeCount2Mapper.updateOaOfficeCount2(oaOfficeCount2);
    }

    /**
     * 批量删除办公用品申领申购
     *
     * @param ids 需要删除的办公用品申领申购主键
     * @return 结果
     */
    @Override
    public int deleteOaOfficeCount2ByIds(String[] ids)
    {
        return oaOfficeCount2Mapper.deleteOaOfficeCount2ByIds(ids);
    }

    /**
     * 删除办公用品申领申购信息
     *
     * @param id 办公用品申领申购主键
     * @return 结果
     */
    @Override
    public int deleteOaOfficeCount2ById(String id)
    {
        return oaOfficeCount2Mapper.deleteOaOfficeCount2ById(id);
    }
}
