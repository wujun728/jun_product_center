package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaPomsWorkmarksOutsiteMapper;
import com.ruoyi.qixing.domain.OaPomsWorkmarksOutsite;
import com.ruoyi.qixing.service.IOaPomsWorkmarksOutsiteService;

/**
 * 外出信息Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaPomsWorkmarksOutsiteServiceImpl implements IOaPomsWorkmarksOutsiteService
{
    @Autowired
    private OaPomsWorkmarksOutsiteMapper oaPomsWorkmarksOutsiteMapper;

    /**
     * 查询外出信息
     *
     * @param id 外出信息主键
     * @return 外出信息
     */
    @Override
    public OaPomsWorkmarksOutsite selectOaPomsWorkmarksOutsiteById(String id)
    {
        return oaPomsWorkmarksOutsiteMapper.selectOaPomsWorkmarksOutsiteById(id);
    }

    /**
     * 查询外出信息列表
     *
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 外出信息
     */
    @Override
    public List<OaPomsWorkmarksOutsite> selectOaPomsWorkmarksOutsiteList(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        return oaPomsWorkmarksOutsiteMapper.selectOaPomsWorkmarksOutsiteList(oaPomsWorkmarksOutsite);
    }

    /**
     * 新增外出信息
     *
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 结果
     */
    @Override
    public int insertOaPomsWorkmarksOutsite(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        oaPomsWorkmarksOutsite.setCreateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksOutsiteMapper.insertOaPomsWorkmarksOutsite(oaPomsWorkmarksOutsite);
    }

    /**
     * 修改外出信息
     *
     * @param oaPomsWorkmarksOutsite 外出信息
     * @return 结果
     */
    @Override
    public int updateOaPomsWorkmarksOutsite(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        oaPomsWorkmarksOutsite.setUpdateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksOutsiteMapper.updateOaPomsWorkmarksOutsite(oaPomsWorkmarksOutsite);
    }

    /**
     * 批量删除外出信息
     *
     * @param ids 需要删除的外出信息主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksOutsiteByIds(String[] ids)
    {
        return oaPomsWorkmarksOutsiteMapper.deleteOaPomsWorkmarksOutsiteByIds(ids);
    }

    /**
     * 删除外出信息信息
     *
     * @param id 外出信息主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksOutsiteById(String id)
    {
        return oaPomsWorkmarksOutsiteMapper.deleteOaPomsWorkmarksOutsiteById(id);
    }
}
